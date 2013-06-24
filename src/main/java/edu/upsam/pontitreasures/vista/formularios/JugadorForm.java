/**
 * 
 */
package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ssabariego
 *
 */
public class JugadorForm implements Serializable{

	/**
	 * id para la serialización
	 */
	private static final long serialVersionUID = 8527110198840547943L;
	
	/**
	 * Nombre de usuario
	 */
	private String username;
	

	/*
	 * el email de usuario
	 */
	private String email;

	/*
	 * la password del usuario
	 */
	private String password;
	
	/*
	 * la password del usuario
	 */
	private String passwordRepetido;

	/**
	 * @return the email
	 */
	@NotNull
	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message="La direccion de mail no tiene un formato v�lido")
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
	@NotNull
	@Size(min=8, max=20, message="La contrase�a debe tener un m�nimo de 8 caracteres")
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
	 * @return the username
	 */
	@NotBlank
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
	 * @return the passwordRepetida
	 */
	@NotNull
	public String getPasswordRepetido() {
		return passwordRepetido;
	}

	/**
	 * @param passwordRepetida the passwordRepetida to set
	 */
	public void setPasswordRepetido(String passwordRepetido) {
		this.passwordRepetido = passwordRepetido;
	}
	
	

}
