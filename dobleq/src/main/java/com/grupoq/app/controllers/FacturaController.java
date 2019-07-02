package com.grupoq.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.service.IFacturaService;
import com.grupoq.app.models.service.IFlujoService;
import com.grupoq.app.models.service.ITallerServices;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private ITallerServices tallerService;
	
	@Autowired
	private IFlujoService flujosService;
	
	



	@GetMapping(value = "/cargar_servicios/{term}", produces = { "application/json" })
	public @ResponseBody List<Servicios> cargarProductos(@PathVariable String term) {

		Servicios servi = new Servicios();
		List<Servicios> list = facturaService.findByNombreServicio(term);
		for (int i = 0; i < list.size(); i++) {
//			servi[i]
			servi.setNombreServicio(list.get(i).getNombreServicio());
			servi.setId(list.get(i).getId());
			
		}
		List<Servicios> list2 = new ArrayList<Servicios>();
		list2.add(servi);

		return list2;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		facturaService.delete(id);

		flash.addFlashAttribute("success", "Item eliminado con éxito!");

		return "redirect:/clientes";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/finalizar/{id}")
	public String finalizarfactura(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Taller taller = tallerService.findByOne(id);
		taller.setActivof(true);
		tallerService.save(taller);

		flash.addFlashAttribute("success", "Factura finalizada con éxito!");

		return "redirect:/clientes";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@Valid Factura factura,String tipopintura, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		String mensajeFlash = (factura.getId() != null) ? "Factura guardada con éxito!" : "Factura editada con éxito!";
		
		factura.getTaller().setActivof(true);
		facturaService.save(factura);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		Flujo flujo = new Flujo();
		flujo.setTaller(factura.getTaller());
		flujo.setEstado(flujosService.findEstado((long)5));
		flujo.setServicios(facturaService.findByidServicios((long)1));
		flujosService.save(flujo);
		return "redirect:/clientes";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/form/{param}")
	public String crear(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long param) {

		Taller taller = null;
		taller = tallerService.findTopByOrderByIdtDscByCliente_Id(param);
		Factura factura = new Factura();
		if (taller != null) {
			if (taller.getActivof()) {
				flash.addFlashAttribute("error", "Cliente ya tiene una factura emitida, realize nueva asignacion!");
				return "redirect:/vercliente/" + param;
			}
			factura.setTaller(taller);
		} else if (taller == null) {
			flash.addFlashAttribute("error", "Cliente ya tiene una factura emitida, realize nueva asignacion!");
			return "redirect:/vercliente/" + param;
		}
		if(taller.getTexpress()==true) {
			flash.addFlashAttribute("error", "No puede facturar porque primero debe ser aprobado por el cliente!");
			return "redirect:/vercliente/" + param;			
		}

		factura.setTaller(taller);
		model.put("pintura", false);
		if(taller.getNombreTaller().getId()==2) {
			System.out.print("TALLER CON VALOR 2");
			model.put("pintura", true);
		}
		
//		model.put("idtaller", tall.get(0).getId());
		model.put("factura", factura);
		model.put("titulo", "Crear nueva factura");
		return "/factura/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/fin/{param}")
	public String fin(Map<String, Object> model, RedirectAttributes flash, @PathVariable Long param) {

		List<?> tall = facturaService.probando(param);
		System.out.print(tall);
		if (tall.isEmpty()) {
			flash.addFlashAttribute("error", "Cliente ya tiene una factura emitida, realize nueva asignacion!");
			return "redirect:/vercliente/" + param;
		}

		Taller taller = facturaService.findByIdWithEmitido(param);
		taller.setActivof(true);
		tallerService.save(taller);
//		model.put("idtaller", tall.get(0).getId());
//		model.put("factura", factura);
//		model.put("titulo", "Crear nueva factura");
		return "/factura/form";
	}

}
