package ama.visualiser;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

import com.google.common.graph.MutableGraph;

import put.algebraminer.event.AlgebraNode;
import put.algebraminer.event.EventType;
import put.algebraminer.event.GlobalAlgebraGraph;
import put.algebraminer.event.LogModel;

public class VisualiserMain {
	@Plugin(name = "Alpha Miner Visualiser",
			parameterLabels = { "GlobalAlgebraGraph" },
			returnLabels = { "Alpha Miner Process Visualisation" },
			returnTypes = { JComponent.class },
			userAccessible = true)
			@Visualizer
			@PluginVariant(requiredParameterLabels = { 0 })
  public static JComponent visualize(PluginContext context, GlobalAlgebraGraph data) {
		
		JPanel2 endComponent = new JPanel2();		
		Matcher matcher = new Matcher();
		
		for(LogModel model : data.models ) {
			Box mainBox = new Box(BoxLayout.Y_AXIS);
			mainBox.setBorder(BorderFactory.createLineBorder(Color.blue));
			
			MutableGraph<AlgebraNode> actualGraph = model.getAg().getGraph();
			
			AlgebraNode startNode = null;
			for (AlgebraNode node : actualGraph.nodes()) {
				if(node.getType() == AlgebraNode.Type.START) startNode = node;
			}
			
			NodeObject end = visualiseNode(matcher, endComponent, actualGraph, mainBox, null, startNode, null);
			endComponent.add(mainBox);
		}
		
		matcher.match();
		endComponent.setServiceLines(matcher.getServiceLines());

		JScrollPane scroll = new JScrollPane(endComponent);
		return scroll;		
  }
	
	
	
	private static NodeObject visualiseNode(Matcher matcher, JPanel2 endComponent, MutableGraph<AlgebraNode> actualGraph, JComponent container, JLabel precedingNodeLabel, AlgebraNode actualNode, AlgebraNode.Type endNodeType) {
		List<JLabel> connecting = null;
		
		while (actualNode.getType() != endNodeType) {
			JLabel actualNodeLabel = makeLabel(actualNode);
			container.add(actualNodeLabel);
			
			if(actualNode.getType() == AlgebraNode.Type.SIMPLE) {
				if(EventType.fromString(actualNode.getEvent().getType()) == EventType.SEND) {
					matcher.addSend(new NodeObject(actualNodeLabel, actualNode));
				}
				if(EventType.fromString(actualNode.getEvent().getType()) == EventType.RECV) {
					matcher.addRecv(new NodeObject(actualNodeLabel, actualNode));
				}
			}
			
			///prinshit begin
			if(actualNode.getType() == AlgebraNode.Type.SIMPLE) {
				if(EventType.fromString(actualNode.getEvent().getType()) == EventType.SEND || EventType.fromString(actualNode.getEvent().getType()) == EventType.RECV) {
					System.out.println(EventType.fromString(actualNode.getEvent().getType())+" lpid: "+actualNode.getEvent().getLpid()+",\trpid: "+actualNode.getEvent().getRpid()+",\tsending:" + actualNode.getSendingTo().size());
					for(AlgebraNode x : actualNode.getSendingTo()) {
						System.out.println("\t\t"+EventType.fromString(x.getEvent().getType())+" lpid: "+x.getEvent().getLpid()+",\trpid: "+x.getEvent().getRpid());
					}
				}
			}
			//end
			
			if (connecting != null && !connecting.isEmpty()) {
				for(JLabel labelFrom : connecting) {
					endComponent.addLine(new LabelLine(labelFrom, actualNodeLabel));
				}
				connecting = null;
			}
			else if (precedingNodeLabel != null) endComponent.addLine(new LabelLine(precedingNodeLabel, actualNodeLabel));
			
			int size = actualGraph.successors(actualNode).size();
			if(size == 0) {
				break;
			}
			else if(size == 1) {
				actualNode = actualGraph.successors(actualNode).iterator().next();
				precedingNodeLabel = actualNodeLabel;
				connecting = new ArrayList<JLabel>();
				connecting.add(actualNodeLabel);
			}
			else {
				JPanel horizontalBox = new JPanel();
				
				AlgebraNode nextNode = null;
				connecting = new ArrayList<JLabel>();
				
				for(AlgebraNode node : actualGraph.successors(actualNode)) {
					Box verticalBox = new Box(BoxLayout.Y_AXIS);
					AlgebraNode.Type type = null;
					switch(actualNode.getType()) {
						case ALT_START: type = AlgebraNode.Type.ALT_END; break;
						case PLL_START: type = AlgebraNode.Type.PLL_END; break;
						default:;
					}
					
					NodeObject received =  visualiseNode(matcher, endComponent, actualGraph, verticalBox, actualNodeLabel, node, type);
					nextNode = received.getNode();
					connecting.add(received.getLabel());
					
					horizontalBox.add(verticalBox);
				}
				actualNode = nextNode;
				container.add(horizontalBox);
			}			
		}
		return new NodeObject(precedingNodeLabel, actualNode);
	}
	
	private static JLabel makeLabel(AlgebraNode node) {
		JLabel label = new JLabel(node.toString());
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		Color borderColor;
		switch(node.getType()) {
			case ALT_START: 
			case ALT_END:
				borderColor = Color.RED;
				break;
			case PLL_START:
			case PLL_END:
				borderColor = Color.GREEN;
				break;
			default:
				borderColor = Color.BLACK;
		}
				
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(VisualiserConfig.margin, VisualiserConfig.margin, VisualiserConfig.margin, VisualiserConfig.margin), BorderFactory.createLineBorder(borderColor)), BorderFactory.createEmptyBorder(VisualiserConfig.padding, VisualiserConfig.padding, VisualiserConfig.padding, VisualiserConfig.padding));
		label.setBorder(border);		
		return label;
	}
}
