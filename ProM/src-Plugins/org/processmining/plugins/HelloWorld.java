package org.processmining.plugins;

import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

import put.algebraminer.event.GlobalAlgebraGraph;

public class HelloWorld {
        @Plugin(
                name = "My Test Plugin", 
                parameterLabels = { "GlobalAlgebraGraph"}, 
                returnLabels = { "AlgebraNode Type" }, 
                returnTypes = { String.class }, 
                userAccessible = true, 
                help = "Produces the AlgebraNodeType'"
        )
        @UITopiaVariant(
                affiliation = "My company", 
                author = "My name", 
                email = "My e-mail address"
        )
        public static String helloWorld(PluginContext context, GlobalAlgebraGraph g) {
                return "Płaszczka";
        }
}