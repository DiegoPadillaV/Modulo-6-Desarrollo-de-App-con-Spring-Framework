package cl.diego.java.cms.service;

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
import org.springframework.security.authentication.AuthenticationManager;

import cl.diego.java.cms.model.Usuario;

@Service
public class MiServicioUsuario implements UserDetailsService{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOp = usuarioService.buscarPorEmail(username);
		if(usuarioOp.isPresent()) {
			//Ejecuto codigo en caso que tenga un valor
			//Transformo un objeto del tipo Usuario a uno de UserDetails (Spring Security)
			return construirObjetoUserDetails(usuarioOp.get());
		} else {
			//En caso que sea NULO
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

	private UserDetails construirObjetoUserDetails(Usuario usuario) {
		User.UserBuilder builder = User.builder();
		Collection<GrantedAuthority> roles = construirRolesDeUsuario(usuario);
		builder
			.username(usuario.getEmail())
			.password(usuario.getPassword())
			.authorities(roles);
		return builder.build();
	}

	private Collection<GrantedAuthority> construirRolesDeUsuario(Usuario usuario) {
		HashSet<GrantedAuthority> roles = new HashSet<>();
		// Ej roles = ADMIN,USER => split(",") => [ADMIN, USER]
		for(String rol : usuario.getRoles().split(",")) {
			roles.add(new SimpleGrantedAuthority(rol));
		}
		return roles;
	}
}
