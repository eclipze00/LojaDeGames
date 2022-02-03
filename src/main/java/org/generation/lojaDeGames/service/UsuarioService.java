package org.generation.lojaDeGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.lojaDeGames.model.UserLogin;
import org.generation.lojaDeGames.model.Usuario;
import org.generation.lojaDeGames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> cadastrarUsuario (Usuario usuario) {

		Optional<Usuario> optional = repository.findByUsuario(usuario.getUsuario());

		if (optional.isPresent()) {
			return Optional.empty();
		}

		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();

		String senhaEncoder = enconder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return Optional.ofNullable(repository.save(usuario));
	}

	public Optional<UserLogin> login (Optional<UserLogin> user) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (enconder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encondeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encondeAuth);

				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());

				return user;
			}
		}

		return null;
	}
}