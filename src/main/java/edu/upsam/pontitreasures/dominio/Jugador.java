package edu.upsam.pontitreasures.dominio;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	 * id para la serializacion del jugador
	 */
	private static final long serialVersionUID = -5812024677853128188L;
	
	/**
	 * Identificador unico del jugador
	 */
	private String identificador;
	
	/**
	 * Caza que el jugador
	 */
	@ManyToOne
	private CazaTesoro cazaTesoroActiva;
	
	/**
	 * Historico de cazas
	 */
	@ManyToMany
	@JoinTable(name="PT_JUG_CAZA", joinColumns={@JoinColumn(name="jug_id", referencedColumnName="id")}, 
			inverseJoinColumns={@JoinColumn(name="caza_id", referencedColumnName="id")})
	private Collection<CazaTesoro> historicoCazas = new HashSet<CazaTesoro>();

	
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

	/**
	 * @return the cazaTesoroActiva
	 */
	public CazaTesoro getCazaTesoroActiva() {
		return cazaTesoroActiva;
	}

	/**
	 * @param cazaTesoroActiva the cazaTesoroActiva to set
	 */
	public void setCazaTesoroActiva(CazaTesoro cazaTesoroActiva) {
		this.cazaTesoroActiva = cazaTesoroActiva;
	}

	/**
	 * @return the historicoCazas
	 */
	public Collection<CazaTesoro> getHistoricoCazas() {
		return historicoCazas;
	}

	/**
	 * @param historicoCazas the historicoCazas to set
	 */
	public void setHistoricoCazas(Collection<CazaTesoro> historicoCazas) {
		this.historicoCazas = historicoCazas;
	}
	
	
	


}
