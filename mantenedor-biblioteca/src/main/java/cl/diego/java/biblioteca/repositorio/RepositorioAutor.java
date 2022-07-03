package cl.diego.java.biblioteca.repositorio;

import java.util.List;

import cl.diego.java.biblioteca.modelo.Autor;

public interface RepositorioAutor {
	
	public List<Autor> findAll();
	
	public Autor findById(Long id);
	
	public void create(Autor autor);
	
	public void edit(Autor autor);
	
	public void delete(Long id);
}
