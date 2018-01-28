package put.logs.ama.plugin;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

import put.algebraminer.event.GlobalAlgebraGraph;

@Plugin(name = "Show Global Algebra Graph",
parameterLabels = { "GlobalAlgebraGraph" },
returnLabels = { "Graph Viewer" },
returnTypes = { JComponent.class },
userAccessible = true)
@Visualizer
public class AMAStringVisualizer {
	@PluginVariant(requiredParameterLabels = {0})
	public static JComponent visualize(final PluginContext cc, final GlobalAlgebraGraph g) {
		return new JLabel(g.toString());
	}
}
