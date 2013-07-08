package edu.upsam.pontitreasures.vista.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.ServletContext;
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

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.TipoPagina;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;
import edu.upsam.pontitreasures.vista.formularios.EtiquetaForm;

/**
 * @author ssabariego
 *
 */
@Controller
@RequestMapping("/etiquetas")
public class EtiquetasController {
	
	@Autowired
	private EtiquetasServicio etiquetasServicio;

	@Autowired
	private PaginasServicio paginasServicio;
	
	@Autowired
	ServletContext context;
	
	/**
	 * Muestra todas las etiquetas
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="etiquetas")
	public Collection<Etiqueta> mostrarTodas(Model model){
		model.addAttribute("seccion", "Etiquetas QR");
		model.addAttribute("etiquetaForm", new EtiquetaForm());
		model.addAttribute("paginas", paginasServicio.recuperarPorTipo(TipoPagina.PERSONALIZABLE));
		return etiquetasServicio.recuperarTodas();
	}
	
	/**
	 * 
	 * @param etiquetaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/etiqueta", method=RequestMethod.POST)
	public String altaCaza(@Valid EtiquetaForm etiquetaForm, Model model, Errors errors){
		PaginaJuego paginasJuegoAnonimo = paginasServicio.recuperarPorId(etiquetaForm.getIdPaginaAnonimo());
		PaginaJuego paginasJuegoIdentificado = paginasServicio.recuperarPorId(etiquetaForm.getIdPaginaIdentificado());
		etiquetasServicio.alta(etiquetaForm.getCodigo(), etiquetaForm.getDescripcion(), etiquetaForm.getLatitud(), etiquetaForm.getLongitud(), paginasJuegoAnonimo, paginasJuegoIdentificado);
		return "redirect:/etiquetas";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{etiquetaId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("etiquetaId") Long etiquetaId, Model model){
		model.addAttribute("seccion", "Etiquetas QR");
		model.addAttribute("etiqueta", etiquetasServicio.recuperarPorId(etiquetaId));
		return "etiqueta";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/etiqueta", method=RequestMethod.POST, params="modify")
	public String modificaCaza(@Valid EtiquetaForm etiquetaForm, Model model, Errors errors){
		Etiqueta etiqueta = etiquetasServicio.recuperarPorId(Long.valueOf(etiquetaForm.getId()));		
		modificarDatosEtiqueta(etiquetaForm, etiqueta);		
		etiquetasServicio.actualizar(etiqueta);
		return "redirect:/etiquetas";
	}


	/**
	 * 
	 */
	@RequestMapping(value="/{etiquetaId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody EtiquetaForm recuperarCaza(@PathVariable("etiquetaId") Long etiquetaId, Model model){
		Etiqueta etiqueta = etiquetasServicio.recuperarPorId(etiquetaId);
		return crearFormularioModificacionEtiqueta(etiqueta);
	}
	

	/**
	 * 
	 */
	@RequestMapping(value="/{etiquetaId}", method=RequestMethod.GET, params="action=delete")
	public String eliminar(@PathVariable("etiquetaId") Long etiquetaId, RedirectAttributes redirectAttributes){
		if(!etiquetasServicio.eliminar(etiquetaId)){
			redirectAttributes.addFlashAttribute("successMsg", "No se puede eliminar ya que esta en un  o se ha registrado por un jugador");
		}
		return "redirect:/etiquetas";
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/qr/{etiquetaId}", method=RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] renderizarQrCode(@PathVariable("etiquetaId") Long etiquetaId, Model model) throws IOException{
		return etiquetasServicio.recuperarImagenQR(etiquetaId);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{etiquetaId}", method=RequestMethod.GET, params="action=graphs")
	public String verGraficas(@PathVariable("etiquetaId") Long etiquetaId, Model model){
		Etiqueta etiqueta = etiquetasServicio.recuperarPorId(etiquetaId);
		String numeroCheckinsAnonimos = etiquetasServicio.calcularCheckinsAnonimos(etiqueta).toString();
		String numeroCheckinsIdentificados = etiquetasServicio.calcularCheckinsIdentificados(etiqueta).toString();
		model.addAttribute("seccion", "Etiquetas QR");
		model.addAttribute("etiqueta", etiqueta);
		model.addAttribute("numeroCheckinsAnonimos", numeroCheckinsAnonimos);
		model.addAttribute("numeroCheckinsIdentificados", numeroCheckinsIdentificados);
		return "etiqueta/graficos";
	}
	
	/*
	 */
	private EtiquetaForm crearFormularioModificacionEtiqueta(Etiqueta etiqueta) {
		EtiquetaForm etiquetaForm = new EtiquetaForm();
		etiquetaForm.setId(etiqueta.getId()!=null?etiqueta.getId().toString():"");
		etiquetaForm.setCodigo(etiqueta.getCodigo());
		etiquetaForm.setDescripcion(etiqueta.getDescripcion());
		etiquetaForm.setLatitud(etiqueta.getLatitud()!=null?etiqueta.getLatitud().toString():"");
		etiquetaForm.setLongitud(etiqueta.getLongitud()!=null?etiqueta.getLongitud().toString():"");
		etiquetaForm.setIdPaginaAnonimo(etiqueta.getPaginaCheckinAnonimo()!=null?etiqueta.getPaginaCheckinAnonimo().getId():0);
		etiquetaForm.setIdPaginaIdentificado(etiqueta.getPaginaCheckinIdentificado()!=null?etiqueta.getPaginaCheckinIdentificado().getId():0);
		return etiquetaForm;
	}
	
	/*
	 */
	private void modificarDatosEtiqueta(EtiquetaForm etiquetaForm, Etiqueta etiqueta) {
		PaginaJuego paginaJuegoAnonima = paginasServicio.recuperarPorId(etiquetaForm.getIdPaginaAnonimo());
		PaginaJuego paginaJuegoIdentificado = paginasServicio.recuperarPorId(etiquetaForm.getIdPaginaIdentificado());
		
		etiqueta.setPaginaCheckinAnonimo(paginaJuegoAnonima);
		etiqueta.setPaginaCheckinIdentificado(paginaJuegoIdentificado);
		etiqueta.setCodigo(etiquetaForm.getCodigo());
		etiqueta.setDescripcion(etiquetaForm.getDescripcion());
		etiqueta.setLatitud(new BigDecimal(etiquetaForm.getLatitud()));
		etiqueta.setLongitud(new BigDecimal(etiquetaForm.getLongitud()));
	}

}
