package cl.diego.java.catalogo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cl.diego.java.catalogo.modelo.Categoria;
import cl.diego.java.catalogo.repositorio.RepositorioCategoria;

@Controller
public class SitioController {
	
	@Autowired
	RepositorioCategoria repositorioCategoria;

	// Nada mas entrar a la raiz del programa, crea una categoria de ejemplo.
	@GetMapping("/")
	public String index() {
		long count = repositorioCategoria.count();
		if(count == 0) {
			Categoria categoria = Categoria.builder().nombre("Categoria de ejemplo").build();
			repositorioCategoria.saveAndFlush(categoria);
		}
		return "index";
	}
}
