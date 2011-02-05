package org.shortbrain.util.reflection;

/*
 * @author Vincent Demeester <vincent.demeester@nextep.net>
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.ReflectionException;

import org.apache.commons.lang.StringUtils;

/**
 * @author vincent
 *
 */
public class Reflect {

  public static final Set<String> defaultHiddenFields = new HashSet<String>(
			Arrays.asList(new String[] { "log", "serialVersionUID" }));

	/**
	 * @param o
	 * @return
	 */
	public static List<Field> getFields(Object o) {
		List<Field> fields = null;
		if (o != null) {
			fields = new ArrayList<Field>();
			for (Field field : getDeclaredFields(o.getClass(), true)) {
				if (!defaultHiddenFields.contains(field.getName())) {
					fields.add(field);
				}
			}
			Collections.sort(fields, new FieldComparator());
		}
		return fields;
	}

	/**
	 * @param o
	 * @return
	 */
	public static List<Field> getVisibleFields(Object o) {
		List<Field> fields = null;
		if (o != null) {
			fields = new ArrayList<Field>();
			for (Field field : getDeclaredFields(o.getClass(), true)) {
					if (!defaultHiddenFields.contains(field.getName())) {
						fields.add(field);
				}
			}
			Collections.sort(fields, new FieldComparator());
		}
		return fields;
	}

	// FIXME : Check if the Set is always order in the same way. (or order it !)
	/**
	 * @param o
	 * @return
	 * @throws EnergyException
	 */
	public static List<Object> getFieldValue(Object o)
			throws ReflectionException {
		List<Object> objects = new ArrayList<Object>();
		if (o != null) {
			for (Field field : Reflect.getFields(o)) {
				try {
					objects.add(getFieldValue(field, o));
				} catch (Exception e) {
					e.printStackTrace();
					throw new ReflectionException(e, e.getLocalizedMessage());
				}
			}
		}
		return objects;
	}

	/**
	 * @param o
	 * @param capitalize
	 * @return
	 * @throws EnergyException
	 */
	public static Map<String, Object> getVisibleFieldValueMap(Object o,
			boolean capitalize) throws ReflectionException {
		Map<String, Object> map = null;
		if (o != null) {
			map = new HashMap<String, Object>();
			for (Field field : Reflect.getVisibleFields(o)) {
				Object oo = Reflect.getFieldValue(field, o);
				if (oo != null) {
					String fieldName = field.getName();
					if (capitalize) {
						fieldName = StringUtils.capitalize(fieldName);
					}
					map.put(fieldName, Reflect.getFieldValue(field, o));
				}
			}
		}
		return map;
	}

	/**
	 * @param o
	 * @return
	 * @throws EnergyException
	 */
	public static Map<String, Object> getVisibleFieldValueMap(Object o)
			throws ReflectionException {
		return getVisibleFieldValueMap(o, false);
	}

	/**
	 * @param o
	 * @return
	 * @throws EnergyException
	 */
	public static List<Object> getVisibleFieldValue(Object o)
			throws ReflectionException {
		return getVisibleFieldValue(o, false);
	}

	/**
	 * Return a List of the values object. If <code>values</code> is true, the
	 * null values are ignored.
	 *
	 * @param o
	 * @param values
	 * @return
	 * @throws EnergyException
	 */
	public static List<Object> getVisibleFieldValue(Object o, boolean values)
			throws ReflectionException {
		List<Object> objects = null;
		if (o != null) {
			objects = new ArrayList<Object>();
			for (Field field : Reflect.getVisibleFields(o)) {
				Object oo = Reflect.getFieldValue(field, o);
				if (values) {
					if (oo != null) {
						objects.add(Reflect.getFieldValue(field, o));
					}
				} else {
					objects.add(Reflect.getFieldValue(field, o));
				}
			}
		}
		return objects;
	}

