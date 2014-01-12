package com.github.vdemeester.mower;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Command line client of MowItNow.
 * 
 * @author vincent
 * 
 */
public class MowItNow {

	private InstructionParser parser;
	private Collection<Mower> mowers;
	
	/**
	 * Create a MowItNow using a {@link File}.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public MowItNow(File file) {
		parser = new InstructionParser(file);
	}

	protected void run() throws IOException {
		parser.parse();
		mowers = parser.getMowers();
		for (Mower mower : mowers) {
			for (Instruction instruction : parser.getInstructionsFor(mower)) {
				mower.process(instruction);
			}
		}
	}

	/**
	 * Get the list of position of the {@link Mower}.
	 * 
	 * @return
	 */
	public List<String> getMowerPositions() {
		List<String> positions = new ArrayList<String>();
		for (Mower mower : mowers) {
			positions.add(mower.getPosition());
		}
		return positions;
	}

	public static void main(String[] args) throws IOException {
		File file = loadFileFromArgs(args);
		MowItNow mowItNow = new MowItNow(file);
		mowItNow.run();
		for (String position : mowItNow.getMowerPositions()) {
			System.out.println(position);
		}
	}

	/**
	 * Load the file from the given args
	 * 
	 * @param args
	 * @return a {@link File} object
	 * @throws IllegalArgumentException
	 *             if the number of argument is not 1.
	 */
	private static File loadFileFromArgs(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(
					"MowItNow takes one and only argument, the file containing the instructions.");
		}
		return new File(args[0]);
	}
}