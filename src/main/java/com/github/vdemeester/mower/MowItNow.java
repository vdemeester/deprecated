package com.github.vdemeester.mower;

import java.io.File;

/**
 * Command line client of MowItNow.
 * 
 * @author vincent
 * 
 */
public class MowItNow {
	
	final File file;
	
	public MowItNow(File file) {
		this.file = file;
	}

	private void run() {
		
	}
	
	public String[] getMowerPositions() {
		return new String[]{};
	}
	
	public static void main(String[] args) {
		File file = loadFileFromArgs(args);
		MowItNow mowItNow = new MowItNow(file);
		mowItNow.run();
		for (String position : mowItNow.getMowerPositions()) {
			System.out.println(position);
		}
	}

	private static File loadFileFromArgs(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("MowItNow takes one and only argument, the file containing the instructions.");
		}
		return new File(args[0]);
	}
}
