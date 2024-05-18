package com.douglas.planeventos.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public void startDB() {

		Evento e1 = new Evento();
		e1.setDataEvento(LocalDate.now());
		e1.setLocal("Local 1");
		e1.setDescricao("Descrição evento 1");
		e1.setStatus(StatusEvento.ABERTO);
		e1.setHorario(HorarioEvento.NOITE);

		List<Organizador> organizadores = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();


		Organizador org1 = new Organizador(null, "Douglas Nascimento", "550.458.110-96", "douglass@mail.com", encoder.encode("123"));
		org1.addPerfil(Perfil.ADMIN);
		organizadores.add(org1);

		Organizador org2 = new Organizador(null, "Clara Pereira", "349.035.750-71", "claraP@mail.com", encoder.encode("123"));
		organizadores.add(org2);

		Organizador org3 = new Organizador(null, "Guilherme Souza", "637.091.100-33", "guilhermeS@mail.com", encoder.encode("123"));
		organizadores.add(org3);

		Organizador org4 = new Organizador(null, "Rafael Costa", "457.396.390-18", "rafaelC@mail.com", encoder.encode("123"));
		organizadores.add(org4);

		Organizador org5 = new Organizador(null, "Carolina Lima", "715.216.650-10", "carolL@mail.com", encoder.encode("123"));
		organizadores.add(org5);

		// Adição dos participantes
		Participante par1 = new Participante(null, "Mahatma Gandhi", "260.088.730-09", "mahatmaG@mail.com", encoder.encode("123"));
		participantes.add(par1);

		Participante par2 = new Participante(null, "Frida Kahlo", "403.273.480-11", "mFridaK@mail.com", encoder.encode("123"));
		participantes.add(par2);

		Participante par3 = new Participante(null, "Michael Jordan", "349.513.400-03", "michaelJ@mail.com", encoder.encode("123"));
		participantes.add(par3);

		Participante par4 = new Participante(null, "Nelson Mandela", "708.497.120-33", "nelsonM@mail.com", encoder.encode("123"));
		participantes.add(par4);

		Participante par5 = new Participante(null, "Elon Musk", "600.590.530-97", "elonM@mail.com", encoder.encode("123"));
		participantes.add(par5);

		// Adição das listas de organizadores e participantes ao evento
		e1.setOrganizadores(organizadores);
		e1.setParticipantes(participantes);

		// Salva os organizadores e participantes
		pessoaRepository.saveAll(organizadores);
		pessoaRepository.saveAll(participantes);

		// Salva o evento
		eventoRepository.save(e1);
	}
}
