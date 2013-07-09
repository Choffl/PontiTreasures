package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;


/**
 * @author ssabariego
 *
 */
public class CazaForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = -3512998172152288415L;
	
	private String id;

	private String nombre;

	private String premio;

	private String distintoPremio;

	private Long circuitoId;

	private Long gestorId;

	private Long idPaginaPremioIdentificado;

	private Long idPaginaPremioAnonimo;

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
	 * @return the circuitoId
	 */
	public Long getCircuitoId() {
		return circuitoId;
	}

	/**
	 * @param circuitoId the circuitoId to set
	 */
	public void setCircuitoId(Long circuitoId) {
		this.circuitoId = circuitoId;
	}

	/**
	 * @return the gestorId
	 */
	public Long getGestorId() {
		return gestorId;
	}

	/**
	 * @param gestorId the gestorId to set
	 */
	public void setGestorId(Long gestorId) {
		this.gestorId = gestorId;
	}

	/**
	 * @return the premio
	 */
	public String getPremio() {
		return premio;
	}

	/**
	 * @param premio the premio to set
	 */
	public void setPremio(String premio) {
		this.premio = premio;
	}

	/**
	 * @return the distintoPremio
	 */
	public String getDistintoPremio() {
		return distintoPremio;
	}

	/**
	 * @param distintoPremio the distintoPremio to set
	 */
	public void setDistintoPremio(String distintoPremio) {
		this.distintoPremio = distintoPremio;
	}

	/**
	 * @return the idPaginaPremioIdentificado
	 */
	public Long getIdPaginaPremioIdentificado() {
		return idPaginaPremioIdentificado;
	}

	/**
	 * @param idPaginaPremioIdentificado the idPaginaPremioIdentificado to set
	 */
	public void setIdPaginaPremioIdentificado(Long idPaginaPremioIdentificado) {
		this.idPaginaPremioIdentificado = idPaginaPremioIdentificado;
	}

	/**
	 * @return the idPaginaPremioAnonimo
	 */
	public Long getIdPaginaPremioAnonimo() {
		return idPaginaPremioAnonimo;
	}

	/**
	 * @param idPaginaPremioAnonimo the idPaginaPremioAnonimo to set
	 */
	public void setIdPaginaPremioAnonimo(Long idPaginaPremioAnonimo) {
		this.idPaginaPremioAnonimo = idPaginaPremioAnonimo;
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
