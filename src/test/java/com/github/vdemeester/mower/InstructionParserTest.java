package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.InstructionParser.INVALID_FIELD_DEFINITION;
import static com.github.vdemeester.mower.InstructionParser.INVALID_INSTRUCTION_DEFINITION;
import static com.github.vdemeester.mower.InstructionParser.INVALID_MOWER_DEFINITION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.vdemeester.mower.exception.InvalidInstrutionFileException;

public class InstructionParserTest {

	private File file;
	private String validPath = "/valid.mow";
	
	static String[] expectedMowerPositions = new String[] { "1 2 N", "3 3 E" };
	static String[] expectedInstructions = new String[] { "GAGAGAGAA",
			"AADAADADDA" };

	@Before
	public void loadFile() throws URISyntaxException {
		file = loadFile(validPath);
	}

	@Test
	public void parse_with_an_nonexistent_file_should_throw_an_IOException() {
		File nonExistentFile = new File("doesntexists");
		InstructionParser parser = new InstructionParser(nonExistentFile);
		try {
			parser.parse();
			fail("Should throw an IOException.");
		} catch (IOException e) {
			assertTrue(e instanceof IOException);
		}
	}

	@Test
	public void parse_with_complete_invalid_file_should_throw_an_InvalidInstrutionFileException()
			throws URISyntaxException {
		InstructionParser parser = new InstructionParser(
				loadFile("/invalid.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_FIELD_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}
	
	@Test
	public void parse_with_invalid_field_definition_should_throw_an_InvalidInstrutionFileException()
			throws URISyntaxException {
		InstructionParser parser = new InstructionParser(
				loadFile("/invalid_field.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_FIELD_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		} parser = new InstructionParser(
				loadFile("/invalid_field_less.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_FIELD_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		} parser = new InstructionParser(
				loadFile("/invalid_field_more.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_FIELD_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}

	@Test
	public void parse_with_invalid_mower_definition_throw_an_InvalidInstrutionFileException()
			throws URISyntaxException {
		InstructionParser parser = new InstructionParser(
				loadFile("/invalid_mower_less.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_MOWER_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
		parser = new InstructionParser(loadFile("/invalid_mower_more.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_MOWER_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
		parser = new InstructionParser(loadFile("/invalid_mower_letters.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_MOWER_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
		parser = new InstructionParser(loadFile("/invalid_mower_orientation.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_MOWER_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}

	@Test
	public void parse_with_invalid_instructions_throw_an_InvalidInstrutionFileException()
			throws URISyntaxException {
		InstructionParser parser = new InstructionParser(
				loadFile("/invalid_instructions.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_INSTRUCTION_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
		parser = new InstructionParser(
				loadFile("/invalid_instructions_empty.mow"));
		try {
			parser.parse();
			fail("Should throw an InvalidInstructionFileException");
		} catch (InvalidInstrutionFileException e) {
			assertEquals(INVALID_INSTRUCTION_DEFINITION, e.getMessage());
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}

	@Test
	public void parse_with_valid_file_should_work() {
		InstructionParser parser = new InstructionParser(file);
		try {
			parser.parse();
			List<Mower> actualMowers = parser.getMowers();
			assertNotNull(actualMowers);
			assertEquals(2, actualMowers.size());
			for (int i = 0; i < actualMowers.size(); i++) {
				Mower mower = actualMowers.get(i);
				List<Instruction> instructions = parser
						.getInstructionsFor(mower);
				assertNotNull(instructions);
				assertFalse(instructions.isEmpty());
				assertEquals(expectedMowerPositions[i], mower.getPosition());
				assertEquals(expectedInstructions[i].length(), instructions.size());
				// FIXME add test for the real Instructions
			}
		} catch (IOException e) {
			fail("Should not throw an IOException.");
		}
	}

	static File loadFile(String path) throws URISyntaxException {
		URL url = InstructionParserTest.class.getResource(path);
		return new File(url.toURI());
	}
}
