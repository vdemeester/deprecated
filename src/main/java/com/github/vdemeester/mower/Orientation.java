package com.github.vdemeester.mower;

public enum Orientation {
	N(0, 1), S(0, -1), E(1, 0), W(-1, 0);

	private final int incrementX;
	private final int incrementY;

	Orientation(int incrementX, int incrementY) {
		this.incrementX = incrementX;
		this.incrementY = incrementY;
	}
	
	public static Coordinates getNextCoordinatesFrom(Orientation orientation, Coordinates initialCoordinates) {
		int x = initialCoordinates.getX() + orientation.incrementX;
		int y = initialCoordinates.getY() + orientation.incrementY;
		return new Coordinates(x, y);
	}

}
