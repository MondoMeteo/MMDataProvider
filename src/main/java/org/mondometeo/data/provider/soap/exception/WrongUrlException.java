/**
 * 
 */
package org.mondometeo.data.provider.soap.exception;

import org.mondometeo.common.exception.MMException;


public class WrongUrlException extends MMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9117838596221422663L;

	/**
	 * 
	 */
	public WrongUrlException() {
		super();
	}

	/**
	 * @param message
	 */
	public WrongUrlException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public WrongUrlException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongUrlException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
