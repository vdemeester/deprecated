package org.shortbrain.util.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Util class to easily convert Collection for different purpose.
 * 
 * The first use of this class will be to easily convert a collection to a
 * String that is a valid SQL representation of the Collection.
 * 
 * @author vincent
 * 
 */
public class Conversion {

  /**
   * @see convertCollectionForSQL(Collection, boolean, String, boolean, Method)
   */
  @SuppressWarnings("rawtypes")
  public static String convertColllectionForSQL(Collection collection,
      boolean capitalize) {
    return convertCollectionForSQL(collection, capitalize, "", false);
  }

  /**
   * @see convertCollectionForSQL(Collection, boolean, String, boolean, Method)
   */
  @SuppressWarnings("rawtypes")
  public static String convertCollectionForSQL(Collection col,
      boolean capitalize, boolean values) {
    return convertCollectionForSQL(col, capitalize, "", values);
  }

  /**
   * @see convertCollectionForSQL(Collection, boolean, String, boolean, Method)
   */
  @SuppressWarnings("rawtypes")
  public static String convertCollectionForSQL(Collection col,
      boolean capitalize, String prefix) {
    return convertCollectionForSQL(col, capitalize, prefix, false);
  }

  /**
   * @see convertCollectionForSQL(Collection, boolean, String, boolean, Method)
   */
  @SuppressWarnings("rawtypes")
  public static String convertCollectionForSQL(Collection col,
      boolean capitalize, String prefix, boolean values) {
    return convertCollectionForSQL(col, capitalize, prefix, values, null);
  }

  /**
   * Easily convert a collection to a String that is a valid SQL representation
   * of the Collection.
   * 
   * @param collection The Collection
   * @param capitalize Capitalize the object in collection
   * @param prefix Set a prefix to the value of the collection
   * @param values Value or .toString()
   * @param transformer The method to get the value from an object
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static String convertCollectionForSQL(Collection collection,
      boolean capitalize, String prefix, boolean values, Method transformer) {
    if ((collection == null) || (collection.size() == 0)) {
      return "";
    }
    if (collection.size() == 1) {
      if (values) {
        String value = collection.iterator().next().toString();
        try {
          value = valueFromTransformer(value, transformer);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        return value;
      } else {
        if (capitalize) {
          return prefix
              + StringUtils.capitalize(collection.iterator().next().toString());
        } else {
          return prefix + collection.iterator().next().toString();
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    boolean isFirst = true;
    for (Object o : collection) {
      if (isFirst) {
        isFirst = false;
      } else {
        sb.append(",");
      }
      if (o != null) {
        if (values) {
          try {
            sb.append(valueFromTransformer(o, transformer));
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        } else {
          if (capitalize) {
            sb.append(prefix + StringUtils.capitalize(o.toString()));
          } else {
            sb.append(prefix + o.toString());
          }
        }
      } else {
        sb.append("''");
      }
    }
    return sb.toString();
  }


  /**
   * @see convertMapForSQL(Map, String, Method)
   */
  @SuppressWarnings("rawtypes")
  public static String convertMapForSQL(Map map, String separateur) {
    return convertMapForSQL(map, separateur, null);
  }

  /**
   * Easily convert a map to a String that is a valid SQL representation
   * of the Map.
   * 
   * @param map
   * @param separateur
   * @param transformer
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static String convertMapForSQL(Map map, String separateur,
      Method transformer) {
    StringBuilder sb = new StringBuilder();
    if ((map == null) || (map.size() == 0)) {
      return "";
    }
    boolean isFirst = true;
    for (Object field : map.keySet()) {
      if (isFirst) {
        isFirst = false;
      } else {
        sb.append(separateur);
      }
      try {
        sb.append(field + " = "
            + valueFromTransformer(map.get(field), transformer));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }

  /**
   * Return the return value of the given transformer (Method) for the given
   * object
   * 
   * @param input object
   * @param transformer method to get the values
   * @return
   * @throws IllegalAccessException in case of the transformer does not return a
   *           String
   */
  protected static String valueFromTransformer(Object input, Method transformer)
      throws IllegalAccessException {
    String ret = input.toString();
    if (transformer != null) {
      if (transformer.getReturnType() == String.class) {
        try {
          ret = (String) transformer.invoke(null, input);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      } else {
        throw new IllegalAccessException(
            "The return type of the transformer should be a String.");
      }
    }
    return ret;
  }

}
