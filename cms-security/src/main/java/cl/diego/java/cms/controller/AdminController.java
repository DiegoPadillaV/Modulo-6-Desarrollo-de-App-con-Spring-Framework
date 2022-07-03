package cl.diego.java.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.diego.java.cms.model.Usuario;
import cl.diego.java.cms.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/instalar")
	public String instalar() {
		long cantidadUsuarios = usuarioService.cantidadUsuarios();
		if(cantidadUsuarios == 0) {
			// Solo ejecutar en el caso de que la tabla Usuarios este vacia
			Usuario usuario = new Usuario(null, "diego@gmail.com", "1234", "ADMIN");
			usuarioService.crearUsuario(usuario);			
		}
		return "redirect:/";
	}
	
	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("/reporte")
	public String reporte() {
		return "admin/reporte";
	}
}
