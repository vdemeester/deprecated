package org.shortbrain.util.collection;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SQLConversion
 * 
 * @author vincent
 * 
 */
public class ConversionTest {

  @Before
  public void onSetup() throws Exception {

  }

  @Test
  public void valueFromTransformerNull() {
    String toto;
    try {
      toto = Conversion.valueFromTransformer("toto", null);
      Assert.assertNotNull(toto);
      Assert.assertEquals("toto", toto);
    } catch (IllegalAccessException e1) {
      Assert.fail("Should not throw an IllegalAccessException");
      e1.printStackTrace();
    }
  }

  @Test(expected = IllegalAccessException.class)
  public void valueFromTransformerWrongTransformer()
      throws IllegalAccessException {
    try {
      Method m = ConversionTest.class.getDeclaredMethod("wrongTransformer",
          Object.class);
      Conversion.valueFromTransformer("toto", m);
    } catch (SecurityException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  public void valueFromTransformer() {
    try {
      Method m = ConversionTest.class.getDeclaredMethod("rightTransformer",
          Object.class);
      String totoQuoted = Conversion.valueFromTransformer("toto", m);
      Assert.assertNotNull(totoQuoted);
      Assert.assertEquals("'toto'", totoQuoted);
    } catch (SecurityException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    }
  }

  public static String rightTransformer(Object o) {
    String ret = "";
    if (o instanceof String) {
      ret = "'" + o + "'";
    } else {
      ret = o.toString();
    }
    return ret;
  }

  public static Object wrongTransformer(Object o) {
    return o;
  }
}
