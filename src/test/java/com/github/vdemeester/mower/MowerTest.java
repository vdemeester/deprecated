package com.github.vdemeester.mower;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class MowerTest {

	private Field field;
	
	@Before
	public void initField() {
		field = new Field(new Coordinates(5, 5));
	}

	@Test
	public void turn_right_modify_only_orientation() {
		Coordinates initialCoordinates = new Coordinates(1, 1);
		Mower mower = new Mower(initialCoordinates, Orientation.N, field);
		mower.process(Instruction.D);
		assertEquals(initialCoordinates, mower.getCoordinates());
		assertEquals(Orientation.E, mower.getOrientation());
	}
	
	@Test
	public void turn_left_modify_only_orientation() {
		Coordinates initialCoordinates = new Coordinates(1, 1);
		Mower mower = new Mower(initialCoordinates, Orientation.N, field);
		mower.process(Instruction.G);
		assertEquals(initialCoordinates, mower.getCoordinates());
		assertEquals(Orientation.W, mower.getOrientation());
	}

	@Test
	public void move_forward_modify_only_coordinates() {
		Coordinates initialCoordinates = new Coordinates(1, 1);
		Mower mower = new Mower(initialCoordinates, Orientation.N, field);
		mower.process(Instruction.A);
		assertEquals(new Coordinates(1, 2), mower.getCoordinates());
		assertEquals(Orientation.N, mower.getOrientation());
	}
	
	@Test
	public void move_forward_outside_field_doesnt_modify_anything() {
		Coordinates initialCoordinates = new Coordinates(5, 5);
		Mower mower = new Mower(initialCoordinates, Orientation.N, field);
		mower.process(Instruction.A);
		assertEquals(initialCoordinates, mower.getCoordinates());
		assertEquals(Orientation.N, mower.getOrientation());
	}
	
	@Test
	public void toString_is_coordinates_and_orientation() {
		String expected = "1 1 N";
		Coordinates initialCoordinates = new Coordinates(1, 1);
		Mower mower = new Mower(initialCoordinates, Orientation.N, field);
		String actual = mower.toString();
		assertEquals(expected, actual);
	}
}
