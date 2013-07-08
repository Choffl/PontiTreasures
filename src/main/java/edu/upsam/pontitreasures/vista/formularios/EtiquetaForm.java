package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;

/**
 * @author ssabariego
 *
 */
public class EtiquetaForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = -4573491340580145962L;
	
	private String id;

	private String codigo;

	private String descripcion;	

	private String latitud;	

	private String longitud;	

	private Long idPaginaAnonimo;	

	private Long idPaginaIdentificado;

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
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the idPaginaAnonimo
	 */
	public Long getIdPaginaAnonimo() {
		return idPaginaAnonimo;
	}

	/**
	 * @param idPaginaAnonimo the idPaginaAnonimo to set
	 */
	public void setIdPaginaAnonimo(Long idPaginaAnonimo) {
		this.idPaginaAnonimo = idPaginaAnonimo;
	}

	/**
	 * @return the idPaginaIdentificado
	 */
	public Long getIdPaginaIdentificado() {
		return idPaginaIdentificado;
	}

	/**
	 * @param idPaginaIdentificado the idPaginaIdentificado to set
	 */
	public void setIdPaginaIdentificado(Long idPaginaIdentificado) {
		this.idPaginaIdentificado = idPaginaIdentificado;
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
