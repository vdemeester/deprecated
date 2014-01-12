package com.github.vdemeester.mower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.github.vdemeester.mower.exception.InvalidInstrutionFileException;

public class MowItNowTest {

	/**
	 * Test from exercice.
	 */
	@Test
	public void mowItNow_with_valid_file_work() {
		MowItNow mowItNow = new MowItNow(new File("src/test/resources/valid.mow"));
		try {
			mowItNow.run();
			assertNotNull(mowItNow.getMowerPositions());
			assertEquals(2, mowItNow.getMowerPositions().size());
			assertEquals("1 3 N", mowItNow.getMowerPositions().get(0));
			assertEquals("5 1 E", mowItNow.getMowerPositions().get(1));
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void main_with_no_args_should_fail() {
		try {
			MowItNow.main(new String[]{});
		} catch (IOException e) {
			fail("Should not have thrown an IOException.");
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void main_with_two_or_more_args_should_fail() {
		try {
			MowItNow.main(new String[]{ "invalid", "args"});
		} catch (IOException e) {
			fail("Should not have thrown an IOException.");
		}
	}

	@Test
	public void main_with_non_existent_file_as_arg_should_fail() {
		try {
			MowItNow.main(new String[]{ "doesntexists.txt"});
			fail("Should throw an IOException.");
		} catch (IOException e) {
			assertTrue(e instanceof IOException);
		}
	}

	@Test
	public void main_with_invalid_file_as_arg_should_fail() {
		try {
			MowItNow.main(new String[]{ "src/test/resources/invalid.mow"});
			fail("Should throw an InvalidInstrutionFileException.");
		} catch (InvalidInstrutionFileException e) {
			assertTrue(e instanceof InvalidInstrutionFileException);
		} catch (IOException e) {
			assertTrue(e instanceof IOException);
		}
	}

	@Test
	public void main_with_valid_file_as_arg_should_work() {
		try {
			MowItNow.main(new String[]{ "src/test/resources/valid.mow"});
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}

}
