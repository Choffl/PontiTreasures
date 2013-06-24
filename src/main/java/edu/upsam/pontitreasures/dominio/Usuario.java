/**
 * 
 */
package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author ssabariego
 *
 */
@Entity
@Table(name="PT_USUARIO")
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario implements Serializable {

	/**
	 * id para la serializacion del usuario
	 */
	private static final long serialVersionUID = -354965486046312575L;
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * version de la entidad persistente: necesario para evitar problemas de concurrencia
	 */
	@Version
	private int version;
	
	/**
	 * 
	 */
	private String email;
	
	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 */
	private String username;
	
	/**
	 * 
	 */
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
		
	/**
	 * 
	 */
	public Usuario() {
		super();
	}


	/**
	 * @param email
	 * @param password
	 * @param tipoUsuario
	 */
	public Usuario(String email, String password, String username, TipoUsuario tipoUsuario) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.tipo = tipoUsuario;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
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
	 * @return the tipo
	 */
	public TipoUsuario getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	

}
