package com.github.vdemeester.mower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class FileUtilsTest {

	@Test
	public void loadFileByLines_with_an_nonexisting_File_should_throw_an_IOException() {
		try {
			FileUtils.loadFileByLines(new File("invalidfile"));
			fail("Should throw an IOException.");
		} catch (IOException e) {
			assertTrue(e instanceof IOException);
		}
	}

	@Test
	public void loadFileByLines_with_an_existing_File_should_return_the_content_by_lines() throws URISyntaxException {
		try {
			URL url = getClass().getResource("/valid.mow");
			File validFile = new File(url.toURI());
			List<String> expected = Arrays.asList(new String[] { "5 5",
					"1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA" });
			List<String> actual = FileUtils.loadFileByLines(validFile);
			assertEquals(expected, actual);
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}
}
