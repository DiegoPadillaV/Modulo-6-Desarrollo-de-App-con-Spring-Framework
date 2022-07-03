package cl.diego.java.biblioteca.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.diego.java.biblioteca.modelo.Usuario;
import cl.diego.java.biblioteca.repositorio.RepositorioUsuario;

@Service
public class ServicioUsuario {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	
	// Utiliza el findAll para sacar una lista de usuarios. Si alguno de estos usuarios es ADMIN, lo retorna.
	// El metodo que llama a este necesita que el retorno sea nulo para crear un admin generico.
	public Usuario checkForAdmin() {
		List<Usuario> usuarios = repositorioUsuario.findAll();
		for(Usuario usuario : usuarios) {
			if (usuario.getRoles().contains("ADMIN")) {
				return usuario;
			}
		}
		return null;
	}
	
	public Optional<Usuario> buscarPorEmail(String email){
		return Optional.of(repositorioUsuario.findByEmail(email));
	}
	
	public Usuario crearUsuario(Usuario usuario){
		String passwordCodificado = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(passwordCodificado);
		repositorioUsuario.create(usuario);
		return usuario;
	}
}
