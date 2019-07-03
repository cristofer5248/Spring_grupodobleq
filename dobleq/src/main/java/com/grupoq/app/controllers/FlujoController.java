package com.grupoq.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.service.IFlujoService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/flujo")
@SessionAttributes("flujo")
public class FlujoController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFlujoService flujoService;
	
//	@Autowired
//	private IUsuarioService usuarioService;
	
	
	@GetMapping(value = "/ver/{term}", produces = { "application/json" })
	public @ResponseBody List<Flujo> todosJson(@PathVariable Long term) {
		return flujoService.findByTaller_Id(term);

	}
	
	
	@RequestMapping(value = { "/ver" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Pageable pageRequest = PageRequest.of(page, 4);

//		Usuario user1 = usuarioService.findByUsername(authentication.getName());
		Page <Flujo> flujo = flujoService.findAll(pageRequest);
		PageRender<Flujo> pageRender = new PageRender<Flujo>("/flujo/ver", flujo);		
		model.addAttribute("titulo", "Flujo de procesos");
		model.addAttribute("flujo", flujo);
		model.addAttribute("page", pageRender);
		return "flujo/ver";
	}
	
	

}
