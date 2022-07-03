package cl.diego.java.tesoreria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.diego.java.tesoreria.modelo.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	
	
}
