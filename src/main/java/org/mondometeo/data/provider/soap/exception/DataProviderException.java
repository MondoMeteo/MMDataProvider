/**
 * 
 */
package org.mondometeo.data.provider.soap.exception;

import org.mondometeo.common.exception.MMException;


public class DataProviderException extends MMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587862320498262535L;

	/**
	 * 
	 */
	public DataProviderException() {
		super();
	}

	/**
	 * @param message
	 */
	public DataProviderException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataProviderException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataProviderException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
