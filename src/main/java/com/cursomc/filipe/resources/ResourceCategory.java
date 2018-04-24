package com.cursomc.filipe.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//The annotation declare that this is a Rest Controller
@RestController
@RequestMapping(value="/categories")
public class ResourceCategory {
	
	@RequestMapping(method=RequestMethod.GET)
	public String lista() {
		return "REST está funcionando";
		
	}

}
