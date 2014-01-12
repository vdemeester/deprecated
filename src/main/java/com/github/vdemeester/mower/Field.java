package com.github.vdemeester.mower;

/**
 * Representation of a field where {@link Mower}s move and do their job. A field
 * is bound by 2 coordinates : down-left, and up-right.
 * 
 * @author vincent
 * 
 */
public class Field {

	private final Coordinates minCoordinates = new Coordinates();
	private final Coordinates maxCoordinates;

	/**
	 * Create a field with the up-right coordinates. The down-left coordinates
	 * is, by default (0,0).
	 * 
	 * @param maxCoordinates
	 */
	public Field(Coordinates maxCoordinates) {
		this.maxCoordinates = maxCoordinates;
	}

	/**
	 * Check is the given {@link Coordinates} is inside the {@link Field} or
	 * not.
	 * 
	 * @param newCoordinates
	 *            {@link Coordinates} to check
	 * @return true if inside, false otherwise.
	 */
	public boolean isInside(Coordinates newCoordinates) {
		boolean inside = true;
		int x = newCoordinates.getX();
		int y = newCoordinates.getY();
		inside = inside && (x >= minCoordinates.getX());
		inside = inside && (x <= maxCoordinates.getX());
		inside = inside && (y >= minCoordinates.getY());
		inside = inside && (y <= maxCoordinates.getY());
		return inside;
	}

}
