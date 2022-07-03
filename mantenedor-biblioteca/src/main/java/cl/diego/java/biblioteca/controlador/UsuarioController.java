package cl.diego.java.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.diego.java.biblioteca.modelo.Usuario;
import cl.diego.java.biblioteca.servicio.ServicioUsuario;

@Controller
@RequestMapping("/admin")
public class UsuarioController {
	
	@Autowired
	ServicioUsuario servicioUsuario;
	
	@GetMapping("/generaradmin")
	// genera una cuenta de administrador generico
	public String generarAdmin() {
		if(servicioUsuario.checkForAdmin() == null) {
			
			Usuario usuario = Usuario.builder()
										.email("admin@admin.cl")
										.password("123")
										.roles("ADMIN")
										.build();
			servicioUsuario.crearUsuario(usuario);
		}
		return "redirect:/";
	}
	
	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}
}
