package cl.diego.java.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.diego.java.modelo.Usuario;

public class UsuarioController {
	
	@GetMapping("/")
	public String index(Model modelo) {
		
		Usuario miUsuario = Usuario.builder()
				.username("administrador@123.cl")
				.contrasena("1234")
				.rol("ADMIN")
				.build()
				;
		modelo.addAttribute("usuario", miUsuario);
		//new Usuario(null, "administrador@123.cl", "1234", "ADMIN");
		
		return "index";
	}
}
