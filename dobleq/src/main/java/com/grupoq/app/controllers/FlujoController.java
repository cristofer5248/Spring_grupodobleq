package com.grupoq.app.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupoq.app.models.entity.Estado;
import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.service.IFlujoService;
import com.grupoq.app.models.service.ITallerServices;
import com.grupoq.app.models.service.IUploadFileService;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/flujo")
@SessionAttributes("flujo")
public class FlujoController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IFlujoService flujoService;

	@Autowired
	private ITallerServices tallerService;

	@Autowired
	private IUploadFileService uploadService;

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
		Page<Flujo> flujo = flujoService.findAllByOrderByIdDesc(pageRequest);
		PageRender<Flujo> pageRender = new PageRender<Flujo>("/flujo/ver", flujo);
		model.addAttribute("titulo", "Flujo de procesos");
		model.addAttribute("flujo", flujo);
		model.addAttribute("page", pageRender);
		return "flujo/ver";
	}

	@RequestMapping(value = { "/nuevo/{id}" }, method = RequestMethod.GET)
	public String nuevo(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		

//		Usuario user1 = usuarioService.findByUsername(authentication.getName());
		Taller taller = tallerService.findTopByOrderByCliente_IdDesc(id);
		Flujo flujo = new Flujo();
		flujo.setTaller(taller);
		model.put("titulo", "Nuevo Flujo de procesos");
		model.put("flujo", flujo);
		return "flujo/form";
	}

	@GetMapping(value = "/cargar_estadosf", produces = { "application/json" })
	public @ResponseBody List<Estado> cargarProductos() {
		return flujoService.findAllEstado();
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Flujo flujo, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) {
		String uniqueFilename = null;
		try {
			uniqueFilename = uploadService.copy(foto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

		flujo.setFoto(uniqueFilename);
		String mensajeFlash = (flujo.getId() != null) ? "Nuevo flujo guardado con éxito!" : "usuario editado con éxito!";
		flujoService.save(flujo);
		flash.addFlashAttribute("succes", mensajeFlash);
		return "redirect:/flujo/ver";
	}

}
