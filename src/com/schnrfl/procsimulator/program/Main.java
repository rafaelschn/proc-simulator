package com.schnrfl.procsimulator.program;

import java.io.IOException;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.GeradorArquivoDat;
import com.schnrfl.procsimulator.generation.ParserArquivoDat;
import com.schnrfl.procsimulator.simulation.Simulacao;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;
import com.schnrfl.procsimulator.simulation.Simulador;

public class Main {

	public static void main(String[] args) {
		Simulador simulador = new Simulador();
		try {
			
			Gerador gerador = new GeradorArquivoDat("files/entrada.dat", new ParserArquivoDat());
			Simulacao simulacao = new SimulacaoProcessos(gerador);
			simulador.iniciar(simulacao);
			
			System.out.println("Tempo de simulação: " + simulador.getTempoExecucao());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
