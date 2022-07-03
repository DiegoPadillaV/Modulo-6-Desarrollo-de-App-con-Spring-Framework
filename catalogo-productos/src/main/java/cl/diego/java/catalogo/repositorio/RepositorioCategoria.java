package cl.diego.java.catalogo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.diego.java.catalogo.modelo.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {

}
