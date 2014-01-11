package com.github.vdemeester.mower;

public class Field {
	
	private final Coordinates minCoordinates = new Coordinates();
	private final Coordinates maxCoordinates;
	
	public Field(Coordinates maxCoordinates) {
		this.maxCoordinates = maxCoordinates;
	}

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
 