	/**
	 * @param f
	 * @param o
	 * @return
	 * @throws EnergyException
	 */
	public static Object getFieldValue(Field f, Object o)
			throws ReflectionException {
		Object object = null;
		if (f != null && o != null) {
			try {
				Method getter = getDeclaredMethod(
						"get" + StringUtils.capitalize(f.getName()),
						o.getClass(), true, (Class<?>[]) null);
				if (getter != null) {
					object = getter.invoke(o, (Object[]) null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ReflectionException(e, e.getLocalizedMessage());
			}
		}
		return object;
	}

	/**
	 * @param o
	 * @return
	 */
	public static List<String> getFieldNames(Object o) {
		List<String> fieldNames = null;
		if (o != null) {
			fieldNames = new ArrayList<String>();
			for (Field field : getFields(o)) {
				fieldNames.add(field.getName());
			}
		}
		return fieldNames;
	}

	/**
	 * @param fieldName
	 * @param o
	 * @return
	 * @throws EnergyException
	 */
	public static Field getField(String fieldName, Object o)
			throws ReflectionException {
		return getField(fieldName, o.getClass());
	}

	public static Field getField(String fieldName, Class<?> c)
			throws ReflectionException {
		try {
			return getDeclaredField(fieldName, c, true);
		} catch (SecurityException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		} catch (NoSuchFieldException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		} catch (NullPointerException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		}
	}

	/**
	 * @param o
	 * @return
	 */
	public static List<String> getVisibleFieldNames(Object o) {
		return getVisibleFieldNames(o, false);
	}

	/**
	 * @param o
	 * @param values
	 * @return
	 */
	public static List<String> getVisibleFieldNames(Object o, boolean values) {
		List<String> fieldNames = null;
		if (o != null) {
			fieldNames = new ArrayList<String>();
			for (Field field : getVisibleFields(o)) {
				if (values) {
					try {
						if ((getFieldValue(field, o) != null)) {
							fieldNames.add(field.getName());
						}
					} catch (ReflectionException e) {
					}
				} else {
					fieldNames.add(field.getName());
				}
			}
		} else {
		}
		return fieldNames;
	}

	/*
	 * Attribut la valeur sp�fici�e au champs sp�cifi�, de l'objet sp�cifi� :P.
	 */
	/**
	 * @param f
	 * @param o
	 * @param value
	 * @throws DaoException
	 */
	public static void setValue(Field f, Object o, Object value)
			throws ReflectionException {
		try {
			setValue(f, o, value, true);
		} catch (NullPointerException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		}
	}

	/**
	 * @param fieldName
	 * @param o
	 * @param value
	 * @throws DaoException
	 */
	public static void setValue(String fieldName, Object o, Object value)
			throws ReflectionException {
		try {
			setValue(fieldName, o, value, true);
		} catch (NullPointerException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		}
	}

	/**
	 * @param f
	 * @param o
	 * @param value
	 * @param force
	 * @throws DaoException
	 */
	public static void setValue(Field f, Object o, Object value, boolean force)
			throws ReflectionException {
		try {
			setValue(f.getName(), o, value, force);
		} catch (NullPointerException e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		}
	}

	/**
	 * Sets the value.
	 *
	 * @param fieldName
	 *            the field name
	 * @param o
	 *            the o
	 * @param value
	 *            the value
	 * @param force
	 *            the force
	 * @throws DaoException
	 *             the dao exception
	 */
	public static void setValue(String fieldName, Object o, Object value,
			boolean force) throws ReflectionException {
		try {
			@SuppressWarnings("rawtypes")
			Class[] c1 = new Class[1];
			if (value != null) {
				if (value.getClass() == java.sql.Date.class) {
					c1[0] = Date.class;
				} else if (value.getClass() == Calendar.class
						|| value.getClass() == GregorianCalendar.class) {
					c1[0] = Calendar.class;
				} else {
					c1[0] = value.getClass();
				}
			} else {
				// Let guess it !
				c1[0] = getDeclaredField(fieldName, o, true).getType();
			}
			Method setValue = o.getClass().getMethod(
					"set" + StringUtils.capitalize(fieldName), c1);
			if (setValue != null) {
				if (force || getValue(fieldName, o) == null) {
					// Soit on a forc� le passage (d�faut), soit la valeur
					// actuelle est
					// nulle
					setValue.invoke(o, value);
				}
			}
		} catch (Exception e) {
			throw new ReflectionException(e, e.getLocalizedMessage());
		}
	}

	/**
	 * Gets the value.
	 *
	 * @param field
	 *            the field
	 * @param object
	 *            the object
	 * @return the value
	 * @throws DaoException
	 *             the dao exception
	 */
	public static Object getValue(Field field, Object object)
			throws ReflectionException {
		Object o = null;
		if (field != null) {
			o = getValue(field.getName(), object);
		}
		return o;
	}

	/**
	 * Gets the value.
	 *
	 * @param fieldName
	 *            the field name
	 * @param object
	 *            the object
	 * @return the value
	 * @throws DaoException
	 *             the dao exception
	 */
	public static Object getValue(String fieldName, Object object)
			throws ReflectionException {
		Object o = null;
		if (object != null && fieldName != null) {
			try {
				Method method = object.getClass().getMethod(
						"get" + StringUtils.capitalize(fieldName),
						(Class[]) null);
				if (method.invoke(object, (Object[]) null) != null) {
					o = method.invoke(object, (Object[]) null);
					return o;
				}
			} catch (Exception e) {
				throw new ReflectionException(e, e.getLocalizedMessage());
			}
		}
		return o;
	}

	/**
	 * Get Declared classe for a given classe. If withSupers is false, it
	 * returns only this classes declared fields ; if withSupers is true, it
	 * returns the classes declared fields with the "chain of the super classes"
	 * fields.
	 *
	 * @param c
	 * @param withSupers
	 * @return
	 */
	public static List<Field> getDeclaredFields(Class<?> c, boolean withSupers) {
		List<Field> ret = new ArrayList<Field>();
		ret.addAll(getDeclaredFields(c));
		if (withSupers) {
			Class<?> superClass = c.getSuperclass();
			while ((superClass != null) && (superClass != Object.class)) {
				ret.addAll(getDeclaredFields(superClass));
				superClass = superClass.getSuperclass();
			}
		}
		return ret;
	}

	/**
	 * Get Declared classes for a given Class
	 *
	 * @param c
	 * @return
	 */
	public static List<Field> getDeclaredFields(Class<?> c) {
		List<Field> ret = new ArrayList<Field>();
		for (Field field : c.getDeclaredFields()) {
			ret.add(field);
		}
		return ret;
	}

	public static List<Field> getDeclaredFields(Object o) {
		return getDeclaredFields(o);
	}

	public static Field getDeclaredField(String fieldName, Object o,
			boolean withSupers) throws NoSuchFieldException {
		return getDeclaredField(fieldName, o.getClass(), withSupers);
	}

	public static Field getDeclaredField(String fieldName, Class<?> c,
			boolean withSupers) throws NoSuchFieldException {
		Field ret = getDeclaredField(fieldName, c);
		if (ret == null && withSupers) {
			Class<?> superClass = c.getSuperclass();
			while ((ret == null) && (superClass != null)
					&& (superClass != Object.class)) {
				ret = getDeclaredField(fieldName, superClass);
				superClass = superClass.getSuperclass();
			}
		}
		if (ret == null) {
			throw new NoSuchFieldException(fieldName);
		}
		return ret;
	}

	public static Field getDeclaredField(String fieldName, Class<?> c) {
		Field ret = null;
		try {
			ret = c.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// pass
		}
		return ret;
	}

	public static Method getDeclaredMethod(String methodName, Object o,
			boolean withSupers, Class<?>... parameterTypes)
			throws NoSuchMethodException {
		return getDeclaredMethod(methodName, o.getClass(), withSupers,
				parameterTypes);
	}

	public static Method getDeclaredMethod(String methodName, Class<?> c,
			boolean withSupers, Class<?>... parameterTypes)
			throws NoSuchMethodException {
		Method ret = getDeclaredMethod(methodName, c, parameterTypes);
		if (ret == null && withSupers) {
			Class<?> superClass = c.getSuperclass();
			while ((ret == null) && (superClass != null)
					&& (superClass != Object.class)) {
				ret = getDeclaredMethod(methodName, superClass, parameterTypes);
				superClass = superClass.getSuperclass();
			}
		}
		if (ret == null) {
			throw new NoSuchMethodException(methodName);
		}
		return ret;
	}

	public static Method getDeclaredMethod(String methodName, Class<?> c,
			Class<?>... parameterTypes) {
		Method ret = null;
		try {
			ret = c.getDeclaredMethod(methodName, parameterTypes);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// pass
		}
		return ret;
	}

}
