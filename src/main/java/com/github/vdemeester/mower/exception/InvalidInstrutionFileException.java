package com.github.vdemeester.mower.exception;

import com.github.vdemeester.mower.Instruction;

/**
 * Exception thrown when the {@link Instruction} file is invalid.
 * 
 * @author vincent
 * 
 */
public class InvalidInstrutionFileException extends RuntimeException {

	private static final long serialVersionUID = -7957490046040761949L;

	public InvalidInstrutionFileException() {
		super();
	}

	public InvalidInstrutionFileException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidInstrutionFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInstrutionFileException(String message) {
		super(message);
	}

	public InvalidInstrutionFileException(Throwable cause) {
		super(cause);
	}

}
