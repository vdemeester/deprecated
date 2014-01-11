package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.Orientation.E;
import static com.github.vdemeester.mower.Orientation.N;
import static com.github.vdemeester.mower.Orientation.S;
import static com.github.vdemeester.mower.Orientation.W;
import static com.github.vdemeester.mower.Orientation.getNextCoordinatesFrom;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class OrientationTest {

	Coordinates initialCoordinates;

	@Before
	public void initCoordinates() {
		this.initialCoordinates = new Coordinates(2, 2);
	}

	@Test
	public void getNextCoordinates_with_N_increment_Y() {
		Coordinates expected = new Coordinates(2, 3);
		Coordinates actual = getNextCoordinatesFrom(N, initialCoordinates);
		assertEquals(expected, actual);
	}

	@Test
	public void getNextCoordinates_with_S_decrement_Y() {
		Coordinates expected = new Coordinates(2, 1);
		Coordinates actual = getNextCoordinatesFrom(S, initialCoordinates);
		assertEquals(expected, actual);
	}

	@Test
	public void getNextCoordinates_with_E_increment_X() {
		Coordinates expected = new Coordinates(3, 2);
		Coordinates actual = getNextCoordinatesFrom(E, initialCoordinates);
		assertEquals(expected, actual);
	}

	@Test
	public void getNextCoordinates_with_W_decrement_X() {
		Coordinates expected = new Coordinates(1, 2);
		Coordinates actual = getNextCoordinatesFrom(W, initialCoordinates);
		assertEquals(expected, actual);
	}
}
