package com.example.demo.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mappíng.Mapping;
import com.example.demo.models.Dato;
import com.example.demo.services.impl.IHttpConsumesImpl;

@RestController
@RequestMapping(Mapping.ACADEMIA)
public class ConsumesRestController {
	
	@Autowired IHttpConsumesImpl httpConsumes;
	
	@GetMapping
	public String ConsumoGoogle() {
		return httpConsumes.sendGet();
	}
}
