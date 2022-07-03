package cl.diego.java.catalogo.controlador;

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

import cl.diego.java.catalogo.modelo.Categoria;
import cl.diego.java.catalogo.repositorio.RepositorioCategoria;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	RepositorioCategoria repositorioCategoria;
	
	
	@GetMapping("/nuevo")
	public String nuevo(Categoria categoria, Model modelo) {
		List<Categoria> categorias = repositorioCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		return "categoria/form";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Categoria categoria, Model modelo) {
		modelo.addAttribute("categoria",categoria);
		return "categoria/form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Long id) {
		repositorioCategoria.deleteById(id);
		return "redirect:/categoria/listado";
	}
	
	@PostMapping("/procesar")
	public String procesar(@Valid Categoria categoria, BindingResult validacion) {
		if(validacion.hasErrors()) return "categoria/form";
		
		repositorioCategoria.saveAndFlush(categoria);
		return "redirect:/categoria/listado";
	}
	
	@GetMapping("/listado")
	public String listado(Model modelo) {
		List<Categoria> categorias = repositorioCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		return "categoria/listado";
	}
}
