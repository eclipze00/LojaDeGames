package org.generation.lojaDeGames.security;

/* 
 * @author Júlia Inoscência O. dos Santos
 * @since 31/01/2022
 * @version 1.2
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired //o spring que vai gerenciar o método
	private UserDetailsService userDetailsService;
	
	//@Override //sobrescreve um método da classe "mãe"
	//método que será chamado durante a autenticação
	/*protected void configure(AuthenticationManagerBuilder auth) throws Exception{ 
		auth.userDetailsService(userDetailsService);
	}*/
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(userDetailsService);

		auth.inMemoryAuthentication().withUser("gameshop").password(passwordEnconder().encode("gameshop"))
		 .authorities("ROLE_ADMIN");
 	}
	
	@Bean //para o Spring saber que vai gerenciar um método que vêm de uma classe importada(biblioteca)
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/usuario/logar").permitAll() //libera endpoints para que não seja preciso acessá-los com Token
		.antMatchers("/usuario/cadastrar").permitAll()
		.antMatchers("/usuario/atualizar").permitAll()
		.antMatchers("/categoria/all").permitAll()
		.antMatchers("/produto/all").permitAll()
		.anyRequest().authenticated() //todas as outras requesições precisaram de autenticação
		.and().httpBasic() //utiliza o padrão Basic para gerar o Token
		.and().sessionManagement() //indica o tipo de sessão que será utilizada
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Stateless não guarda nenhuma sessão -> API Rest tem que ser assim
		.and().cors() //habilita o cors -> Cross-Origin Resource Sharing
		.and().csrf().disable(); // desabilita o Cross-site request forgery um dos ataques mais comuns da Web
	}
	
	
}
