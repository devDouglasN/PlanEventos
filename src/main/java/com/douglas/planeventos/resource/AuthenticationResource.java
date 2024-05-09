package com.douglas.planeventos.resource;

import com.douglas.planeventos.domain.dtos.CredentialDTO;
import com.douglas.planeventos.domain.dtos.LoginResponseDTO;
import com.douglas.planeventos.infra.security.TokenService;
import com.douglas.planeventos.infra.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity login(@RequestBody @Valid CredentialDTO credentialDTO) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(credentialDTO.email(), credentialDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((UserSS) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
