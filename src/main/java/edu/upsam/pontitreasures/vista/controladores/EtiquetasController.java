package edu.upsam.pontitreasures.vista.controladores;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
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
	ServletContext context;
	
	/**
	 * Muestra todas las cazas
	 * <strong>SE DEBE LIMITAR SU NUMERO</strong>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(value="etiquetas")
	public Collection<Etiqueta> mostrarTodas(Model model){
		model.addAttribute("seccion", "Etiquetas QR");
		model.addAttribute("etiquetaForm", new EtiquetaForm());
		return etiquetasServicio.recuperarTodas();
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
	 * @throws IOException 
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/qr/{etiquetaId}", method=RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] renderizarQrCode(@PathVariable("etiquetaId") Long etiquetaId, Model model) throws IOException{
		return etiquetasServicio.recuperarImagenQR(etiquetaId);
	}

}
