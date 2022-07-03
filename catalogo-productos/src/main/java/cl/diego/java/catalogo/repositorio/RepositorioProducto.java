package cl.diego.java.catalogo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.diego.java.catalogo.modelo.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, Long> {
	
		// Retornara el nombre de la imagen correspondiente para su eliminacion
		@Query(value = "SELECT imagen FROM producto p WHERE p.id = ?", nativeQuery = true)
		String findImagenById(Long id);
}
