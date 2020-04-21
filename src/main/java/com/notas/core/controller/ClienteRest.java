package com.notas.core.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.notas.core.entity.Nota;

@Controller
@RequestMapping("/nota")
public class ClienteRest {

	private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWtoYWVsIn0.nleduaHSMDbKbga1tdr0QFPCm-FvTa7LKJbwSSiBu-8dD6lWdHk80nDTyEfYmr47IQ0Yet-XmdZtIxUNk58RJw";

	@GetMapping("/all")
	public ModelAndView devolvertodos() {
		ModelAndView mav = new ModelAndView("template");

		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", token);

		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<Nota[]> notasEntity = rest.exchange("http://localhost:9001/v1/nota", HttpMethod.GET, entity,
				Nota[].class);

		Nota[] notas = notasEntity.getBody();

		mav.addObject("notas", notas);
		return mav;
	}
}
