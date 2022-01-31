package org.generation.lojaDeGames.security;

import java.util.Optional;

import org.generation.lojaDeGames.model.Usuario;
import org.generation.lojaDeGames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
		private UsuarioRepository userRepository;
	
	@Override
		public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
		
		
		Optional<Usuario>user=userRepository.findByUsuario(userName); // Procura no repositorio o usuario pelo nome
		user.orElseThrow(()->new UsernameNotFoundException(userName + "Not Found")); // se não achar imprime a mensagem
		return user.map(UserDetailsImpl::new).get(); // encaminha para gerar um novo usuario
	}

}
