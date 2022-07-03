package cl.diego.java.biblioteca.servicio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.diego.java.biblioteca.modelo.Usuario;

@Service
public class ServicioDetallesUsuario implements UserDetailsService{
	
	@Autowired
	private ServicioUsuario servicioUsuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOp = servicioUsuario.buscarPorEmail(username);
		if(usuarioOp.isPresent()) {
			return construirObjetoUserDetails(usuarioOp.get());
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

	private UserDetails construirObjetoUserDetails(Usuario usuario) {
		User.UserBuilder builder = User.builder();
		Collection<GrantedAuthority> roles = construirRolesUsuario(usuario);
		builder
			.username(usuario.getEmail())
			.password(usuario.getPassword())
			.authorities(roles)
		;
		return builder.build();
	}

	private Collection<GrantedAuthority> construirRolesUsuario(Usuario usuario) {
		HashSet<GrantedAuthority> roles = new HashSet<>();
		for (String rol : usuario.getRoles().split(",")) {
			roles.add(new SimpleGrantedAuthority(rol));
		}
		return roles;
	}
}
