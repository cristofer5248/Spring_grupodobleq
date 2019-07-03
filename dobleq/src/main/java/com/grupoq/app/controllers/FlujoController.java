package com.grupoq.app.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.service.IFlujoService;

@RestController
@RequestMapping("/flujo")
@SessionAttributes("flujo")
public class FlujoController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFlujoService flujoService;
	
	
	@GetMapping(value = "/ver/{term}", produces = { "application/json" })
	public @ResponseBody List<Flujo> todosJson(@PathVariable Long term) {
		return flujoService.findByTaller_Id(term);

	}
	
	

}
