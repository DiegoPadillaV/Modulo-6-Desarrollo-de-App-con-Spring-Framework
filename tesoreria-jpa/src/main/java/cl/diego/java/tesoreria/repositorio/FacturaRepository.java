package cl.diego.java.tesoreria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.diego.java.tesoreria.modelo.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
	
	
}
