package cl.diego.java.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.diego.java.catalogo.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
