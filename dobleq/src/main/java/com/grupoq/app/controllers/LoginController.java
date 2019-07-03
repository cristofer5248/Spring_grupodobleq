package com.grupoq.app.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.EmailServiceImpl;
import com.grupoq.app.models.service.IUsuarioService;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailServiceImpl emailService;

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

	@RequestMapping(value = { "/recuperar" }, method = RequestMethod.GET)
	public String recuperarpage(Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo1", "Activar");
		model.put("titulo2", "Restablecer");
		return "recuperar";
	}

	@RequestMapping(value = "/reset")
	public String hacerUsuario(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		Usuario usuariocheck = usuarioService.findByUsernameByRecoverypass(usuario.getUsername(),
				usuario.getRecoverypass());
		if (usuariocheck == null) {
			flash.addFlashAttribute("error", "Tus datos no coinciden");
			return "redirect:/recuperar";
		}
		String passplano = usuario.getPassword();
		String passencryp = passwordEncoder.encode(passplano);
		usuariocheck.setPassword(passencryp);
		usuariocheck.setRecoverypass(null);
		usuarioService.save(usuariocheck);

		flash.addFlashAttribute("success", "Password restaurado");
		return "redirect:/recuperar";
	}

	@RequestMapping(value = "/newcod")
	public String newcod(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		Usuario usuariocheck = usuarioService.findByUsername(usuario.getUsername());
		if (usuariocheck == null) {
			flash.addFlashAttribute("error", "Correo no existe");
			return "redirect:/recuperar";
		}
		//1- creamos el token sencillo de 4 digitos STRING 000
		Random rand = new Random();
		String idp = String.format("%04d", rand.nextInt(10000));
		usuariocheck.setRecoverypass(idp);
		emailService.sendSimpleMessage(usuariocheck.getCorreo(), "Mensaje de recuperacion- No reenviar", "Este se su token: "+idp);
		usuarioService.save(usuariocheck);
		flash.addFlashAttribute("success", "Token enviado a su correo");
		return "redirect:/recuperar";
	}
}
