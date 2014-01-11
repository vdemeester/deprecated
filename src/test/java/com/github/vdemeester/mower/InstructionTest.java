package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.Instruction.R;
import static com.github.vdemeester.mower.Instruction.L;
import static com.github.vdemeester.mower.Instruction.F;
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
		Orientation actual = getNextOrientationFrom(F, Orientation.N);
		assertEquals(expected, actual);
		expected = Orientation.S;
		actual = getNextOrientationFrom(F, Orientation.S);
		assertEquals(expected, actual);
		expected = Orientation.E;
		actual = getNextOrientationFrom(F, Orientation.E);
		assertEquals(expected, actual);
		expected = Orientation.W;
		actual = getNextOrientationFrom(F, Orientation.W);
		assertEquals(expected, actual);
	}

	@Test
	public void Right_of_N_is_E() {
		Orientation expected = Orientation.E;
		Orientation actual = getNextOrientationFrom(R, Orientation.N);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_N_is_W() {
		Orientation expected = Orientation.W;
		Orientation actual = getNextOrientationFrom(L, Orientation.N);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Right_of_S_is_W() {
		Orientation expected = Orientation.W;
		Orientation actual = getNextOrientationFrom(R, Orientation.S);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_N_is_E() {
		Orientation expected = Orientation.E;
		Orientation actual = getNextOrientationFrom(L, Orientation.S);
		assertEquals(expected, actual);
	}

	@Test
	public void Right_of_E_is_S() {
		Orientation expected = Orientation.S;
		Orientation actual = getNextOrientationFrom(R, Orientation.E);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_E_is_N() {
		Orientation expected = Orientation.N;
		Orientation actual = getNextOrientationFrom(L, Orientation.E);
		assertEquals(expected, actual);
	}
	@Test
	public void Right_of_W_is_S() {
		Orientation expected = Orientation.N;
		Orientation actual = getNextOrientationFrom(R, Orientation.W);
		assertEquals(expected, actual);
	}
	
	@Test
	public void Left_of_W_is_N() {
		Orientation expected = Orientation.S;
		Orientation actual = getNextOrientationFrom(L, Orientation.W);
		assertEquals(expected, actual);
	}
}
