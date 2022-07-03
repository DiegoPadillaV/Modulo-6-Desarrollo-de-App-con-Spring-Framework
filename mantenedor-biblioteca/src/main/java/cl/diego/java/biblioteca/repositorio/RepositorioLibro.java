package cl.diego.java.biblioteca.repositorio;

import java.util.List;

import cl.diego.java.biblioteca.modelo.Libro;

public interface RepositorioLibro {
	
	public List<Libro> findAll();
	
	public Libro findById(Long id);
	
	public void create(Libro libro);
	
	public void edit(Libro libro);
	
	public void delete(Long id);
}	
