package cl.diego.java.catalogo.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.diego.java.catalogo.modelo.Categoria;
import cl.diego.java.catalogo.modelo.Producto;
import cl.diego.java.catalogo.repositorio.RepositorioCategoria;
import cl.diego.java.catalogo.repositorio.RepositorioProducto;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	RepositorioProducto repositorioProducto;
	
	@Autowired
	RepositorioCategoria repositorioCategoria;
	
	@GetMapping("/nuevo")
	public String nuevo(Producto producto, Model modelo) {
		List<Categoria> categorias = repositorioCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		return "producto/form";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Producto producto, Model modelo) {
		List<Categoria> categorias = repositorioCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("producto",producto);
		return "producto/form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Long id) throws IOException {
		repositorioProducto.deleteById(id);		
		return "redirect:/producto/listado";
	}
	
	@PostMapping("/procesar")
	public String procesar(@Valid Producto producto, BindingResult validacion, @RequestParam("image") MultipartFile imagen, Model modelo) throws IOException {
		if(validacion.hasErrors()) {
			List<Categoria> categorias = repositorioCategoria.findAll();
			modelo.addAttribute("categorias",categorias);
			return "producto/form";
		}
		if(producto.getId() == null) {
			byte[] contenidoImagen 	= imagen.getBytes();
			Producto agregarProducto = Producto.builder()
										.nombre(producto.getNombre())
										.imagen(contenidoImagen)
										.descripcion(producto.getDescripcion())
										.categoria(producto.getCategoria())
										.build();
			repositorioProducto.saveAndFlush(agregarProducto);
			return "redirect:/producto/listado";
		}else {
			byte[] contenidoImagen 	= imagen.getBytes();
			producto.setImagen(contenidoImagen);
			repositorioProducto.saveAndFlush(producto);
			return "redirect:/producto/listado";
		}
	}
	
	@GetMapping("/listado")
	public String listado(Model modelo) {
		List<Producto> productos = repositorioProducto.findAll();
		modelo.addAttribute("productos",productos);
		return "producto/listado";
	}
	
	// Retorna la imagen desde la base de datos usando ResponseEntity
		@GetMapping("listado/{id}")
		public ResponseEntity<byte[]> muestraImagenes(@PathVariable("id") Long id) throws SQLException {

			Optional<Producto> producto = repositorioProducto.findById(id);
			byte[] imageBytes = null;
			if (producto.isPresent()) {
				imageBytes = producto.get().getImagen();
			}

			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		}
}
