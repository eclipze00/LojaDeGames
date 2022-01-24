package org.generation.lojaDeGames.model;

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 24/01/2022
 * @version 0.01
  */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String genero;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String secoesEspecias;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSecoesEspecias() {
		return secoesEspecias;
	}

	public void setSecoesEspecias(String secoesEspecias) {
		this.secoesEspecias = secoesEspecias;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
		

}
