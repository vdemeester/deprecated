package com.github.vdemeester.mower;

import java.io.IOException;

import org.junit.Test;

public class MowItNowTest {

	@Test(expected = IllegalArgumentException.class)
	public void mainWithNoArgsShouldFail() {
		MowItNow.main(new String[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void mainWithTwoOrMoreArgsShouldFail() {
		MowItNow.main(new String[]{ "invalid", "args"});
	}
	
//	@Test(expected = IOException.class)
//	public void mainWithInvalidFileAsArgShouldFail() {
//		MowItNow.main(new String[]{ "invalidfile.txt"});
//	}
}
