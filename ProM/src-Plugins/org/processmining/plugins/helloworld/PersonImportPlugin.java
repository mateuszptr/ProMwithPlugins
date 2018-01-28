package org.processmining.plugins.helloworld;
 
import java.io.InputStream;
import java.util.Scanner;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
 
@Plugin(name = "Import Person",
        parameterLabels = { "Filename" },
        returnLabels = { "Person" },
        returnTypes = { Person.class })
@UIImportPlugin(description = "Person",
                extensions = { "person" })
public class PersonImportPlugin extends AbstractImportPlugin {
  @Override
  protected Person importFromStream(final PluginContext context,
                                    final InputStream input,
                                    final String filename,
                                    final long fileSizeInBytes) {
    try {
      context.getFutureResult(0).setLabel("Person imported from " + filename);
    } catch (final Throwable _) {
      // Don't care if this fails
    }
    Person result = new Person();
    Scanner s = new Scanner(input);
    String line1 = s.nextLine();
    String tokens[] = line1.split(" ");
    s.close();
    result.setName(new Name(tokens[0], tokens[1]));
    result.setAge(Integer.parseInt(tokens[2]));
    return result;
  }
}