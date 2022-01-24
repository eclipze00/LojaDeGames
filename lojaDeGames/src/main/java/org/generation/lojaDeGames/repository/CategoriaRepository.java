package org.generation.lojaDeGames.repository;

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 24/01/2022
 * @version 0.01
  */

import java.util.List;

import org.generation.lojaDeGames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria>findAllByGeneroContainingIgnoreCase(String genero);


}
