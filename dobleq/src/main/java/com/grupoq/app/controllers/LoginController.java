package com.grupoq.app.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Usuario;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesion antetiormente");
			return "redictect:/";
		}
		if (error != null) {
			model.addAttribute("error", "Error en el login");
		}
		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesion con exito");
		}
		return "login";
	}

	@RequestMapping(value = { "/recuperar"}, method = RequestMethod.GET)
	public String recuperarpage(Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo1", "Activar");
		model.put("titulo2", "Restablecer");
		return "recuperar";
	}

}
