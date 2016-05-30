package com.schnrfl.procsimulator.program;

import java.io.IOException;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.GeradorArquivoDat;
import com.schnrfl.procsimulator.generation.GeradorEstaticoHomologacao;
import com.schnrfl.procsimulator.generation.ParserArquivoDat;
import com.schnrfl.procsimulator.simulation.Simulacao;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;
import com.schnrfl.procsimulator.simulation.Simulador;

public class Main {

	public static void main(String[] args) {
		
		//Criação do simulador
		Simulador simulador = new Simulador();
		
		try {
			//Criação do gerador de eventos
			Gerador gerador = new GeradorArquivoDat("files/entrada.dat", new ParserArquivoDat());
			//Gerador gerador = new GeradorEstaticoHomologacao();
			
			//Criação do Motor de Simulação com eventos exógenos oriundos do arquivo .DAT
			Simulacao simulacao = new SimulacaoProcessos(gerador);
			
			//Inicia a simulação
			simulador.iniciar(simulacao);
			
			//Mostra os resultados da simulação
			System.out.println(simulador.getResultado());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
