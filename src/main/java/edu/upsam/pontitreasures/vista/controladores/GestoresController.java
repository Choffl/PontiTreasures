package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.servicios.UsuariosServicio;
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

}
