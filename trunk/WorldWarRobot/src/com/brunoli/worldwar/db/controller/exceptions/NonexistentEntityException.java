package com.brunoli.worldwar.db.controller.exceptions;

public class NonexistentEntityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9006880469294041217L;
	public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}