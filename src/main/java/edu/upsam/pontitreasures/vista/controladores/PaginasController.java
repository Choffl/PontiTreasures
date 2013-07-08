package edu.upsam.pontitreasures.vista.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.servicios.PaginasServicio;
import edu.upsam.pontitreasures.vista.formularios.PaginaForm;

/**
 * @author ssabariego
 *
 */
@Controller
@RequestMapping("/paginas")
public class PaginasController {
	
	@Autowired
	private PaginasServicio paginasServicio;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="paginas")
	public Collection<PaginaJuego> mostrarTodas(Model model){
		model.addAttribute("seccion", "Paginas jQUERY");
		model.addAttribute("paginaForm", new PaginaForm());
		return paginasServicio.recuperarTodas();
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{paginaId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("paginaId") Long paginaId, Model model){
		model.addAttribute("seccion", "Paginas jQUERY");
		model.addAttribute("pagina", paginasServicio.recuperarPorId(paginaId));
		return "pagina";
	}
	
	/**
	 * @param paginaId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{paginaId}/html", params="download", method=RequestMethod.GET)
	public HttpEntity<byte[]> bajarContenidoHTML(@PathVariable("paginaId")Long paginaId, Model model){
		PaginaJuego paginaJuego = paginasServicio.recuperarPorId(paginaId);
		HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.TEXT_HTML);
        header.set("Content-Disposition", "attachment; filename=paginaJquery.html");
        header.setContentLength(paginaJuego.getPaginaHtml().length);
        return new HttpEntity<byte[]>(paginaJuego.getPaginaHtml(), header);
	}
	
	/**
	 * @param paginaId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{paginaId}/html", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
	public byte[] mostrarContenidoHTML(@PathVariable("paginaId")Long paginaId, Model model){
		PaginaJuego paginaJuego = paginasServicio.recuperarPorId(paginaId);
        return paginaJuego.getPaginaHtml();
	}
	
	/**
	 * 
	 * @param usuarioForm
	 * @param model
	 * @param errors
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/pagina", method=RequestMethod.POST)
	public String altaPagina(@Valid PaginaForm paginaForm, BindingResult bindingResult, @RequestParam(value="html", required=true)MultipartFile file, Errors errors) throws IOException{
		if(bindingResult.hasErrors() || !isValidaFichero(file, errors)){
			return "paginas/pagina";
		}
		paginasServicio.alta(paginaForm.getNombre(), paginaForm.getDescripcion(), file.getBytes());
		return "redirect:/paginas";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/pagina", method=RequestMethod.POST, params="modify")
	public String modificaCaza(@Valid PaginaForm paginaForm, BindingResult bindingResult, @RequestParam(value="html", required=true)MultipartFile file, Errors errors) throws IOException{
		PaginaJuego paginaJuego = paginasServicio.recuperarPorId(Long.valueOf(paginaForm.getId()));
		if(bindingResult.hasErrors() || !isValidaFichero(file, errors)){
			return "paginas/pagina";
		}
		modificarDatosPagina(paginaForm, paginaJuego, file.getBytes());		
		paginasServicio.actualizar(paginaJuego);
		return "redirect:/paginas";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{paginaId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaginaForm recuperarCaza(@PathVariable("paginaId") Long paginaId, Model model){
		PaginaJuego paginaJuego = paginasServicio.recuperarPorId(paginaId);
		return crearFormularioModificacionPagina(paginaJuego);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{paginaId}", method=RequestMethod.GET, params="action=delete")
	public String eliminar(@PathVariable("paginaId") Long paginaId, RedirectAttributes redirectAttributes){
		if(!paginasServicio.eliminar(paginaId)){
			redirectAttributes.addFlashAttribute("successMsg", "No se puede eliminar ya que la pagina esta en uso");
		}
		return "redirect:/paginas";
	}
	
	private PaginaForm crearFormularioModificacionPagina(PaginaJuego paginaJuego) {
		PaginaForm paginaForm = new PaginaForm();
		paginaForm.setId(paginaJuego.getId().toString());
		paginaForm.setNombre(paginaJuego.getNombre());
		paginaForm.setDescripcion(paginaJuego.getDescripcion());
		return paginaForm;
	}
	
	private void modificarDatosPagina(PaginaForm paginaForm, PaginaJuego paginaJuego, byte[] contenido) {
		paginaJuego.setNombre(paginaForm.getNombre());
		paginaJuego.setDescripcion(paginaForm.getDescripcion());
		paginaJuego.setPaginaHtml(contenido);
	}


	private boolean isValidaFichero(MultipartFile file, Errors errors) {
		Boolean valido= Boolean.TRUE;
		if(!file.getContentType().equals("text/html")){
			errors.reject("paginaForm","El fichero no tiene extension valida");
			valido= Boolean.FALSE;	
		}
		return valido;
	}

}
