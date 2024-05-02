package com.douglas.planeventos.services;

import java.time.LocalDate;
import java.util.Arrays;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.enums.HorarioEvento;
import com.douglas.planeventos.enums.Perfil;
import com.douglas.planeventos.enums.StatusEvento;
import com.douglas.planeventos.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.planeventos.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Organizador org1 = new Organizador(null, "Douglas Nascimento", "123.456.789-01", "douglas@mail.com", encoder.encode("123"));
		org1.addPerfil(Perfil.ADMIN);
		Organizador org2 = new Organizador(null, "Clara Pereira", "123.456.789-02", "clara@mail.com", encoder.encode("123"));
		Organizador org3 = new Organizador(null, "Guilherme Souza", "123.456.789-03", "guilherme@mail.com", encoder.encode("123"));
		Organizador org4 = new Organizador(null, "Rafael Costa", "123.456.789-04", "rafael@mail.com", encoder.encode("123"));
		Organizador org5 = new Organizador(null, "Carolina Lima", "123.456.789-05", "linus@mail.com", encoder.encode("123"));

		Participante par1 = new Participante(null, "Mahatma Gandhi", "123.456.789-06", "mahatma@mail.com", encoder.encode("123"));
		Participante par2 = new Participante(null, "Frida Kahlo", "123.456.789-07", "mFrida@mail.com", encoder.encode("123"));
		Participante par3 = new Participante(null, "Michael Jordan", "123.456.789-08", "michael@mail.com", encoder.encode("123"));
		Participante par4 = new Participante(null, "Nelson Mandela", "123.456.789-09", "nelson@mail.com", encoder.encode("123"));
		Participante par5 = new Participante(null, "Elon Musk", "123.456.789-10", "elon@mail.com", encoder.encode("123"));


		Evento e1 = new Evento(null, LocalDate.now(), "Local 1", "Descrição evento 1", StatusEvento.ABERTO, HorarioEvento.MANHA, org1, par1);
		Evento e2 = new Evento(null, LocalDate.now(), "Local 2", "Descrição evento 2", StatusEvento.ABERTO, HorarioEvento.TARDE, org1, par2);
		Evento e3 = new Evento(null, LocalDate.now(), "Local 3", "Descrição evento 3", StatusEvento.ENCERRADO, HorarioEvento.NOITE, org2, par3);
		Evento e4 = new Evento(null, LocalDate.now(), "Local 4", "Descrição evento 4", StatusEvento.ABERTO, HorarioEvento.MANHA, org3, par3);
		Evento e5 = new Evento(null, LocalDate.now(), "Local 5", "Descrição evento 5", StatusEvento.ANDAMENTO, HorarioEvento.TARDE, org2, par1);
		Evento e6 = new Evento(null, LocalDate.now(), "Local 6", "Descrição evento 6", StatusEvento.ANDAMENTO, HorarioEvento.NOITE, org1, par5);

		pessoaRepository.saveAll(Arrays.asList(org1, org2, org3, org4, org5, par1, par2, par3, par4, par5));
		eventoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6));
	}
}
