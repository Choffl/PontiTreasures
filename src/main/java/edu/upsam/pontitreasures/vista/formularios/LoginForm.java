package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Formulario de Login del usuario
 */
public class LoginForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = 6240594713673504925L;
	
	/*
	 * el email de usuario
	 */
	private String email;

	/*
	 * la password del usuario
	 */
	private String password;

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
	
	
	
}
