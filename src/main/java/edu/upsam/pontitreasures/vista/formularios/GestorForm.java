package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;

/**
 * @author ssabariego
 *
 */
public class GestorForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = -6265296881580304353L;
	

	private String username;

	private String email;
	
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

}
