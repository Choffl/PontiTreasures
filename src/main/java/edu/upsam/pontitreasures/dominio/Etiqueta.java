/**
 * 
 */
package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import edu.upsam.pontitreasures.servicios.PaginasServicio;

/**
 * @author ssabariego
 *
 */
@Entity
@Table(name="PT_ETIQUETA")
public class Etiqueta implements Serializable {

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = 7159293502905677251L;
	
	/**
	 * id de persistencia
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
	 * Describe la pista
	 */
	private String descripcion;
	
	/**
	 * El codigo del qr
	 */
	private String codigo;
	
	/**
	 * 
	 */
	@Column(precision=18, scale=14)
	private BigDecimal latitud;
	
	/**
	 * 
	 */
	@Column(precision=18, scale=14)
	private BigDecimal longitud;
	
	/**
	 * 
	 */
	@ManyToOne
	private PaginaJuego paginaCheckinAnonimo;
	
	/**
	 * 
	 */
	@ManyToOne
	private PaginaJuego paginaCheckinIdentificado;
	
	public Etiqueta(){
		
	}

	public Etiqueta(String codigo, String descripcion, BigDecimal latitudValor, BigDecimal longitudValor) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.latitud = latitudValor;
		this.longitud = longitudValor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Etiqueta other = (Etiqueta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the paginaCheckinAnonimo
	 */
	public PaginaJuego getPaginaCheckinAnonimo() {
		return paginaCheckinAnonimo;
	}

	/**
	 * @param paginaCheckinAnonimo the paginaCheckinAnonimo to set
	 */
	public void setPaginaCheckinAnonimo(PaginaJuego paginaCheckinAnonimo) {
		this.paginaCheckinAnonimo = paginaCheckinAnonimo;
	}

	/**
	 * @return the paginaCheckinIdentificado
	 */
	public PaginaJuego getPaginaCheckinIdentificado() {
		return paginaCheckinIdentificado;
	}

	/**
	 * @param paginaCheckinIdentificado the paginaCheckinIdentificado to set
	 */
	public void setPaginaCheckinIdentificado(PaginaJuego paginaCheckinIdentificado) {
		this.paginaCheckinIdentificado = paginaCheckinIdentificado;
	}
	
	

}
