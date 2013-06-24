/**
 * 
 */
package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Una caza es una instancia de juego sobre un circuito de etiquetas.
 * Habra un gestor de la caza o juego y unas fechas donde el juego esta activo.
 * 
 * @author ssabariego
 */
@Entity
@Table(name="PT_CAZA")
public class CazaTesoro implements Serializable {

	/**
	 * id para la serializacion de una instancia de juego o caza.
	 */
	private static final long serialVersionUID = 7524189917694522709L;
	
	/**
	 * id de persistencia.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * Nombre de la instancia del juego
	 */
	private String nombre;
	
	/**
	 * Circuito sobre el que se realiza la caza del tesoro.
	 */
	@ManyToOne
	private Circuito circuito;
	
	/**
	 * Usuario gestor del juego.
	 */
	@ManyToOne
	private Usuario gestor;
	
	/**
	 * Fecha de inicio del juego.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	/**
	 * Fecha tope para poder participar.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		CazaTesoro other = (CazaTesoro) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the circuito
	 */
	public Circuito getCircuito() {
		return circuito;
	}

	/**
	 * @param circuito the circuito to set
	 */
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	/**
	 * @return the gestor
	 */
	public Usuario getGestor() {
		return gestor;
	}

	/**
	 * @param gestor the gestor to set
	 */
	public void setGestor(Usuario gestor) {
		this.gestor = gestor;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	

}
