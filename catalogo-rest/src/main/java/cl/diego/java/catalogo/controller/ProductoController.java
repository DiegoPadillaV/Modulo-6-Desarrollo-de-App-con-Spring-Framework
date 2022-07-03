package cl.diego.java.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.diego.java.catalogo.exceptions.ProductoNoEncontradoException;
import cl.diego.java.catalogo.modelo.Producto;
import cl.diego.java.catalogo.repository.ProductoRepository;

@RestController
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	ProductoRepository productoRepository;
	
	@GetMapping("/productos")
	public List<Producto> todosLosProductos(){
		return productoRepository.findAll();
	}
	
	@PostMapping("/productos")
	public Producto nuevoProducto(@RequestBody Producto productoNuevo) {
		return productoRepository.save(productoNuevo);
	}
	
	@GetMapping("/productos/{id}")
	public Producto productoPorId(@PathVariable Long id) {
		return productoRepository.findById(id)
							 .orElseThrow( () -> new ProductoNoEncontradoException(id));
	}
	
	@PutMapping("/productos/{id}")
	public Producto reemplazarProducto(@RequestBody Producto productoAReemplazar, @PathVariable Long id) {
		return productoRepository.findById(id)
							 .map( producto -> {
								 producto.setNombre(productoAReemplazar.getNombre());
								 producto.setPrecio(productoAReemplazar.getPrecio());
								 return productoRepository.save(producto);
							 })
							 .orElseGet(() -> {
								productoAReemplazar.setId(id);
								return productoRepository.save(productoAReemplazar);
							 });
	}
	
	@DeleteMapping("/productos/{id}")
	public void eliminarProducto(@PathVariable Long id) {
		productoRepository.deleteById(id);
	}

	/*
	 * @GetMapping("/instalar") private String instalar() { // Opcionalmente
	 * verificar si ya se instalaron los datos long count =
	 * productoRepository.count(); if (count == 0) { // Instaciamos productos
	 * Producto producto1 =
	 * Producto.builder().nombre("Correa paseo").precio(9990).build(); Producto
	 * producto2 =
	 * Producto.builder().nombre("Plato Grande Metalico").precio(4990).build();
	 * Producto producto3 =
	 * Producto.builder().nombre("Pelota juguete").precio(3990).build();
	 * 
	 * // Persistimos en la BD productoRepository.saveAndFlush(producto1);
	 * productoRepository.saveAndFlush(producto2);
	 * productoRepository.saveAndFlush(producto3); return "ok"; } else { return
	 * "ya se habia realizado la instalacion."; } }
	 * 
	 * @GetMapping("/productos/destacados") public List<Producto>
	 * productosDestacados(){ List<Producto> productos =
	 * productoRepository.findAll(); return productos; }
	 */

}
