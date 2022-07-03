package cl.diego.java.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.processor.AuthorizeAclAttrProcessor;

import cl.diego.java.cms.service.MiServicioUsuario;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MiServicioUsuario miServicioUsuario;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(miServicioUsuario)
			.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests(authorize -> authorize
				.mvcMatchers("/", "/nosotros", "/contacto", "/admin/instalar").permitAll() // Permite acceso sin autenticacion
				.mvcMatchers("/admin/index", "/admin/reporte").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/ingreso")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.mvcMatchers("/img/**", "/css/**", "/js/**")
			;
	}
	
	
}
