package cl.diego.java.biblioteca.repositorio;

import java.util.List;

import cl.diego.java.biblioteca.modelo.Usuario;

public interface RepositorioUsuario {
	
	public List<Usuario> findAll();
	
	public int count();
	
	public Usuario findById(Long id);
	
	public void create(Usuario usuario);
	
	public void edit(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findByEmail(String email);
}
