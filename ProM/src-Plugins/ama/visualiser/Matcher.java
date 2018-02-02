package ama.visualiser;

import java.util.ArrayList;
import java.util.List;

import put.algebraminer.event.AlgebraNode;

public class Matcher {
	private List<NodeObject> sendNodes = new ArrayList<NodeObject>();
	private List<NodeObject> recvNodes = new ArrayList<NodeObject>();
	
	private List<LabelLine> lines = new ArrayList<LabelLine>();
	
	public void addSend(NodeObject node) {
		sendNodes.add(node);
	}
	
	public void addRecv(NodeObject node) {
		recvNodes.add(node);
	}
	
	public List<LabelLine> getServiceLines() {
		return lines;
	}
	
	public void match() {
		for(NodeObject nodeSend : sendNodes) {
			int maxMark = 0;
			NodeObject bestNode = null;
			for(AlgebraNode recvNode : nodeSend.getNode().getSendingTo()) {
				int mark = 0;
				if(recvNode.getEvent().getLpid().equals(nodeSend.getNode().getEvent().getRpid())) mark += 1;
				if(recvNode.getEvent().getRpid().equals(nodeSend.getNode().getEvent().getLpid())) mark += 2;
				
				if(mark > maxMark) {
					maxMark = mark;
					for(NodeObject nodeRecv : recvNodes) {
						if(recvNode.equals(nodeRecv.getNode())) {
							bestNode = nodeRecv;
							break;
						}
					}
				}
			}
			
			if(maxMark != 0)  {
				lines.add(new LabelLine(nodeSend.getLabel(), bestNode.getLabel()));
				recvNodes.remove(bestNode);
			}
			
//			for(NodeObject nodeRecv : recvNodes) {
//				if(nodeSend.getNode().getEvent().getLpid().equals(nodeRecv.getNode().getEvent().getRpid()) && nodeSend.getNode().getEvent().getRpid().equals(nodeRecv.getNode().getEvent().getLpid())) {
//					lines.add(new LabelLine(nodeSend.getLabel(), nodeRecv.getLabel()));
//					recvNodes.remove(nodeRecv);
//					break;
//				}
//			}
		}
	}
}
