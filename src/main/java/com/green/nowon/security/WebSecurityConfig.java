package com.green.nowon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity //Configuration 이라는 뜻
public class WebSecurityConfig {

	//spring-boot 1.5 -> 2.x -> 3.x 버전에 따라 설정이 조금씩 변경되고 있다.
	//2023/05/22 기준 spring-boot 2.7.12, springsecurtiy5 버전 적용 예
	AuthenticationManager ma;
	DaoAuthenticationProvider dao;
	
	@Autowired
    private CustomOAuth2UserService customOAuth2UserService;
	
	
		
	//url : 요청하는 것 (request) HttpSecurity
	
	@Bean	
	UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
		
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			
			.authorizeHttpRequests(authorize->
			authorize

					.antMatchers("/css/*","/js/*","/images/**","/css/*/*","/js/*/*").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/*","/sign**","/board/**","/map*","/find*","/notice-board/**","/load*","/goods/**").permitAll() // ()안에 있는 요소들은 다 허가할게요
					.antMatchers(HttpMethod.GET,"/board/*").permitAll() //get method 로 요청한 것만 허용한다
					.antMatchers(HttpMethod.POST,"/board/*").permitAll() //post method 로 요청한 것만 허용한다 
					.anyRequest() 
						.authenticated() 
								
			)
		  //.csrf(csrf->csrf.disable()) 
		  .formLogin( form-> form 
				  .loginPage("/login") 
				  .usernameParameter("userId") 
				  .passwordParameter("password")
				  .successHandler(mySuccessHandler())
				  .permitAll())
		  
		  .logout(logout -> logout
				 .logoutUrl("/logout")
		         .logoutSuccessUrl("/")
         		 .deleteCookies("JSESSIONID")
		      )
		  .oauth2Login(oauth->oauth
				  .loginPage("/login")
				  .defaultSuccessUrl("/")
				  .userInfoEndpoint().userService(customOAuth2UserService)
		  );
		return http.build();
	}
	
	@Bean
	AuthenticationSuccessHandler mySuccessHandler() {
		// 
		return new MySuccessHandler();
	}

	
}
