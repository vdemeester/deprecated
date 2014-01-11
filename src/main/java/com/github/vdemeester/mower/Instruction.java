package com.github.vdemeester.mower;

public enum Instruction {
	R, L, F;

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
