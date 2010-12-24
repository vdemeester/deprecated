package org.shortbrain.util.reflection;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.ReflectionException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Test with Enclosed object.
 * 
 * @author vincent
 * 
 */
public class ReflectTest extends TestCase {

	private ObjectToBeReflected o;

	@Before
	public void setUp() throws Exception {
		o = new ObjectToBeReflected();
		o.setPrivateString("Private Field");
		o.setPublicString("Public Field");
	}

	@After
	public void tearDown() throws Exception {
		o = null;
	}

	@Test
	public void testGetFields() {
		List<Field> fields = Reflect.getFields(o);
		try {
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
      assertEquals(2, fields.size());
			assertTrue(fields
			    .contains(o.getClass().getDeclaredField("privateString")));
			assertTrue(fields.contains(o.getClass().getDeclaredField("publicString")));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testGetFieldsWithNullObject() {
		o = null;
		try {
			List<Field> fields = Reflect.getFields(o);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetFieldsWithEmptyObject() {
		EmptyObject eo = new EmptyObject();
		List<Field> fields = Reflect.getFields(eo);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFields() {
		List<Field> fields = Reflect.getVisibleFields(o);
		try {
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields
			    .contains(o.getClass().getDeclaredField("privateString")));
			assertTrue(fields.contains(o.getClass().getDeclaredField("publicString")));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldsWithNullObject() {
		o = null;
		try {
			List<Field> fields = Reflect.getVisibleFields(o);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetVisibleFieldsWithEmptyObject() {
		EmptyObject eo = new EmptyObject();
		List<Field> fields = Reflect.getVisibleFields(eo);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetFieldValueObject() {
		try {
			List<Object> values = Reflect.getFieldValue(o);
			assertNotNull(values);
			assertFalse(values.isEmpty());
      assertEquals(2, values.size());
			assertTrue(values.contains("Public Field"));
			assertTrue(values.contains("Private Field"));
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetFieldValueObjectNull() {
		try {
			List<Object> values = Reflect.getFieldValue(null);
			assertNotNull(values);
			assertTrue(values.isEmpty());
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectBooleanTrue() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(o, true);
			assertNotNull(valuesMap);
			assertFalse(valuesMap.isEmpty());
			assertEquals(2, valuesMap.size());
			Set<String> fields = valuesMap.keySet();
			assertFalse(fields.contains("privateString"));
			assertFalse(fields.contains("publicString"));
			assertTrue(fields.contains("PrivateString"));
			assertTrue(fields.contains("PublicString"));
			assertEquals("Private Field", valuesMap.get("PrivateString"));
			assertEquals("Public Field", valuesMap.get("PublicString"));
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectBooleanFalse() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(o, false);
			assertNotNull(valuesMap);
			assertFalse(valuesMap.isEmpty());
			assertEquals(2, valuesMap.size());
			Set<String> fields = valuesMap.keySet();
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
			assertFalse(fields.contains("PrivateString"));
			assertFalse(fields.contains("PublicString"));
			assertEquals("Private Field", valuesMap.get("privateString"));
			assertEquals("Public Field", valuesMap.get("publicString"));
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectNullBooleanTrue() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(null,
			    true);
			assertNull(valuesMap);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectNullBooleanFalse() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(null,
			    true);
			assertNull(valuesMap);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectEmptyBooleanTrue() {
		EmptyObject eo = new EmptyObject();
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(eo, true);
			assertNotNull(valuesMap);
			assertTrue(valuesMap.isEmpty());
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectEmptyBooleanFalse() {
		EmptyObject eo = new EmptyObject();
		try {
			Map<String, Object> valuesMap = Reflect
			    .getVisibleFieldValueMap(eo, false);
			assertNotNull(valuesMap);
			assertTrue(valuesMap.isEmpty());
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObject() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(o);
			assertNotNull(valuesMap);
			assertFalse(valuesMap.isEmpty());
			assertEquals(2, valuesMap.size());
			Set<String> fields = valuesMap.keySet();
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
			assertFalse(fields.contains("PrivateString"));
			assertFalse(fields.contains("PublicString"));
			assertEquals("Private Field", valuesMap.get("privateString"));
			assertEquals("Public Field", valuesMap.get("publicString"));
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectNull() {
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(null);
			assertNull(valuesMap);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueMapObjectEmpty() {
		EmptyObject eo = new EmptyObject();
		try {
			Map<String, Object> valuesMap = Reflect.getVisibleFieldValueMap(eo);
			assertNotNull(valuesMap);
			assertTrue(valuesMap.isEmpty());
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectBooleanTrue() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o, true);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(2, values.size());
			assertTrue(values.get(0) instanceof String);
			assertEquals("Private Field", (String) values.get(0));
			assertTrue(values.get(1) instanceof String);
			assertEquals("Public Field", (String) values.get(1));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o, true);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(1, values.size());
			assertTrue(values.get(0) instanceof String);
			assertEquals("Public Field", (String) values.get(0));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectBooleanFalse() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o, false);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(2, values.size());
			assertTrue(values.get(0) instanceof String);
			assertEquals("Private Field", (String) values.get(0));
			assertTrue(values.get(1) instanceof String);
			assertEquals("Public Field", (String) values.get(1));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o, false);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(2, values.size());
			assertNull(values.get(0));
			assertTrue(values.get(1) instanceof String);
			assertEquals("Public Field", (String) values.get(1));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectNullBooleanTrue() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(null, true);
			assertNull(values);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectNullBooleanFalse() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(null, false);
			assertNull(values);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectEmptyBooleanTrue() {
		EmptyObject eo = new EmptyObject();
		try {
			List<Object> values = Reflect.getVisibleFieldValue(eo, true);
			assertNotNull(values);
			assertTrue(values.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectEmptyBooleanFalse() {
		EmptyObject eo = new EmptyObject();
		try {
			List<Object> values = Reflect.getVisibleFieldValue(eo, false);
			assertNotNull(values);
			assertTrue(values.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObject() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(2, values.size());
			assertTrue(values.get(0) instanceof String);
			assertEquals("Private Field", (String) values.get(0));
			assertTrue(values.get(1) instanceof String);
			assertEquals("Public Field", (String) values.get(1));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<Object> values = Reflect.getVisibleFieldValue(o);
			assertNotNull(values);
			assertFalse(values.isEmpty());
			assertEquals(2, values.size());
			assertNull(values.get(0));
			assertTrue(values.get(1) instanceof String);
			assertEquals("Public Field", (String) values.get(1));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectNull() {
		try {
			List<Object> values = Reflect.getVisibleFieldValue(null);
			assertNull(values);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldValueObjectEmpty() {
		EmptyObject eo = new EmptyObject();
		try {
			List<Object> values = Reflect.getVisibleFieldValue(eo);
			assertNotNull(values);
			assertTrue(values.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetFieldValueFieldObject() {
		try {
			Object object = Reflect.getFieldValue(
			    o.getClass().getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", object);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Object object = Reflect.getFieldValue(
			    o.getClass().getDeclaredField("publicString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Public Field", object);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testGetFieldValueFieldNullObject() {
		try {
			Object object = Reflect.getFieldValue(null, o);
			assertNull(object);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetFieldValueFieldNullObjectNull() {
		try {
			Object object = Reflect.getFieldValue(null, null);
			assertNull(object);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		}
	}

	@Test
	public void testGetFieldNames() {
		List<String> fields = Reflect.getFieldNames(o);
		try {
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
      assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetFieldNamesWithNullObject() {
		o = null;
		try {
			List<String> fields = Reflect.getFieldNames(o);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetFieldNamesWithEmptyObject() {
		EmptyObject eo = new EmptyObject();
		List<String> fields = Reflect.getFieldNames(eo);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNames() {
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNamesWithNullObject() {
		o = null;
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetVisibleFieldNamesWithEmptyObject() {
		EmptyObject eo = new EmptyObject();
		List<String> fields = Reflect.getVisibleFieldNames(eo);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectBooleanTrue() {
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, true);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, true);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(1, fields.size());
			assertFalse(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectNullBooleanTrue() {
		o = null;
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, true);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectEmptyBooleanTrue() {
		EmptyObject eo = new EmptyObject();
		List<String> fields = Reflect.getVisibleFieldNames(eo, true);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectBooleanFalse() {
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, false);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		o.setPrivateString(null);
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, false);
			assertNotNull(fields);
			assertFalse(fields.isEmpty());
			assertEquals(2, fields.size());
			assertTrue(fields.contains("privateString"));
			assertTrue(fields.contains("publicString"));
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectNullBooleanFalse() {
		o = null;
		try {
			List<String> fields = Reflect.getVisibleFieldNames(o, false);
			assertNull(fields);
		} catch (NullPointerException e) {
			fail("Should not throw a NullPointerException.");
		}
	}

	@Test
	public void testGetVisibleFieldNamesObjectEmptyBooleanFalse() {
		EmptyObject eo = new EmptyObject();
		List<String> fields = Reflect.getVisibleFieldNames(eo, false);
		try {
			assertNotNull(fields);
			assertTrue(fields.isEmpty());
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetFieldStringObject() {
		try {
			Field f = Reflect.getField("privateString", o);
			assertNotNull(f);
			assertEquals(ObjectToBeReflected.class.getDeclaredField("privateString"),
			    f);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Field f = Reflect.getField("publicString", o);
			assertNotNull(f);
			assertEquals(ObjectToBeReflected.class.getDeclaredField("publicString"),
			    f);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Field f = Reflect.getField("noField", o);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringNullObject() {
		try {
			Field f = Reflect.getField(null, o);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringObjectNull() {
		try {
			Field f = Reflect.getField("Field", null);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringNullObjectNull() {
		try {
			Field f = Reflect.getField(null, null);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringClass() {
		try {
			Field f = Reflect.getField("privateString", ObjectToBeReflected.class);
			assertNotNull(f);
			assertEquals(ObjectToBeReflected.class.getDeclaredField("privateString"),
			    f);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Field f = Reflect.getField("publicString", ObjectToBeReflected.class);
			assertNotNull(f);
			assertEquals(ObjectToBeReflected.class.getDeclaredField("publicString"),
			    f);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Field f = Reflect.getField("noField", ObjectToBeReflected.class);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringNullClass() {
		try {
			Field f = Reflect.getField(null, ObjectToBeReflected.class);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringClassNull() {
		try {
			Field f = Reflect.getField("Field", null);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldStringNullClassNull() {
		try {
			Field f = Reflect.getField(null, null);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetFieldWithEmptyObject() {
		try {
			Field f = Reflect.getField("anyField", EmptyObject.class);
      fail("Should throw a ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		}
	}

	// FIXME : Tester avec DoublePK, ...
	@Test
	public void testGetValueFieldObject() {
		try {
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("publicString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Public Field", object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testGetValueFieldNullObject() {
		try {
			Object object = Reflect.getValue((Field) null, o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetValueFieldNullObjectNull() {
		try {
			Object object = Reflect.getValue((Field) null, null);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetValueStringObject() {
		try {
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Object object = Reflect.getValue("publicString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Public Field", object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Object object = Reflect.getValue("noField", o);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetValueStringNullObject() {
		try {
			Object object = Reflect.getValue((String) null, o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testGetValueStringNullObjectNull() {
		try {
			Object object = Reflect.getValue((String) null, null);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	// GetValue doit �tre test� avant...

	@Test
	public void testSetValueFieldObjectObject() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private");
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private");
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectObjectBooleanTrue() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private", true);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private", true);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectObjectBooleanFalse() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private", false);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o,
			    "Not So Private", false);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectObjectNull() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o, null);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectObjectNullBooleanTrue() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o, null,
			    true);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	public void testSetValueFieldObjectObjectNullBooleanFalse() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o, null,
			    false);
			Object object = Reflect.getValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNulldObjectObject() {
		try {
			Reflect.setValue((Field) null, o, "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNulldObjectObjectBoolean() {
		try {
			Reflect.setValue((Field) null, o, "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((Field) null, o, "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectNullObject() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectNullObjectBoolean() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNullObjectNullObject() {
		try {
			Reflect.setValue((Field) null, null, "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNullObjectNullObjectBoolean() {
		try {
			Reflect.setValue((Field) null, null, "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((Field) null, null, "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectNullObjectNull() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    null);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldObjectNullObjectNullBoolean() {
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    null, true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
		try {
			Reflect.setValue(
			    ObjectToBeReflected.class.getDeclaredField("privateString"), null,
			    null, false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("NoSuchFieldException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNullObjectNullObjectNull() {
		try {
			Reflect.setValue((Field) null, null, null);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueFieldNullObjectNullObjectNullBoolean() {
		try {
			Reflect.setValue((Field) null, null, null, true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((Field) null, null, null, false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectObject() {
		try {
			Reflect.setValue("privateString", o, "Not So Private");
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue("privateString", o, "Not So Private");
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectObjectBooleanTrue() {
		try {
			Reflect.setValue("privateString", o, "Not So Private", true);
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue("privateString", o, "Not So Private", true);
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectObjectBooleanFalse() {
		try {
			Reflect.setValue("privateString", o, "Not So Private", false);
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			o.setPrivateString(null);
			Reflect.setValue("privateString", o, "Not So Private", false);
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Not So Private", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectObjectNull() {
		try {
			Reflect.setValue("privateString", o, null);
			Object object = Reflect.getValue("privateString", o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectObjectNullBooleanTrue() {
		try {
			Reflect.setValue("privateString", o, null, true);
			Object object = Reflect.getValue("privateString", o);
			assertNull(object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	public void testSetValueStringObjectObjectNullBooleanFalse() {
		try {
			Reflect.setValue("privateString", o, null, false);
			Object object = Reflect.getValue("privateString", o);
			assertNotNull(object);
			assertTrue(object instanceof String);
			assertEquals("Private Field", (String) object);
    } catch (ReflectionException e) {
			e.printStackTrace();
      fail("ReflectionException : " + e);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringNulldObjectObject() {
		try {
			Reflect.setValue((String) null, o, "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringNulldObjectObjectBoolean() {
		try {
			Reflect.setValue((String) null, o, "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((String) null, o, "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectNullObject() {
		try {
			Reflect.setValue("privateString", null, "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectNullObjectBoolean() {
		try {
			Reflect.setValue("privateString", null, "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue("privateString", null, "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringNullObjectNullObject() {
		try {
			Reflect.setValue((String) null, null, "Not So Private");
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringNullObjectNullObjectBoolean() {
		try {
			Reflect.setValue((String) null, null, "Not So Private", true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((String) null, null, "Not So Private", false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectNullObjectNull() {
		try {
			Reflect.setValue("privateString", null, null);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringObjectNullObjectNullBoolean() {
		try {
			Reflect.setValue("privateString", null, null, true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue("privateString", null, null, false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueStringNullObjectNullObjectNull() {
		try {
			Reflect.setValue((String) null, null, null);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

	@Test
	public void testSetValueSringNullObjectNullObjectNullBoolean() {
		try {
			Reflect.setValue((String) null, null, null, true);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
		try {
			Reflect.setValue((Field) null, null, null, false);
      fail("Should throw an ReflectionException");
    } catch (ReflectionException e) {
			assertTrue(true);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("SecurityException : " + e);
		}
	}

}
