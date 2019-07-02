package com.grupoq.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.Vehiculo;
import com.grupoq.app.models.service.IAutomovilService;

@Controller
@RequestMapping("/vehiculo")
@SessionAttributes("vehiculo")
public class VehiculoController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IAutomovilService automovilService;
	

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/vehiculoform")
	public String crear(Map<String, Object> model) {

		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculo", vehiculo);
		model.put("titulo", "Registrar un vehiculo");
		return "/vehiculo/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/vehiculosave", method = RequestMethod.POST)
	public String guardar(@Valid Vehiculo vehiculo, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Vehiculos");
			return "/vehiculo/form";
		}

		String mensajeFlash = (vehiculo.getId() != null) ? "Vehiculo editado con éxito!" : "Vehiculo creado con éxito!";

		automovilService.saveFactura(vehiculo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/vehiculoform";
	}

}
