/**
 * 
 */
package edu.upsam.pontitreasures.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author ssabariego
 *
 */
@Entity
@Table(name="PT_CIRCUITO")
public class Circuito implements Serializable {

	/**
	 * id para la serializaci�n de un circuito
	 */
	private static final long serialVersionUID = -4305512033999166219L;

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
	@ManyToMany
	@JoinTable(name="PT_CIR_ETQ", joinColumns={@JoinColumn(name="circ_id", referencedColumnName="id")}, 
			inverseJoinColumns={@JoinColumn(name="etiq_id", referencedColumnName="id")})
	private Collection<Etiqueta> etiquetas = new HashSet<Etiqueta>();

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
	 * @return the etiquetas
	 */
	public Collection<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	/**
	 * @param etiquetas the etiquetas to set
	 */
	public void setEtiquetas(Collection<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	

}
