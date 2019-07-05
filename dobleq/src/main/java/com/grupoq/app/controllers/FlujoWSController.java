package com.grupoq.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.service.IFlujoService;
import com.grupoq.app.models.service.ITallerServices;
import com.grupoq.app.models.service.IUsuarioService;
import com.grupoq.app.ws.LoginWS;

@RestController
public class FlujoWSController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFlujoService flujoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ITallerServices tallerServices;
	
	@RequestMapping(value="/cargarflujows",method = RequestMethod.POST, produces = "application/json")
	public Flujo postLoginAndFlujoWS(@RequestBody LoginWS login) {
//		usuario
//		List<Flujo> list = flujoService.findByTaller_Id((long)1);
		Flujo flujo = new Flujo();
		flujo.setComentario("asd");
		flujo.setId((long)1);
        

		return flujo;
	}
	
	@RequestMapping(value="/cargarflujows/{term}/pass/{term2}",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Flujo> getLoginAndFlujoWSGet(@PathVariable(value = "term") String username, @PathVariable(value = "term2") String password) {
//		usuario
		List<Flujo> list = null;
		Usuario usuario = usuarioService.findByUsername(username);
		if(usuario!=null) {
			list = flujoService.findByTaller_Cliente_Id(username);
		}
        

		return list;
	}

}
