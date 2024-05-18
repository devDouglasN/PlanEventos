package com.douglas.planeventos.infra.security;

import java.io.IOException;
import java.util.Optional;

import com.douglas.planeventos.domain.Pessoa;
import com.douglas.planeventos.repositories.PessoaRepository;
import com.douglas.planeventos.services.UserDetailsServicelmpl;
import com.douglas.planeventos.services.exceptions.ValidationException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServicelmpl customUserDetailsService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recoverToken(request);
		if (token != null) {

			var email = tokenService.validateToken(token);

			Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
			if (pessoa.isPresent()) {

				UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
				var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else {
				throw new ValidationException("Usuário não encontrado ao tentar fazer login!");
			}

		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "").trim();
		}

		return null;
	}
}
