package ama.visualiser;

import javax.swing.JLabel;

import put.algebraminer.event.AlgebraNode;

public class NodeObject {
	private JLabel label;
	private AlgebraNode node;
	
	public NodeObject(JLabel label, AlgebraNode node) {
		this.label = label;
		this.node = node;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public AlgebraNode getNode() {
		return node;
	}
	
	public void setNode(AlgebraNode node) {
		this.node = node;
	}
}
