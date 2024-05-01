package com.douglas.fastTrackSupport.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.fastTrackSupport.domain.Chamado;
import com.douglas.fastTrackSupport.domain.Cliente;
import com.douglas.fastTrackSupport.domain.Tecnico;
import com.douglas.fastTrackSupport.domain.enums.Perfil;
import com.douglas.fastTrackSupport.domain.enums.Prioridade;
import com.douglas.fastTrackSupport.domain.enums.Status;
import com.douglas.fastTrackSupport.repositories.ChamadoRepository;
import com.douglas.fastTrackSupport.repositories.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Tecnico tec1 = new Tecnico(null, "Douglas Nascimento", "109.099.870-84", "douglas@mail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Clara Pereira", "859.322.510-18", "clara@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Guilherme Souza", "663.964.430-40", "guilherme@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Rafael Costa", "673.029.140-01", "rafael@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Carolina Lima", "743.822.010-98", "linus@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Mahatma Gandhi", "446.882.380-10", "mahatma@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "MFrida Kahlo", "658.327.270-96", "mFrida@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Michael Jordan", "517.396.860-55", "michael@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Nelson Mandela", "764.309.390-30", "nelson@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Elon Musk", "852.388.520-02", "elon@mail.com", encoder.encode("123"));


		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ENCERRADO, "Chamado 1", "Teste chamado 1", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
		Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
		Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
		Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 7", "Teste chamado 6", tec1, cli5);

		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
}
