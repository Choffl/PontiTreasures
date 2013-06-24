package edu.upsam.pontitreasures.vista.formularios;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


/**
 * @author ssabariego
 *
 */
public class CazaForm implements Serializable{

	/**
	 * id para la serializacion
	 */
	private static final long serialVersionUID = -3512998172152288415L;
	

	private String nombre;
	

	private String fechaInicio;
	

	private String fechaFin;
	

	private Long circuitoId;
	

	private Long gestorId;
	
	public Date getValorFechaInicio(){
		Date fecha = new Date();
		DateFormat dateFormat = DateFormat.getInstance();	
		try {
			fecha = new Date(dateFormat.parse(getFechaInicio()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}
	
	public Date getValorFechaFin(){
		Date fecha = new Date();
		DateFormat dateFormat = DateFormat.getInstance();	
		try {
			fecha = new Date(dateFormat.parse(getFechaFin()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	
	

}
