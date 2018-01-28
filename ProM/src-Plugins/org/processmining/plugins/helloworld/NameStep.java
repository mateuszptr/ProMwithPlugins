package org.processmining.plugins.helloworld;
 
import javax.swing.JComponent;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.framework.util.ui.wizard.ProMWizardStep;
 
public class NameStep<M> implements ProMWizardStep<ProcreationConfiguration> {
  public String getTitle() {
    return "Name the Mistake";
  }
 
  private class ProcreationPanel extends ProMPropertiesPanel {
    private final ProMTextField name;
 
    public ProcreationPanel(ProcreationConfiguration config) {
      super("Configure Procreation");
      name = this.addTextField("Name", config.getName());
    }
 
    public String getName() {
      return name.getName();
    }
  }
 
  public JComponent getComponent(final ProcreationConfiguration config) {
    return new ProcreationPanel(config);
  }
 
  public boolean canApply(final ProcreationConfiguration model,
                          final JComponent component) {
    ProcreationPanel panel = (ProcreationPanel) component;
    return !"".equals(panel.getName());
  }
 
  public ProcreationConfiguration apply(final ProcreationConfiguration model,
                                        final JComponent component) {
    ProcreationPanel panel = (ProcreationPanel) component;
    model.setName(panel.getName());
    return model;
  }
}