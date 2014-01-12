package com.github.vdemeester.mower;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.vdemeester.mower.exception.InvalidInstrutionFileException;

/**
 * Parser for {@link MowItNow}.
 * 
 * @author vincent
 * 
 */
public class InstructionParser {

	protected static final String INVALID_FIELD_DEFINITION = "Invalid definition of the Field, should be two numbers separated by a space.";
	protected static final String INVALID_MOWER_DEFINITION = "Invalid definition of the Mower, should be two numbers and a letter separated by a space.";
	protected static final String INVALID_INSTRUCTION_DEFINITION = "Invalid definition of the Instructions, should contains only the letter R, G and F.";

	private File instructionFile;
	private Map<Mower, List<Instruction>> instructionsByMower;

	/**
	 * Create a parser from a File.
	 * 
	 * @param file
	 */
	public InstructionParser(File file) {
		this.instructionFile = file;
	}

	/**
	 * 
	 * @throws IOException
	 *             if the file does not exists or there is problems when reading
	 *             it.
	 */
	public void parse() throws IOException {
		List<String> instructionLines = FileUtils
				.loadFileByLines(instructionFile);
		Field field = readFieldFrom(instructionLines.get(0));
		instructionsByMower = readAndPopulateMowerAndInstructions(
				instructionLines.subList(1, instructionLines.size()), field);
	}

	private Field readFieldFrom(String line) {
		String[] fieldCoordinates = line.split(" ");
		if (fieldCoordinates.length != 2) {
			throw new InvalidInstrutionFileException(INVALID_FIELD_DEFINITION);
		}
		try {
			Integer x = Integer.valueOf(fieldCoordinates[0]);
			Integer y = Integer.valueOf(fieldCoordinates[1]);
			return new Field(new Coordinates(x, y));
		} catch (NumberFormatException e) {
			throw new InvalidInstrutionFileException(INVALID_FIELD_DEFINITION);
		}
	}

	private Map<Mower, List<Instruction>> readAndPopulateMowerAndInstructions(
			List<String> lines, Field field) {
		Map<Mower, List<Instruction>> map = new LinkedHashMap<Mower, List<Instruction>>();
		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			String mowerLine = (String) it.next();
			Mower mower = createMowerFrom(mowerLine, field);
			String instructionsLine = (String) it.next();
			List<Instruction> instructions = getInstructionFrom(instructionsLine);
			map.put(mower, instructions);
		}
		return map;
	}

	private Mower createMowerFrom(String mowerLine, Field field) {
		String[] elements = mowerLine.split(" ");
		if (elements.length != 3) {
			throw new InvalidInstrutionFileException(INVALID_MOWER_DEFINITION);
		}
		try {
			Integer x = Integer.valueOf(elements[0]);
			Integer y = Integer.valueOf(elements[1]);
			Orientation orientation = Orientation.valueOf(elements[2]);
			return new Mower(new Coordinates(x, y), orientation, field);
		} catch (NumberFormatException e) {
			throw new InvalidInstrutionFileException(INVALID_MOWER_DEFINITION);
		} catch (IllegalArgumentException e) {
			throw new InvalidInstrutionFileException(INVALID_MOWER_DEFINITION);
		}
	}

	private List<Instruction> getInstructionFrom(String instructionsLine) {
		if (instructionsLine.isEmpty()) {
			throw new InvalidInstrutionFileException(
					INVALID_INSTRUCTION_DEFINITION);
		}
		try {
			List<Instruction> instructions = new LinkedList<Instruction>();
			for (char character : instructionsLine.toCharArray()) {
				instructions.add(Instruction.valueOf("" + character));
			}
			return instructions;
		} catch (IllegalArgumentException e) {
			throw new InvalidInstrutionFileException(
					INVALID_INSTRUCTION_DEFINITION);
		}
	}

	public List<Mower> getMowers() {
		return new LinkedList<Mower>(instructionsByMower.keySet());
	}

	public List<Instruction> getInstructionsFor(Mower mower) {
		return instructionsByMower.get(mower);
	}
}
