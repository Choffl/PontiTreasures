package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.upsam.pontitreasures.vista.json.CazaJSON;
import edu.upsam.pontitreasures.vista.json.EjemploJSON;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@RequestMapping(value="/cazas")
	@ResponseBody
	public Collection<CazaJSON> recuperarCazasActivas(){
		return new HashSet<CazaJSON>();
	}
	
	@RequestMapping(value="/ejemplo")
	@ResponseBody
	public EjemploJSON recuperarEjemplo(){
		return new EjemploJSON("mi caza");
	}

}
