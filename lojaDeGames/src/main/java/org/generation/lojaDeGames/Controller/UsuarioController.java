package org.generation.lojaDeGames.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojaDeGames.model.UserLogin;
import org.generation.lojaDeGames.model.Usuario;
import org.generation.lojaDeGames.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins="*",allowedHeaders = "*")

	public class UsuarioController {
	
	@Autowired
		private UsuarioService usuarioService;
	
	@PostMapping("/logar")
		public ResponseEntity<UserLogin>Authentication(@RequestBody Optional<UserLogin>user){
		return usuarioService.Logar(user).map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	} 
	
	@PostMapping("/cadastrar")
		public ResponseEntity<Usuario>Post(@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(201).body(usuarioService.CadastrarUsuario(usuario));
		
	}
		
	

}
