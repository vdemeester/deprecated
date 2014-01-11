package com.github.vdemeester.mower;

import static com.github.vdemeester.mower.Instruction.getNextOrientationFrom;
import static com.github.vdemeester.mower.Orientation.getNextCoordinatesFrom;

public class Mower {

	private Coordinates coordinates;
	private Orientation orientation;
	private Field field;
	
	public Mower(Coordinates initialCoordinates, Orientation initialOrientation, Field field) {
		this.coordinates = initialCoordinates;
		this.orientation = initialOrientation;
		this.field = field;
	}
	
	public void process(Instruction instruction) {
		switch (instruction) {
		case R:
		case L:
			turn(instruction);
			break;
		case F:
			move();
		default:
			break;
		}
	}
	
	private void turn(Instruction instruction) {
		orientation = getNextOrientationFrom(instruction, orientation);
	}

	private void move() {
		Coordinates newCoordinates = getNextCoordinatesFrom(orientation, coordinates);
		if (field.isInside(newCoordinates)) {
			coordinates = newCoordinates;
		}
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public String getPosition() {
		return String.format("%s %s", coordinates, orientation);
	}

	@Override
	public String toString() {
		return getPosition();
	}
	
	
}
