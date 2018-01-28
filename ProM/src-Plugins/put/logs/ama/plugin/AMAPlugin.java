package put.logs.ama.plugin;

import java.util.Map;

import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;

import put.algebraminer.algorithm.GlobalAlgebraMiner;
import put.algebraminer.algorithm.LogImporter;
import put.algebraminer.event.GlobalAlgebraGraph;

public class AMAPlugin {
	@Plugin(
            name = "GlobalAlgebraMiner plugin", 
            parameterLabels = { "XES Log containing all traces of distributed process"}, 
            returnLabels = { "Global Algebra Graph of distributed process" }, 
            returnTypes = { GlobalAlgebraGraph.class }, 
            userAccessible = true, 
            help = "Produces the Global Algebra Graph of distributed process"
    )
    @UITopiaVariant(
            affiliation = "PUT", 
            author = "Mateusz Peter", 
            email = "mateusz.peter@student.put.poznan.pl"
    )
	public static GlobalAlgebraGraph helloWorld(PluginContext context, XLog wholeLog) {
		Graph<String> graph = GraphBuilder.directed().build();
		Map<String, XLog> xlogMap = LogImporter.importLogsFromBigLog(wholeLog);
		GlobalAlgebraGraph g = new GlobalAlgebraGraph();
		g.models = LogImporter.generateLogModels(xlogMap);
		GlobalAlgebraMiner.findConnections(g.models);
		
        return g;
	}
}
