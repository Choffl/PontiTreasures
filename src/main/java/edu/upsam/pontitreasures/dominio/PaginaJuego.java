package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author ssabariego
 *
 */
@Entity
@Table(name="PT_PAGINA")
public class PaginaJuego implements Serializable {

	/**
	 * id para la serializacion de una pagina del juego
	 */
	private static final long serialVersionUID = -1266415627353084594L;
	
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
	private String nombre;
	
	/**
	 * 
	 */
	private String descripcion;
	
	/**
	 * 
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] paginaHtml;

	/**
	 * 
	 */
	public PaginaJuego() {
	}	

	/**
	 * @param nombre
	 * @param descripcion
	 */
	public PaginaJuego(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paginaHtml == null) ? 0 : paginaHtml.hashCode());
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
		PaginaJuego other = (PaginaJuego) obj;
		if (paginaHtml == null) {
			if (other.paginaHtml != null)
				return false;
		} else if (!paginaHtml.equals(other.paginaHtml))
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
	 * @return the paginaHtml
	 */
	public byte[] getPaginaHtml() {
		return paginaHtml;
	}

	/**
	 * @param paginaHtml the paginaHtml to set
	 */
	public void setPaginaHtml(byte[] paginaHtml) {
		this.paginaHtml = paginaHtml;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	

}
