package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PT_CHECKIN")
public class Checkin implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = -440284676899711913L;
	
	/**
	 * id de persistencia.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Boolean premio;
	
	private Boolean mencion;
	

	@Column(precision=18, scale=14)
	private BigDecimal latitud;
	
	@Column(precision=18, scale=14)
	private BigDecimal longitud;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
	private Etiqueta etiqueta;
	
	@ManyToOne
	private CazaTesoro cazaTesoro;
	
	@ManyToOne
	private Jugador jugador;

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((etiqueta == null) ? 0 : etiqueta.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((jugador == null) ? 0 : jugador.hashCode());
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
		Checkin other = (Checkin) obj;
		if (etiqueta == null) {
			if (other.etiqueta != null)
				return false;
		} else if (!etiqueta.equals(other.etiqueta))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (jugador == null) {
			if (other.jugador != null)
				return false;
		} else if (!jugador.equals(other.jugador))
			return false;
		return true;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the etiqueta
	 */
	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	/**
	 * @param etiqueta the etiqueta to set
	 */
	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * @return the cazaTesoro
	 */
	public CazaTesoro getCazaTesoro() {
		return cazaTesoro;
	}

	/**
	 * @param cazaTesoro the cazaTesoro to set
	 */
	public void setCazaTesoro(CazaTesoro cazaTesoro) {
		this.cazaTesoro = cazaTesoro;
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the premio
	 */
	public Boolean getPremio() {
		return premio;
	}

	/**
	 * @param premio the premio to set
	 */
	public void setPremio(Boolean premio) {
		this.premio = premio;
	}

	/**
	 * @return the mencion
	 */
	public Boolean getMencion() {
		return mencion;
	}

	/**
	 * @param mencion the mencion to set
	 */
	public void setMencion(Boolean mencion) {
		this.mencion = mencion;
	}


	/**
	 * @return the latitud
	 */
	public BigDecimal getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public BigDecimal getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	
	
	

}
