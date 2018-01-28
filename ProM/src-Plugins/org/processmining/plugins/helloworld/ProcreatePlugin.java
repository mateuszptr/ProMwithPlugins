package org.processmining.plugins.helloworld;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.plugin.events.Logger.MessageLevel;
import org.processmining.framework.providedobjects.ProvidedObjectDeletedException;
import org.processmining.framework.providedobjects.ProvidedObjectID;
import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;

@Plugin(name = "Procreate", parameterLabels = { "Father", "Mother", "Procreation Configuration" }, returnLabels = {
		"Child" }, returnTypes = { Person.class })
public class ProcreatePlugin {
	@UITopiaVariant(affiliation = "University of Life", author = "Britney J. Spears", email = "britney@westergaard.eu", uiLabel = UITopiaVariant.USEPLUGIN)
	@PluginVariant(requiredParameterLabels = { 0, 1, 2 })
	public static Person procreate(final PluginContext context, final Person father, final Person mother,
			final ProcreationConfiguration config) {
		context.log("Creating new Person", MessageLevel.NORMAL);
		Person child = new Person();
		child.setAge(0);
		if (config == null) {
			context.log("No configuration given!", MessageLevel.ERROR);
			return null;
		}
		if ("Dżesika".equalsIgnoreCase(config.getName())) {
			context.log("Person has stupid name!", MessageLevel.WARNING);
		}
		try {
			child.setName(new Name(config.getName(), father.getName().getLast()));
			context.log("New person set up!", MessageLevel.DEBUG);
		} catch (Exception e) {
			context.log(e);
			return null;
		}
		context.log("About to successfully return.", MessageLevel.TEST);
		return child;
	}

	@UITopiaVariant(affiliation = "University of Life", author = "Britney J. Spears", email = "britney@westergaard.eu", uiLabel = UITopiaVariant.USEPLUGIN)
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public static Person procreate(final UIPluginContext cc, final Person father, final Person mother) {
		ProcreationConfiguration pc = new ProcreationConfiguration();
		populate(cc, pc);
		return procreate(cc, father, mother, pc);
	}

	public static ProcreationConfiguration populate(final UIPluginContext context,
			final ProcreationConfiguration config) {
		ProMPropertiesPanel panel = new ProMPropertiesPanel("Configure Procreation");
		ProMTextField name = panel.addTextField("Name", config.getName());
		final InteractionResult interactionResult = context.showConfiguration("Setup Procreation", panel);
		if (interactionResult == InteractionResult.FINISHED || interactionResult == InteractionResult.CONTINUE
				|| interactionResult == InteractionResult.NEXT) {
			config.setName(name.getText());
			return config;
		}
		return null;
	}
	

	@UITopiaVariant(affiliation = "University of Life", author = "Britney J. Spears", email = "britney@westergaard.eu", uiLabel = "Procreate2")
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public static Person procreate2(final UIPluginContext context, final Person father, final Person mother) {
		ProcreationConfiguration config = new ProcreationConfiguration();
		for (ProvidedObjectID id : context.getProvidedObjectManager().getProvidedObjects()) {
			try {
				Class<?> clazz = context.getProvidedObjectManager().getProvidedObjectType(id);
				if (ProcreationConfiguration.class.isAssignableFrom(clazz)) {
					Object o = context.getProvidedObjectManager().getProvidedObjectObject(id, false);
					if (o instanceof ProcreationConfiguration) {
						ProcreationConfiguration c = (ProcreationConfiguration) o;
						config.setName(c.getName());
						break;
					}
				}
			} catch (ProvidedObjectDeletedException _) {
				// Ignore
			}
		}
		config = populate(context, config);
		context.getProvidedObjectManager().createProvidedObject("Procreation Configuration", config,
				ProcreationConfiguration.class, context);
		return procreate(context, father, mother, config);
	}

}

class ProcreationConfiguration {
	String name;

	public ProcreationConfiguration() {

	}

	public ProcreationConfiguration(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}