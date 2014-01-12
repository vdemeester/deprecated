package com.github.vdemeester.mower;

import java.io.File;
import java.io.IOException;

/**
 * Command line client of MowItNow.
 * 
 * @author vincent
 * 
 */
public class MowItNow {

	final File file;

	/**
	 * Create a MowItNow using a {@link File}.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public MowItNow(File file) {
		this.file = file;
	}

	private void run() {

	}

	/**
	 * Get the list of position of the {@link Mower}.
	 * 
	 * @return
	 */
	public String[] getMowerPositions() {
		return new String[] {};
	}

	public static void main(String[] args) {
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