package edu.upsam.pontitreasures.vista.controladores.excepciones;

/**
 * @author ssabariego
 *
 */
public class FalloServidorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -523720771632790641L;
	
	public FalloServidorException() {
	}
	
	public FalloServidorException(String message) {
		super(message);
	}

}
