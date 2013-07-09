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
import edu.upsam.pontitreasures.dominio.TipoUsuario;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.servicios.UsuariosServicio;
import edu.upsam.pontitreasures.vista.formularios.CazaForm;
import edu.upsam.pontitreasures.vista.formularios.GestorForm;

@Controller
@RequestMapping("/gestores")
public class GestoresController {
	
	@Autowired
	private UsuariosServicio usuariosServicio;
	
	/**
	 * Muestra todas las circuitos
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="gestores")
	public Collection<Usuario> mostrarTodos(Model model){
		model.addAttribute("seccion", "Gestores de circuitos");
		model.addAttribute("gestorForm", new GestorForm());
		return usuariosServicio.recuperarGestores();
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/gestor", method=RequestMethod.POST)
	public String altaCaza(@Valid GestorForm gestorForm, Model model, Errors errors){
		usuariosServicio.alta(gestorForm.getUsername(), gestorForm.getEmail(), gestorForm.getPassword(), TipoUsuario.GESTOR);
		
		return "redirect:/gestores";
	}
	
	/**
	 * 
	 * @param cazaForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/gestor", method=RequestMethod.POST, params="modify")
	public String modificaGestor(@Valid GestorForm gestorForm, Model model, Errors errors){
		Usuario usuario = usuariosServicio.recuperarPorId(Long.valueOf(gestorForm.getId()));		
		modificarDatosGestor(gestorForm, usuario);		
		usuariosServicio.actualizar(usuario);
		return "redirect:/gestores";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{gestorId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("gestorId") Long gestorId, Model model){
		model.addAttribute("seccion", "Gestores de circuitos");
		model.addAttribute("gestor", usuariosServicio.recuperarPorId(gestorId));
		return "gestor";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{gestorId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GestorForm recuperarGestor(@PathVariable("gestorId") Long gestorId, Model model){
		Usuario gestor = usuariosServicio.recuperarPorId(gestorId);
		return crearFormularioModificacionGestor(gestor);
	}

	/**
	 * 
	 */
	@RequestMapping(value="/{gestorId}", method=RequestMethod.GET, params="action=delete")
	public String eliminar(@PathVariable("gestorId") Long gestorId, RedirectAttributes redirectAttributes){
		if(!usuariosServicio.eliminar(gestorId)){
			redirectAttributes.addFlashAttribute("successMsg", "No se puede eliminar ya que gestiona alguna caza");
		}
		return "redirect:/gestores";
	}
	
	private GestorForm crearFormularioModificacionGestor(Usuario gestor) {
		GestorForm gestorForm = new GestorForm();
		gestorForm.setUsername(gestor.getUsername());
		gestorForm.setEmail(gestor.getEmail());
		gestorForm.setId(gestor.getId().toString());
		gestorForm.setPassword(gestor.getPassword());
		gestorForm.setPasswordRepetido(gestor.getPassword());
		return gestorForm;
	}

	private void modificarDatosGestor(GestorForm gestorForm, Usuario usuario) {
		usuario.setEmail(gestorForm.getEmail());
		usuario.setPassword(gestorForm.getPassword());
		usuario.setUsername(gestorForm.getUsername());
	}

}
