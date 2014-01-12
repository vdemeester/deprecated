package com.github.vdemeester.mower;

import java.util.Enumeration;

/**
 * {@link Enumeration} representing the orientation of a {@link Mower} (or
 * something else). Each element comes with a tuple of for incrementing
 * {@link Coordinates}.
 * 
 * <ul>
 * <li><strong>N</strong> for North</li>
 * <li><strong>S</strong> for South</li>
 * <li><strong>E</strong> for East</li>
 * <li><strong>W</strong> for West</li>
 * </ul>
 * 
 * @author vincent
 * 
 */
public enum Orientation {
	N(0, 1), S(0, -1), E(1, 0), W(-1, 0);

	private final int incrementX;
	private final int incrementY;

	Orientation(int incrementX, int incrementY) {
		this.incrementX = incrementX;
		this.incrementY = incrementY;
	}

	/**
	 * Get new {@link Coordinates} from an {@link Orientation} and the previous
	 * {@link Coordinates}.
	 * 
	 * @param orientation
	 *            the {@link Orientation}.
	 * @param initialCoordinates
	 *            the previous/current {@link Coordinates}.
	 * @return a new {@link Coordinates}
	 */
	public static Coordinates getNextCoordinatesFrom(Orientation orientation,
			Coordinates initialCoordinates) {
		int x = initialCoordinates.getX() + orientation.incrementX;
		int y = initialCoordinates.getY() + orientation.incrementY;
		return new Coordinates(x, y);
	}

}
