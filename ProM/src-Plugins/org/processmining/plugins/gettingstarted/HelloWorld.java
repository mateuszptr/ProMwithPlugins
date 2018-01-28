package org.processmining.plugins.gettingstarted;

import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

public class HelloWorld {
	@Plugin(
			name = "Hello World Plugin",
			parameterLabels = {},
			returnLabels = {"String"},
			returnTypes = {String.class},
			userAccessible = true,
			help = "Produces 'hello world'"
	)
	@UITopiaVariant(
			affiliation = "PUT",
			author = "Mateusz Peter",
			email = "mateuszptr@gmail.com"
	)
	public static String helloWorld(PluginContext context)
	{
		return "Hello World";
	}
}
