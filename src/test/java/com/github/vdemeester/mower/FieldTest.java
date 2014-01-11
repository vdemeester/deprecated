package com.github.vdemeester.mower;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(BlockJUnit4ClassRunner.class)
public class FieldTest {

	Field field;
	
	@Before
	public void initField() {
		field = new Field(new Coordinates(5, 5));
	}
	
	@Test
	public void coordinate_4_4_is_inside_field() {
		Coordinates coordinates = new Coordinates(4, 4);
		assertTrue(field.isInside(coordinates));
	}

	@Test
	public void coordinate_0_0_is_inside_field() {
		Coordinates coordinates = new Coordinates(0, 0);
		assertTrue(field.isInside(coordinates));
	}

	@Test
	public void coordinate_5_5_is_inside_field() {
		Coordinates coordinates = new Coordinates(5, 5);
		assertTrue(field.isInside(coordinates));
	}

	@Test
	public void coordinate_6_5_is_inside_field() {
		Coordinates coordinates = new Coordinates(6, 5);
		assertFalse(field.isInside(coordinates));
	}

	@Test
	public void coordinate_5_6_is_inside_field() {
		Coordinates coordinates = new Coordinates(5, 6);
		assertFalse(field.isInside(coordinates));
	}
	
	@Test
	public void coordinate_6_6_is_inside_field() {
		Coordinates coordinates = new Coordinates(6, 6);
		assertFalse(field.isInside(coordinates));
	}
	
	@Test
	public void coordinate_0_minus1_is_inside_field() {
		Coordinates coordinates = new Coordinates(0, -1);
		assertFalse(field.isInside(coordinates));
	}

	@Test
	public void coordinate_minus1_0_is_inside_field() {
		Coordinates coordinates = new Coordinates(-1, 0);
		assertFalse(field.isInside(coordinates));
	}
	
	@Test
	public void coordinate_minus1_minus1_is_inside_field() {
		Coordinates coordinates = new Coordinates(-1, -1);
		assertFalse(field.isInside(coordinates));
	}
}
