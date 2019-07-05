package com.grupoq.app.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.entity.NombreTaller;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.entity.Vehiculo;
import com.grupoq.app.models.service.IClienteService;
import com.grupoq.app.models.service.IFlujoService;
import com.grupoq.app.models.service.ITallerServices;
import com.grupoq.app.util.paginator.PageRender;

@Controller
@RequestMapping("/taller")
@SessionAttributes("taller")
public class TallerController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ITallerServices tallerService;

	@Autowired
	private IFlujoService flujoService;

	@Autowired
	private IClienteService clienteService;

	@GetMapping(value = "/cargar_vehiculos/{term}", produces = { "application/json" })
	public @ResponseBody List<Vehiculo> cargarProductos(@PathVariable String term) {
		return tallerService.findByPlacas(term);
	}

	@GetMapping(value = "/verflujo/{term}", produces = { "application/json" })
	public @ResponseBody List<Flujo> todosJson(@PathVariable Long term) {
		return flujoService.findByTaller_Id(term);

	}

	@GetMapping(value = "/cargar_delegado/{term}", produces = { "application/json" })
	public @ResponseBody List<Usuario> cargarDelegado(@PathVariable String term) {
		return tallerService.findByUsername(term);
	}

	@GetMapping(value = "/cargar_tallernombre/{term}", produces = { "application/json" })
	public @ResponseBody List<NombreTaller> cargarTallerNombre(@PathVariable String term) {
		List<NombreTaller> list = tallerService.findNombreTallernombreNative(term);
		NombreTaller ob = new NombreTaller();
		List<NombreTaller> list2 = new ArrayList<NombreTaller>();
		for (int i = 0; i < list.size(); i++) {
			ob.setId(list.get(0).getId());
			ob.setNombre(list.get(0).getNombre());
			list2.add(ob);
		}

		return list2;
	}

	@GetMapping(value = "/cargar_nombretallerTodos", produces = { "application/json" })
	public @ResponseBody List<NombreTaller> todosJson() {
		List<NombreTaller> list = tallerService.findAllNombreTaller();
		return list;

//		return list2;
	}

	@GetMapping(value = "/cargar_usuariosTodos", produces = { "application/json" })
	public @ResponseBody List<Usuario> todosUsuariosJson() {
		List<Usuario> list = tallerService.findByOrderByUsernameAscByRoles_Authority("ROLE_USER");

		return list;
	}

	@RequestMapping(value = "/activar_express/{param}", method = RequestMethod.GET)
	public String activarTallerexpress(@PathVariable(value = "param") Long id) {
		Taller taller = tallerService.findByOne(id);
		taller.setTexpress(false);
		tallerService.save(taller);
		return "redirect:/login";

		
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = { "/ver" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Taller> taller = tallerService.findAll(pageRequest);
//		List<Taller> taller = tallerService.findAll();

//				clienteService.findAll(pageRequest);

		PageRender<Taller> pageRender = new PageRender<Taller>("/taller", taller);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("taller", taller);
		model.addAttribute("page", pageRender);
		return "taller/ver";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		tallerService.delete(id);
		flash.addFlashAttribute("success", "Requerimiento eliminado con éxito!");

		return "login";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/tallerform/{param}")
	public String crear(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long param) {
//para asignacion
		Taller taller = new Taller();
		try {
			taller = tallerService.findTopByOrderByIdtDscByCliente_Id(param);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (taller != null) {
			if (!taller.getActivof() && taller.getEmitidoa()) {
				if (taller.getActivof() && !taller.getEmitidoa())
					flash.addFlashAttribute("error", "Cliente ya tiene una asiganacion pendiente!");
				return "redirect:/vercliente/" + param;
			}

			// vacio es porque dejaremos crear uno

		}
		Taller taller2 = new Taller();
		taller2.setCliente(clienteService.findOne(param));
		model.put("taller", taller2);
		model.put("titulo", "Crear nueva asignacion");
		return "/taller/form";
	}

	@PostMapping("/tallersave")
	public String guardar(@Valid Taller taller, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "taller/form";
		}

		Flujo flujo = new Flujo();

		taller.setActivof(false);
		if (taller.getNombreTaller().getId() != 2) {
			taller.setTexpress(true);
		}
		taller.setEmitidoa(true);
		tallerService.save(taller);
//		a partir de aqui metemos datos a la tabla flujo para ir dando datos al bitacora en este caso prebitacora
		List<Flujo> isitnew = new ArrayList<Flujo>();
		isitnew = flujoService.findByTallerCliente(taller.getCliente().getId());
		if (isitnew.isEmpty()) {

			flujo.setEstado(flujoService.findEstado((long) 1));

		} else
			flujo.setEstado(flujoService.findEstado((long) 2));
		flujo.setServicios(clienteService.findProductoById((long) 1));
		Taller ultimoidObj = tallerService.findTopByOrderByIdtDscByCliente_Id(taller.getCliente().getId());
		flujo.setTaller(ultimoidObj);
		flujoService.save(flujo);
		status.setComplete();

		flash.addFlashAttribute("success", "Asignacion creada con éxito!");

		return "redirect:/vercliente/" + taller.getCliente().getId();
	}
}
