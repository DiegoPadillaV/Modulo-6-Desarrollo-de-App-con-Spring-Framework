package cl.diego.java.cft.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import cl.diego.java.cft.modelo.Alumno;
import cl.diego.java.cft.modelo.Carrera;
import cl.diego.java.cft.repository.AlumnoRepository;
import cl.diego.java.cft.repository.CarreraRepository;

@Controller
@RequestMapping("/alumno") // Prefijo de las rutas
public class AlumnoController {
	
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@GetMapping("/nuevo")
	public String alumnoForm(Alumno alumno, Model modelo) {	
		List<Carrera> carreras = carreraRepository.findAll();
		modelo.addAttribute("carreras", carreras);
		return "alumno/form";
	}
	
	@GetMapping("/editar/{alumnoId}")
	public String alumnoNuevo(@PathVariable int alumnoId, Model modelo) {
		Alumno alumno = alumnoRepository.findById(alumnoId);
		modelo.addAttribute("alumno", alumno);
		List<Carrera> carreras = carreraRepository.findAll();
		modelo.addAttribute("carreras", carreras);
		return "alumno/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam(name="id", required = true) int id) {
		alumnoRepository.delete(id);
		return "redirect:/alumno/listado";
	}
	
	@PostMapping("/procesar")
	public String alumnoProcesar(@Valid Alumno alumno, BindingResult informeValidacion) {
		if(informeValidacion.hasErrors()) {
			return "alumno/form"; // Si hubieron errores, vuevlo a la vista del for
		}
		
		if(alumno.getId() == 0) {
			alumnoRepository.create(alumno);
		} else {
			alumnoRepository.edit(alumno);
		}
		return "redirect:/alumno/listado"; 
	}
	

	
	@GetMapping("/listado")
	public String listar(Model modelo) {
		List<Alumno> alumnos = alumnoRepository.findAll();
		modelo.addAttribute("alumnos", alumnos);
		return "alumno/listado";
	}
	
	
}
