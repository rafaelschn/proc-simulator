package com.schnrfl.procsimulator.program;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.GeradorArquivoDat;
import com.schnrfl.procsimulator.generation.GeradorEstaticoHomologacao;
import com.schnrfl.procsimulator.generation.ParserArquivoDat;
import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;
import com.schnrfl.procsimulator.simulation.Simulador;

public class MainTest {

	@Test
	public void deveTratarTodosEventosEProcessos() {

		Simulador simulador = new Simulador();

		try {
			 Gerador gerador = new GeradorArquivoDat("files/entrada.dat", new ParserArquivoDat(), new FileLogger());
			//Gerador gerador = new GeradorEstaticoHomologacao();

			SimulacaoProcessos simulacao = new SimulacaoProcessos(gerador);

			simulador.iniciar(simulacao);
			
			assertEquals(0, simulacao.getFilaDeEventos().size());
			assertEquals(0, simulacao.getFilaDeProntos().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
