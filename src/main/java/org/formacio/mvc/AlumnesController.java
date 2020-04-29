package org.formacio.mvc;

import org.formacio.component.ServeiAlumnat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlumnesController {

	ServeiAlumnat matricules = new ServeiAlumnat();
	
	@RequestMapping(path="/alumnes")
	@ResponseBody
	public String saluda() {
		return "Hola món";
	}
}
