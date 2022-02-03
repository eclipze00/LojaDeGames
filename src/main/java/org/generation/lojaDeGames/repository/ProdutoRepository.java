package org.generation.lojaDeGames.repository;
/*
 * @author Darllan Lopes Pinto
 * @since 24/01/2022
 * @version 0.01
  */

import java.util.List;

import org.generation.lojaDeGames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
		public List<Produto>findAllByNomeContainingIgnoreCase(String nome);


}
