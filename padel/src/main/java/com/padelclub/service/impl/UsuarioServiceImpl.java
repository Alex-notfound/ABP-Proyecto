package com.padelclub.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padelclub.commons.GenericServiceImpl;
import com.padelclub.dao.api.UsuarioRepository;
import com.padelclub.model.Authority;
import com.padelclub.model.Usuario2;
import com.padelclub.service.api.UsuarioService;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario2, Long>
		implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public CrudRepository<Usuario2, Long> getDao() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		Usuario2 appUser = usuarioRepository.findByDni(username)
				.orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

		// Mapear nuestra lista de Authority con la de spring security
		List grantList = new ArrayList();
		for (Authority authority : appUser.getAuthority()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}

		// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		UserDetails user = (UserDetails) new User(appUser.getDni(), appUser.getPassword(), grantList);
		return user;
	}

	@Override
	public Usuario2 getUsuario(Principal usuario) {
		return usuarioRepository.findByDni(usuario.getName()).get();
	}

}
