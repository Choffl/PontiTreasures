package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
import edu.upsam.pontitreasures.vista.formularios.CircuitoForm;
import edu.upsam.pontitreasures.vista.formularios.EtiquetaForm;

/**
 * @author ssabariego
 *
 */
@Controller
@RequestMapping("/circuitos")
public class CircuitosController {
	
	@Autowired
	private CircuitosServicio circuitosServicio;
	
	@Autowired
	private EtiquetasServicio etiquetasServicio;
	
	/**
	 * Muestra todas las circuitos
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="circuitos")
	public Collection<Circuito> mostrarTodos(Model model){
		model.addAttribute("seccion", "Circuitos de etiquetas");
		model.addAttribute("circuitoForm", new CircuitoForm());
		model.addAttribute("etiquetas", etiquetasServicio.recuperarTodas());
		return circuitosServicio.recuperarTodos();
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/circuito", method=RequestMethod.POST)
	public String altaCaza(@Valid CircuitoForm circuitoForm, Model model, Errors errors){
		circuitosServicio.alta(circuitoForm.getNombre(), circuitoForm.getDescripcion(), circuitoForm.getEtiquetas());
		return "redirect:/circuitos";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{circuitoId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("circuitoId") Long circuitoId, Model model){
		model.addAttribute("seccion", "Circuitos de etiquetas");
		model.addAttribute("circuito", circuitosServicio.recuperarPorId(circuitoId));
		return "circuito";
	}

}
