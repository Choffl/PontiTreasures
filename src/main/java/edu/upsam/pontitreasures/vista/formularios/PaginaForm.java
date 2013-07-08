/**
 * 
 */
package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;

/**
 * @author ssabariego
 *
 */
public class PaginaForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = 4876476036836377959L;
	
	private String id;
	
	private String nombre;
	
	private String descripcion;

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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
