package com.douglas.planeventos.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

		Set<Organizador> organizadores = new HashSet<>();
		Set<Participante> participantes = new HashSet<>();

		Organizador org1 = new Organizador(null, "Douglas Nascimento", "550.458.110-96", "douglas.nascimento@mail.com", encoder.encode("123"));
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

		Participante par1 = new Participante(null, "Mahatma Gandhi", "947.351.810-24", "mahatmaG@mail.com", encoder.encode("123"));
		participantes.add(par1);

		Participante par2 = new Participante(null, "Frida Kahlo", "858.393.320-03", "mFridaK@mail.com", encoder.encode("123"));
		participantes.add(par2);

		Participante par3 = new Participante(null, "Michael Jordan", "957.061.560-50", "michaelJ@mail.com", encoder.encode("123"));
		participantes.add(par3);

		Participante par4 = new Participante(null, "Nelson Mandela", "330.006.050-88", "nelsonM@mail.com", encoder.encode("123"));
		participantes.add(par4);

		Participante par5 = new Participante(null, "Elon Musk", "660.742.700-66", "elonM@mail.com", encoder.encode("123"));
		participantes.add(par5);

		Participante par6 = new Participante(null, "Ana Silva", "712.301.810-91", "ana@mail.com", encoder.encode("123"));
		participantes.add(par6);

		Participante par7 = new Participante(null, "Carlos Santos", "940.998.260-07", "carlos@mail.com", encoder.encode("123"));
		participantes.add(par7);

		Participante par8 = new Participante(null, "Bruna Costa", "453.906.500-93", "bruna@mail.com", encoder.encode("123"));
		participantes.add(par8);

		Participante par9 = new Participante(null, "João Oliveira", "261.184.070-96", "joao@mail.com", encoder.encode("123"));
		participantes.add(par9);

		Participante par10 = new Participante(null, "Mariana Ferreira", "309.641.370-38", "mariana@mail.com", encoder.encode("123"));
		participantes.add(par10);

		Participante par11 = new Participante(null, "Pedro Lima", "620.718.230-87", "pedro@mail.com", encoder.encode("123"));
		participantes.add(par11);

		Participante par12 = new Participante(null, "Lucas Almeida", "302.914.070-99", "lucas@mail.com", encoder.encode("123"));
		participantes.add(par12);

		Participante par13 = new Participante(null, "Fernanda Rocha", "585.182.950-84", "fernanda@mail.com", encoder.encode("123"));
		participantes.add(par13);

		Participante par14 = new Participante(null, "Gabriel Sousa", "551.100.280-10", "gabriel@mail.com", encoder.encode("123"));
		participantes.add(par14);

		Participante par15 = new Participante(null, "Juliana Mendes", "834.286.020-56", "juliana@mail.com", encoder.encode("123"));
		participantes.add(par15);

		Participante par16 = new Participante(null, "Renato Carvalho", "768.396.200-77", "renato@mail.com", encoder.encode("123"));
		participantes.add(par16);

		Participante par17 = new Participante(null, "Patrícia Gomes", "415.043.870-66", "patricia@mail.com", encoder.encode("123"));
		participantes.add(par17);

		Participante par18 = new Participante(null, "Rafael Martins", "485.844.380-92", "rafael@mail.com", encoder.encode("123"));
		participantes.add(par18);

		Participante par19 = new Participante(null, "Amanda Oliveira", "439.115.880-97", "amanda@mail.com", encoder.encode("123"));
		participantes.add(par19);

		Participante par20 = new Participante(null, "Thiago Rodrigues", "357.234.620-75", "thiago@mail.com", encoder.encode("123"));
		participantes.add(par20);

		Participante par21 = new Participante(null, "Julio César", "241.343.390-22", "julio@mail.com", encoder.encode("123"));
		participantes.add(par21);

		Participante par22 = new Participante(null, "Larissa Albuquerque", "554.616.080-27", "larissa@mail.com", encoder.encode("123"));
		participantes.add(par22);

		Participante par23 = new Participante(null, "Ricardo Silva", "734.261.510-22", "ricardo@mail.com", encoder.encode("123"));
		participantes.add(par23);

		Participante par24 = new Participante(null, "Marcos Vinícius", "315.846.980-47", "marcos@mail.com", encoder.encode("123"));
		participantes.add(par24);

		Participante par25 = new Participante(null, "Paula Teixeira", "642.022.720-80", "paula@mail.com", encoder.encode("123"));
		participantes.add(par25);

		e1.setOrganizadores(organizadores);
		e1.setParticipantes(participantes);

		pessoaRepository.saveAll(organizadores);
		pessoaRepository.saveAll(participantes);

	}
}
