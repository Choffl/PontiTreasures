package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;
import edu.upsam.pontitreasures.vista.formularios.CircuitoForm;

/**
 * @author ssabariego
 *
 */
@Controller
@RequestMapping("/circuitos")
public class CircuitosController {
	
	@Autowired
	private CircuitosServicio circuitosServicio;
	
	/**
	 * Muestra todas las circuitos
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="circuitos")
	public Collection<Circuito> mostrarTodos(Model model){
		model.addAttribute("seccion", "Circuitos de etiquetas");
		model.addAttribute("circuitoForm", new CircuitoForm());
		return circuitosServicio.recuperarTodos();
	}

}
