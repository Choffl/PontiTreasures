package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;
import edu.upsam.pontitreasures.servicios.UsuariosServicio;
import edu.upsam.pontitreasures.vista.formularios.CazaForm;


@Controller
@RequestMapping("/cazas")
public class CazasTesoroController {
	
	@Autowired
	private CazasTerosoroServicio cazasTerosoroServicio;
	
	@Autowired
	private UsuariosServicio usuariosServicio;
	
	@Autowired
	private CircuitosServicio circuitosServicio;
	
	@Autowired
	private PaginasServicio paginasServicio;
	
	/**
	 * Muestra todas las cazas
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="cazas")
	public Collection<CazaTesoro> mostrarTodas(Model model){
		model.addAttribute("gestores", usuariosServicio.recuperarGestores());
		model.addAttribute("circuitos", circuitosServicio.recuperarTodos());
		model.addAttribute("seccion", "Cazas del Tesoro");
		model.addAttribute("paginas", paginasServicio.recuperarTodas());
		model.addAttribute("cazaForm", new CazaForm());
		return cazasTerosoroServicio.recuperarTodos();
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/caza", method=RequestMethod.POST)
	public String altaCaza(@Valid CazaForm cazaForm, Model model, Errors errors){
		Circuito circuito = circuitosServicio.recuperarPorId(cazaForm.getCircuitoId());
		Usuario gestor = usuariosServicio.recuperarPorId(cazaForm.getGestorId());
		PaginaJuego paginaPremioAnonimo = paginasServicio.recuperarPorId(cazaForm.getIdPaginaPremioAnonimo());
		PaginaJuego paginaPremioIdentificado = paginasServicio.recuperarPorId(cazaForm.getIdPaginaPremioIdentificado());
		Integer condicionPremio = Integer.valueOf(cazaForm.getPremio());
		Integer condicionMencion = Integer.valueOf(cazaForm.getMencion());
		cazasTerosoroServicio.alta(cazaForm.getNombre(), circuito, gestor, 
				paginaPremioAnonimo, paginaPremioIdentificado, condicionPremio, condicionMencion);
		return "redirect:/cazas";
	}

}
