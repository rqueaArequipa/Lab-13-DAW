package com.miempresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miempresa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServicio userDet;

	@Autowired
	private BCryptPasswordEncoder bcryp;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDet).passwordEncoder(bcryp);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/listarEmpleados").permitAll()
		.antMatchers("/agregarEmpleado").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/mostrarEmpleado").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/guardarEmpleado").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/editarEmpleado").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/eliminarEmpleado").access("hasRole('ROLE_ADMIN')")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.and()
		.logout()
		.permitAll();
	}
}
