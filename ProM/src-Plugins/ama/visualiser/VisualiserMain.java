package ama.visualiser;

import java.awt.Color;
import java.awt.Component;

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
		
		JComponent endComponent = new JPanel2();
		
		//endComponent.pain
		
		
		for(LogModel model : data.models ) {
			Box mainBox = new Box(BoxLayout.Y_AXIS);
			mainBox.setBorder(BorderFactory.createLineBorder(Color.blue));
			//mainBox.setAlignmentX(0.5f);
			//mainBox.setAlignmentY(0.5f);
			
			MutableGraph<AlgebraNode> actualGraph = model.getAg().getGraph();
			
			AlgebraNode startNode = null;
			for (AlgebraNode node : actualGraph.nodes()) {
				if(node.getType() == AlgebraNode.Type.START) startNode = node;
			}
			
			AlgebraNode end = visualiseNode(actualGraph, mainBox, startNode, null);
			endComponent.add(mainBox);
		}
		
		JScrollPane scroll = new JScrollPane(endComponent);
		return scroll;
		
		//return endComponent;		
  }
	
	
	
	private static AlgebraNode visualiseNode(MutableGraph<AlgebraNode> actualGraph, JComponent container, AlgebraNode actualNode, AlgebraNode.Type endNodeType) {
		while (actualNode.getType() != endNodeType) {
			JLabel label = new JLabel(""+actualNode);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			//label.setBorder(BorderFactory.createLineBorder(Color.black));
			Border compound = BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(5, 5, 5, 5));
			label.setBorder(compound);
			
			
			container.add(label);
			//container.add(Box.createRigidArea(new Dimension(0,5)));
			
			
			int size = actualGraph.successors(actualNode).size();
			if(size == 0) {
				break;
			}
			else if(size == 1) {
				actualNode = actualGraph.successors(actualNode).iterator().next();
			}
			else {
//				Box horizontalBox = new Box(BoxLayout.X_AXIS);
//				horizontalBox.setBorder(BorderFactory.createLineBorder(Color.red));
//				
				JPanel horizontalBox = new JPanel();
				//horizontalBox.
				horizontalBox.setBorder(BorderFactory.createLineBorder(Color.red));
				//horizontalBox.setLayout(new GridLayout());
				
				AlgebraNode nextNode = null;
				for(AlgebraNode node : actualGraph.successors(actualNode)) {
					Box verticalBox = new Box(BoxLayout.Y_AXIS);
					verticalBox.setBorder(BorderFactory.createLineBorder(Color.red));
					//verticalBox.setAlignmentX(0.5f);
					AlgebraNode.Type type = null;
					switch(actualNode.getType()) {
						case ALT_START: type = AlgebraNode.Type.ALT_END; break;
						case PLL_START: type = AlgebraNode.Type.PLL_END; break;
					}
					nextNode = visualiseNode(actualGraph, verticalBox, node, type);
					horizontalBox.add(verticalBox);
				}
				actualNode = nextNode;
				container.add(horizontalBox);
			}			
		}
		return actualNode;
	}
}
