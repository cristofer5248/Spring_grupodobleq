package com.grupoq.app.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.grupoq.app.models.entity.Flujo;
import com.grupoq.app.models.service.IFlujoService;

@Controller
@SessionAttributes("flujo")
public class FlujoController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFlujoService flujoService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/flujo/{term}", produces = { "application/json" })
	public @ResponseBody List<Flujo> todosJson(@PathVariable Long term) {
		return flujoService.findByTaller_Id(term);


	}

}
