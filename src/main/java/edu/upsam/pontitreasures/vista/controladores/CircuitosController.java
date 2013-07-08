package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
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
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/circuito", method=RequestMethod.POST, params="modify")
	public String modificaCaza(@Valid CircuitoForm circuitoForm, Model model, Errors errors){
		Circuito circuito = circuitosServicio.recuperarPorId(Long.valueOf(circuitoForm.getId()));		
		modificarDatosCircuito(circuitoForm, circuito);		
		circuitosServicio.actualizar(circuito);
		return "redirect:/circuitos";
	}

	/**
	 * 
	 */
	@RequestMapping(value="/{circuitoId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CircuitoForm recuperarCaza(@PathVariable("circuitoId") Long circuitoId, Model model){
		Circuito circuito = circuitosServicio.recuperarPorIdConEtiquetas(circuitoId);
		return crearFormularioModificacionCircuito(circuito);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{circuitoId}", method=RequestMethod.GET, params="action=delete")
	public String eliminar(@PathVariable("circuitoId") Long circuitoId, RedirectAttributes redirectAttributes){
		if(!circuitosServicio.eliminar(circuitoId)){
			redirectAttributes.addFlashAttribute("successMsg", "No se puede eliminar ya que se usa en una caza de tesoro");
		}
		return "redirect:/circuitos";
	}
	
	private void modificarDatosCircuito(CircuitoForm circuitoForm, Circuito circuito) {
		circuito.setNombre(circuitoForm.getNombre());
		circuito.setDescripcion(circuitoForm.getDescripcion());
		
		Collection<Etiqueta> etiquetas =  new HashSet<Etiqueta>();
		for (Long etiquetaId : circuitoForm.getEtiquetas()) {
			etiquetas.add(etiquetasServicio.recuperarPorId(etiquetaId));
		}
		circuito.setEtiquetas(etiquetas);
	}

	private CircuitoForm crearFormularioModificacionCircuito(Circuito circuito) {
		CircuitoForm circuitoForm = new CircuitoForm();
		circuitoForm.setNombre(circuito.getNombre());
		circuitoForm.setDescripcion(circuito.getDescripcion());
		circuitoForm.setId(circuito.getId().toString());
		
		Collection<Long> etiquetasId = new HashSet<Long>();
		for (Etiqueta etiqueta : circuito.getEtiquetas()) {
			etiquetasId.add(etiqueta.getId());
		}
		
		circuitoForm.setEtiquetas(etiquetasId);
		
		return circuitoForm;
	}

}
