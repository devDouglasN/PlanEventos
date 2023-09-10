package com.douglas.fastTrackSupport;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.fastTrackSupport.domain.Chamado;
import com.douglas.fastTrackSupport.domain.Cliente;
import com.douglas.fastTrackSupport.domain.Tecnico;
import com.douglas.fastTrackSupport.domain.enums.Perfil;
import com.douglas.fastTrackSupport.domain.enums.Prioridade;
import com.douglas.fastTrackSupport.domain.enums.Status;
import com.douglas.fastTrackSupport.repositories.ChamadoRepository;
import com.douglas.fastTrackSupport.repositories.ClienteRepository;
import com.douglas.fastTrackSupport.repositories.TecnicoRepository;

@SpringBootApplication
public class FasttracksupportApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(FasttracksupportApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Douglas Nascimento", "45854578", "douglas@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Léo Messi", "85692458", "messi@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
