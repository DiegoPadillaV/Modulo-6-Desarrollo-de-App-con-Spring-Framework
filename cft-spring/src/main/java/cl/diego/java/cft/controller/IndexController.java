package cl.diego.java.cft.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
