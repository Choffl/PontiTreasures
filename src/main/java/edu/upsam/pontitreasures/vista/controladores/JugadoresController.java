package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;

@Controller
@RequestMapping("/jugadores")
public class JugadoresController {
	
	@Autowired
	private JugadoresServicio jugadoresServicio;
	
	/**
	 * Muestra todas las circuitos
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="jugadores")
	public Collection<Jugador> mostrarTodos(Model model){
		model.addAttribute("seccion", "Jugadores");
		return jugadoresServicio.recuperarTodos();
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{jugadorId}", method=RequestMethod.GET)
	public String verDetalle(@PathVariable("jugadorId") Long jugadorId, Model model){
		model.addAttribute("seccion", "Jugadores");
		model.addAttribute("jugador", jugadoresServicio.recuperarPorId(jugadorId));
		return "jugador";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/{jugadorId}", method=RequestMethod.GET, params="action=graphs")
	public String verGraficas(@PathVariable("jugadorId") Long jugadorId, Model model){
		Jugador jugador = jugadoresServicio.recuperarPorId(jugadorId);
		String numeroCheckins = jugadoresServicio.calcularCheckins(jugador).toString();
		String numeroPremios = jugadoresServicio.calcularPremios(jugador).toString();

		model.addAttribute("seccion", "Jugadores");
		model.addAttribute("numeroCheckins", numeroCheckins);
		model.addAttribute("numeroPremios", numeroPremios);
		return "jugador/graficos";
	}

}
