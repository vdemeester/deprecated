package org.shortbrain.util.reflection;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldComparator implements Comparator<Field> {

  @Override
  public int compare(Field f2, Field f1) {
    return f2.getName().compareTo(f1.getName());
  }

}
