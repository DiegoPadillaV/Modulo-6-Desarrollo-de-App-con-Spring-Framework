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
import cl.diego.java.biblioteca.modelo.Libro;
import cl.diego.java.biblioteca.repositorio.RepositorioAutor;
import cl.diego.java.biblioteca.repositorio.RepositorioLibro;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	@Autowired
	RepositorioLibro repositorioLibro ;
	
	@Autowired
	RepositorioAutor repositorioAutor;
	
	@GetMapping("/nuevolibro")
	public String nuevolibro(Libro libro, Model modelo) {
		List<Autor> autores = repositorioAutor.findAll();
		modelo.addAttribute("autores",autores);
		return "admin/nuevolibro";
	}
	
	@PostMapping("/procesar")
	public String procesar(@Valid Libro libro, BindingResult validacion, Model modelo) {
		if(validacion.hasErrors()) { 
			List<Autor> autores = repositorioAutor.findAll();
			modelo.addAttribute("autores",autores);
			return "admin/nuevolibro";
		}
		libro.setAutor(repositorioAutor.findById(libro.getAutor_id()));
		repositorioLibro.create(libro);
		return "redirect:/libros/listado";
	}
	
	@GetMapping("/listado")
	public String listado(Model modelo) {
		List<Libro> libros = repositorioLibro.findAll();
		modelo.addAttribute("libro",libros);
		return "/listalibros";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable (name="id") Long id) {
		repositorioLibro.delete(id);
		return "redirect:/libros/listado";
	}
}
