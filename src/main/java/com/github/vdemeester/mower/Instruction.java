package com.github.vdemeester.mower;

import java.util.Enumeration;

/**
 * {@link Enumeration} the represent the possible instruction for a
 * {@link Mower}.
 * 
 * <ul>
 * <li><strong>R</strong> for Right</li>
 * <li><strong>L</strong> for Left</li>
 * <li><strong>F</strong> for Forward</li>
 * </ul>
 * 
 * @author vincent
 * 
 */
public enum Instruction {
	R, L, F;

	/**
	 * Get an new {@link Orientation} from an {@link Instruction} and the
	 * previous {@link Orientation}.
	 * 
	 * @param instruction
	 *            the {@link Instruction} to follow.
	 * @param orientation
	 *            the previous/current {@link Orientation}.
	 * @return a new {@link Orientation}.
	 */
	public static Orientation getNextOrientationFrom(Instruction instruction,
			Orientation orientation) {
		switch (orientation) {
		case N:
			if (instruction == R)
				return Orientation.E;
			if (instruction == L)
				return Orientation.W;
		case S:
			if (instruction == R)
				return Orientation.W;
			if (instruction == L)
				return Orientation.E;
		case E:
			if (instruction == R)
				return Orientation.S;
			if (instruction == L)
				return Orientation.N;
		case W:
			if (instruction == R)
				return Orientation.N;
			if (instruction == L)
				return Orientation.S;
		default:
			return orientation;
		}
	}
}
