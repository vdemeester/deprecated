package com.github.vdemeester.mower;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Static class for managing {@link File}s.
 * 
 * @author vincent
 * 
 */
public final class FileUtils {

	private FileUtils() {
	}

	/**
	 * Load the given {@link File} as text and return a {@link List} of its
	 * lines.
	 * 
	 * @param file
	 *            the file to load.
	 * @return a {@link List} of {@link String}.
	 * @throws IOException
	 *             if there is a problem while opening or reading the file.
	 */
	public static List<String> loadFileByLines(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		scanner.close();
		return lines;
	}

}
