package cl.diego.java.biblioteca.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.diego.java.biblioteca.modelo.Autor;
import cl.diego.java.biblioteca.repositorio.RepositorioAutor;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	RepositorioAutor repositorioAutor;
	
	@GetMapping("/nuevoautor")
	public String nuevolibro(Autor autor, Model modelo) {
		List<Autor> autores = repositorioAutor.findAll();
		modelo.addAttribute("autores",autores);
		return "admin/nuevoautor";
	}
	
	@PostMapping("/procesarautor")
	public String procesarAutor(@Valid Autor autor, BindingResult validacion, Model modelo) {
		if(validacion.hasErrors()) { 
			List<Autor> autores = repositorioAutor.findAll();
			modelo.addAttribute("autores",autores);
			return "admin/nuevoautor";
		}
		repositorioAutor.create(autor);
		return "redirect:/autor/nuevoautor";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable (name="id") Long id) {
		repositorioAutor.delete(id);
		return "redirect:/autor/nuevoautor";
	}
}
