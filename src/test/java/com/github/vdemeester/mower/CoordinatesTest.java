package com.github.vdemeester.mower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(BlockJUnit4ClassRunner.class)
public class CoordinatesTest {

	@Test
	public void default_coordinates_is_0_0() {
		Coordinates expected = new Coordinates(0, 0);
		Coordinates actual = new Coordinates();
		assertEquals(expected, actual);
	}
	
	@Test
	public void coordinates_hashCode_is_composed_of_x_and_y_hashCodes() {
		Integer x = 1;
		Integer y = 1;
		int expectedHashCode = 31 * (31 + x.hashCode()) + y.hashCode();
		int actualHashCode = new Coordinates(x, y).hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void coordinates_with_same_x_and_y_are_equals() {
		Coordinates coordinates1 = new Coordinates(1,1);
		Coordinates coordinates2 = new Coordinates(1, 1);
		assertNotSame(coordinates1, coordinates2);
		assertEquals(coordinates1, coordinates2);
	}
	
	@Test
	public void coordinates_with_different_x_or_y_are_different() {
		Coordinates coordinates1 = new Coordinates(1, 2);
		Coordinates coordinates2 = new Coordinates(1, 1);
		assertNotSame(coordinates1, coordinates2);
		assertNotEquals(coordinates1, coordinates2);
		coordinates1 = new Coordinates(2, 1);
		coordinates2 = new Coordinates(1, 1);
		assertNotSame(coordinates1, coordinates2);
		assertNotEquals(coordinates1, coordinates2);
		coordinates1 = new Coordinates(2, 2);
		coordinates2 = new Coordinates(1, 1);
		assertNotSame(coordinates1, coordinates2);
		assertNotEquals(coordinates1, coordinates2);
	}
	
	@Test
	public void coordinates_toString_return_x_and_y_separate_by_space() {
		Coordinates coordinates = new Coordinates();
		String expected = "0 0";
		assertEquals(expected, coordinates.toString());
		coordinates = new Coordinates(1, 1);
		expected = "1 1";
		assertEquals(expected, coordinates.toString());
	}
}
