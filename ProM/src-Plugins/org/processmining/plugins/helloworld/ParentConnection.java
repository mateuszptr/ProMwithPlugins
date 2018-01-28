package org.processmining.plugins.helloworld;
 
import org.processmining.framework.connections.impl.AbstractConnection;
 
public class ParentConnection extends AbstractConnection {
  public static final String FATHER = "father";
  public static final String MOTHER = "mother";
  public static final String CHILD = "child";
 
  public ParentConnection(Person child, Person mother) {
    super("Mother of " + child.getName() + " is " + mother.getName());
    put(CHILD, child);
    put(MOTHER, mother);
  }
 
  public ParentConnection(Person child, Person father, Person mother) {
    super("Parents of " + child.getName() + " are " + father.getName() + " and " + mother.getName());
    put(CHILD, child);
    put(FATHER, father);
    put(MOTHER, mother);
  }
}