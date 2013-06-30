package edu.upsam.pontitreasures.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * @author ssabariego
 *
 */
@Entity
@Table(name="PT_JUGADOR")
@PrimaryKeyJoinColumn(name="id")
public class Jugador extends Usuario{


	/**
	 * id para la serializaci�n del jugador
	 */
	private static final long serialVersionUID = -5812024677853128188L;
	
	/**
	 * Identificador unico del jugador
	 */
	private String identificador;

	
	/**
	 */
	public Jugador() {
		super();
	}	
	
	/**
	 * @param username
	 * @param email
	 * @param password
	 */
	public Jugador(String username, String email, String password) {
		super(email, password, username, TipoUsuario.JUGADOR);
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	


}
