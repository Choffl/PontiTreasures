package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

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

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.TipoPagina;
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
		model.addAttribute("paginas", paginasServicio.recuperarPorTipo(TipoPagina.PERSONALIZABLE));
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
		Integer condicionDistintoPremio = Integer.valueOf(cazaForm.getDistintoPremio());
		
		cazasTerosoroServicio.alta(cazaForm.getNombre(), circuito, gestor, 
				paginaPremioAnonimo, paginaPremioIdentificado, condicionPremio, condicionDistintoPremio);
		return "redirect:/cazas";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/caza", method=RequestMethod.POST, params="modify")
	public String modificaCaza(@Valid CazaForm cazaForm, Model model, Errors errors){
		CazaTesoro cazaTesoro = cazasTerosoroServicio.recuperarPorId(Long.valueOf(cazaForm.getId()));		
		modificarDatosCaza(cazaForm, cazaTesoro);		
		cazasTerosoroServicio.actualizar(cazaTesoro);
		return "redirect:/cazas";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET, params="action=active")
	public String activarCaza(@PathVariable("cazaId") Long cazaId, Model model){
		cazasTerosoroServicio.activar(cazaId);
		return "redirect:/cazas";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET, params="action=close")
	public String cerrarCzaza(@PathVariable("cazaId") Long cazaId, Model model){
		cazasTerosoroServicio.cerrar(cazaId);
		return "redirect:/cazas";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CazaForm recuperarCaza(@PathVariable("cazaId") Long cazaId, Model model){
		CazaTesoro caza = cazasTerosoroServicio.recuperarPorId(cazaId);
		return crearFormularioModificacionCaza(caza);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("cazaId") Long cazaId, Model model){
		model.addAttribute("seccion", "Cazas del Tesoro");
		model.addAttribute("caza", cazasTerosoroServicio.recuperarPorId(cazaId));
		return "caza";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET, params="action=delete")
	public String eliminar(@PathVariable("cazaId") Long cazaId, RedirectAttributes redirectAttributes){
		if(!cazasTerosoroServicio.eliminar(cazaId)){
			redirectAttributes.addFlashAttribute("successMsg", "No se puede eliminar ya que existen jugadores participando en la caza");
		}
		return "redirect:/cazas";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{cazaId}", method=RequestMethod.GET, params="action=graphs")
	public String verGraficas(@PathVariable("cazaId") Long cazaId, Model model){
		CazaTesoro cazaTesoro = cazasTerosoroServicio.recuperarPorId(cazaId);
		String numeroCheckinsAnonimos = cazasTerosoroServicio.calcularCheckinsAnonimos(cazaTesoro).toString();
		String numeroCheckinsIdentificados = cazasTerosoroServicio.calcularCheckinsIdentificados(cazaTesoro).toString();
		String numeroPremiosAnonimos = cazasTerosoroServicio.calcularPremiosAnonimos(cazaTesoro).toString();
		String numeroPremiosIdentificados = cazasTerosoroServicio.calcularPremiosIdentificados(cazaTesoro).toString();
		
		model.addAttribute("seccion", "Cazas del Tesoro");
		model.addAttribute("numeroCheckinsAnonimos", numeroCheckinsAnonimos);
		model.addAttribute("numeroCheckinsIdentificados", numeroCheckinsIdentificados);
		model.addAttribute("numeroPremiosAnonimos", numeroPremiosAnonimos);
		model.addAttribute("numeroPremiosIdentificados", numeroPremiosIdentificados);		
		return "caza/graficos";
	}
	
	/*
	 * copiamos los datos necesarios
	 */
	private CazaForm crearFormularioModificacionCaza(CazaTesoro caza) {
		CazaForm cazaForm = new CazaForm();
		cazaForm.setId(caza.getId()!=null?caza.getId().toString():"");
		cazaForm.setNombre(caza.getNombre());
		cazaForm.setDistintoPremio(caza.getNumeroDistintoCheckinPremio()!=null?caza.getNumeroDistintoCheckinPremio().toString():"");
		cazaForm.setPremio(caza.getNumeroCheckinPremio()!=null?caza.getNumeroCheckinPremio().toString():"");
		cazaForm.setCircuitoId(caza.getCircuito()!=null?caza.getCircuito().getId():0);
		cazaForm.setGestorId(caza.getGestor()!=null?caza.getGestor().getId():0);
		cazaForm.setIdPaginaPremioAnonimo(caza.getPaginaPremioAnonimo()!=null?caza.getPaginaPremioAnonimo().getId():0);
		cazaForm.setIdPaginaPremioIdentificado(caza.getPaginaPremioIdentificado()!=null?caza.getPaginaPremioAnonimo().getId():0);
		return cazaForm;
	}
	
	private void modificarDatosCaza(CazaForm cazaForm, CazaTesoro cazaTesoro) {
		Circuito circuito = circuitosServicio.recuperarPorId(cazaForm.getCircuitoId());
		Usuario gestor = usuariosServicio.recuperarPorId(cazaForm.getGestorId());
		PaginaJuego paginaPremioAnonimo = paginasServicio.recuperarPorId(cazaForm.getIdPaginaPremioAnonimo());
		PaginaJuego paginaPremioIdentificado = paginasServicio.recuperarPorId(cazaForm.getIdPaginaPremioIdentificado());
		Integer condicionPremio = Integer.valueOf(cazaForm.getPremio());
		Integer condicionDistintoMencion = Integer.valueOf(cazaForm.getDistintoPremio());
		cazaTesoro.setCircuito(circuito);
		cazaTesoro.setGestor(gestor);
		cazaTesoro.setPaginaPremioAnonimo(paginaPremioAnonimo);
		cazaTesoro.setPaginaPremioIdentificado(paginaPremioIdentificado);
		cazaTesoro.setNombre(cazaForm.getNombre());
		cazaTesoro.setNumeroDistintoCheckinPremio(condicionDistintoMencion);
		cazaTesoro.setNumeroCheckinPremio(condicionPremio);
	}


}
