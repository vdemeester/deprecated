package com.github.vdemeester.mower;

import java.util.Enumeration;

/**
 * {@link Enumeration} the represent the possible instruction for a
 * {@link Mower}.
 * 
 * <ul>
 * <li><strong>D</strong> for Right (Droite).</li>
 * <li><strong>G</strong> for Left (Gauche).</li>
 * <li><strong>A</strong> for Forward (Avancer).</li>
 * </ul>
 * 
 * @author vincent
 * 
 */
public enum Instruction {
	D, G, A;

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
			if (instruction == D)
				return Orientation.E;
			if (instruction == G)
				return Orientation.W;
		case S:
			if (instruction == D)
				return Orientation.W;
			if (instruction == G)
				return Orientation.E;
		case E:
			if (instruction == D)
				return Orientation.S;
			if (instruction == G)
				return Orientation.N;
		case W:
			if (instruction == D)
				return Orientation.N;
			if (instruction == G)
				return Orientation.S;
		default:
			return orientation;
		}
	}
}
