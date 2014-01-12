package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.Instruction.D;
import static com.github.vdemeester.mower.Instruction.G;
import static com.github.vdemeester.mower.Instruction.A;
import static com.github.vdemeester.mower.Instruction.getNextOrientationFrom;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(BlockJUnit4ClassRunner.class)
public class InstructionTest {

	@Test
	public void Forward_of_any_Orientation_is_itself() {
		Orientation expected = Orientation.N;
		Orientation actual = getNextOrientationFrom(A, Orientation.N);
		assertEquals(expected, actual);
		expected = Orientation.S;
		actual = getNextOrientationFrom(A, Orientation.S);
		assertEquals(expected, actual);
		expected = Orientation.E;
		actual = getNextOrientationFrom(A, Orientation.E);
		assertEquals(expected, actual);
		expected = Orientation.W;
		actual = getNextOrientationFrom(A, Orientation.W);
		assertEquals(expected, actual);
	}

	@Test
	public void Right_of_N_is_E() {
		Orientation expected = Orientation.E;
		Orientation actual = getNextOrientationFrom(D, Orientation.N);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_N_is_W() {
		Orientation expected = Orientation.W;
		Orientation actual = getNextOrientationFrom(G, Orientation.N);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Right_of_S_is_W() {
		Orientation expected = Orientation.W;
		Orientation actual = getNextOrientationFrom(D, Orientation.S);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_N_is_E() {
		Orientation expected = Orientation.E;
		Orientation actual = getNextOrientationFrom(G, Orientation.S);
		assertEquals(expected, actual);
	}

	@Test
	public void Right_of_E_is_S() {
		Orientation expected = Orientation.S;
		Orientation actual = getNextOrientationFrom(D, Orientation.E);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_E_is_N() {
		Orientation expected = Orientation.N;
		Orientation actual = getNextOrientationFrom(G, Orientation.E);
		assertEquals(expected, actual);
	}
	@Test
	public void Right_of_W_is_S() {
		Orientation expected = Orientation.N;
		Orientation actual = getNextOrientationFrom(D, Orientation.W);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_W_is_N() {
		Orientation expected = Orientation.S;
		Orientation actual = getNextOrientationFrom(G, Orientation.W);
		assertEquals(expected, actual);
	}
}
