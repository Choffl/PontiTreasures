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
	
	private String id;

	private String username;

	private String email;
	
	private String password;
	
	private String passwordRepetido;

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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the passwordRepetido
	 */
	public String getPasswordRepetido() {
		return passwordRepetido;
	}

	/**
	 * @param passwordRepetido the passwordRepetido to set
	 */
	public void setPasswordRepetido(String passwordRepetido) {
		this.passwordRepetido = passwordRepetido;
	}
	

}
