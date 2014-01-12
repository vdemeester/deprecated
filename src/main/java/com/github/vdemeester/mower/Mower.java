package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.Instruction.getNextOrientationFrom;
import static com.github.vdemeester.mower.Orientation.getNextCoordinatesFrom;

/**
 * Representation of a {@link Mower} and its possiblities.
 * 
 * <p>
 * A {@link Mower} is evolving on a {@link Field}, and has {@link Coordinates}
 * and {@link Orientation}.
 * </p>
 * 
 * @author vincent
 * 
 */
public class Mower {

	private Coordinates coordinates;
	private Orientation orientation;
	private Field field;

	/**
	 * Create a {@link Mower} with initial {@link Coordinates} and
	 * {@link Orientation}, in a {@link Field}.
	 * 
	 * @param initialCoordinates
	 * @param initialOrientation
	 * @param field
	 */
	public Mower(Coordinates initialCoordinates,
			Orientation initialOrientation, Field field) {
		this.coordinates = initialCoordinates;
		this.orientation = initialOrientation;
		this.field = field;
	}

	/**
	 * Process a given {@link Instruction}, resulting in either turning or
	 * moving (forward).
	 * 
	 * @param instruction
	 */
	public void process(Instruction instruction) {
		switch (instruction) {
		case D:
		case G:
			turn(instruction);
			break;
		case A:
			move();
		default:
			break;
		}
	}

	/**
	 * Turn the {@link Mower} using the {@link Instruction}.
	 * 
	 * @param instruction
	 *            the "turning" {@link Instruction}.
	 */
	private void turn(Instruction instruction) {
		orientation = getNextOrientationFrom(instruction, orientation);
	}

	/**
	 * Move the {@link Mower} forward if possible (inside the field).
	 */
	private void move() {
		Coordinates newCoordinates = getNextCoordinatesFrom(orientation,
				coordinates);
		if (field.isInside(newCoordinates)) {
			coordinates = newCoordinates;
		}
	}

	/**
	 * @return the current {@link Coordinates} of the {@link Mower}.
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @return the current {@link Orientation} of the {@link Mower}.
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @return the position of the {@link Mower} as a String (Coordinates,
	 *         orientation)
	 */
	public String getPosition() {
		return String.format("%s %s", coordinates, orientation);
	}

	@Override
	public String toString() {
		return getPosition();
	}

}
