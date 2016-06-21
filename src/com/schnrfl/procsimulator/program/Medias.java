package com.schnrfl.procsimulator.program;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.GeradorArquivoDat;
import com.schnrfl.procsimulator.generation.ParserArquivoDat;
import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.NoopLogger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;
import com.schnrfl.procsimulator.simulation.Simulacao;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;
import com.schnrfl.procsimulator.simulation.Simulador;

public class Medias {

	public static void main(String[] args) {

		FileLogger logger = new NoopLogger();
		Simulador simulador = new Simulador(logger);
		int quantidadeDeRodadas = 100;
		
		double tempoMedioDeEsperaNaFilaDeProntos = 0;
		double numeroDeProcessosConcluidosPorUnidadeDeTempo = 0;
		double numeroMedioDeExecucoes = 0;
		double numeroMedioDeExecucoesPorUnidadeDeTempo = 0;
		long numeroMaximoDeProcessosNaFilaDeProntos = 0;
		
		try {
			
			long inicio = System.currentTimeMillis();
			for (int i = 0; i < quantidadeDeRodadas; i++) {
				
				Gerador gerador = new GeradorArquivoDat("files/randomico500.dat", new ParserArquivoDat(), logger);
				//Gerador gerador = new GeradorArquivoDat("files/entrada.dat", new ParserArquivoDat(), logger);
				
				Simulacao simulacao = new SimulacaoProcessos(gerador);
				
				simulador.iniciar(simulacao);
				
				ResultadoProcessos resultadoSimulacao = ((ResultadoProcessos)simulador.getResultado());
				
				tempoMedioDeEsperaNaFilaDeProntos += resultadoSimulacao.getTempoMedioDeEsperaNaFilaDeProntos();
				numeroDeProcessosConcluidosPorUnidadeDeTempo += resultadoSimulacao.getNumeroDeProcessosConcluidosPorUnidadeDeTempo();
				numeroMedioDeExecucoes += resultadoSimulacao.getNumeroMedioDeExecucoes();
				numeroMedioDeExecucoesPorUnidadeDeTempo += resultadoSimulacao.getNumeroMedioDeExecucoesPorUnidadeDeTempo();
				numeroMaximoDeProcessosNaFilaDeProntos += resultadoSimulacao.getNumeroMaximoDeProcessosNaFilaDeProntos();
				
			}
			long fim = System.currentTimeMillis();
			
			double mediaTempoMedioDeEsperaNaFilaDeProntos = tempoMedioDeEsperaNaFilaDeProntos / quantidadeDeRodadas;
			double mediaNumeroDeProcessosConcluidosPorUnidadeDeTempo = numeroDeProcessosConcluidosPorUnidadeDeTempo / quantidadeDeRodadas;
			double mediaNumeroMedioDeExecucoes = numeroMedioDeExecucoes / quantidadeDeRodadas;
			double mediaNumeroMedioDeExecucoesPorUnidadeDeTempo = numeroMedioDeExecucoesPorUnidadeDeTempo / quantidadeDeRodadas;
			double mediaNumeroMaximoDeProcessosNaFilaDeProntos = (double) numeroMaximoDeProcessosNaFilaDeProntos / quantidadeDeRodadas;
			
			System.out.println("Médias de Resultado da simulação após " + quantidadeDeRodadas + " rodadas (" + ( fim - inicio ) + "ms)");
			System.out.println();
			System.out.println("Tempo médio de espera na fila de prontos: " + mediaTempoMedioDeEsperaNaFilaDeProntos + " unidades de tempo");
			System.out.println("Número de processos concluídos por unidade de tempo: " + mediaNumeroDeProcessosConcluidosPorUnidadeDeTempo);
			System.out.println("Número médio de execuções: " + mediaNumeroMedioDeExecucoes + " por processo / " + mediaNumeroMedioDeExecucoesPorUnidadeDeTempo + " por unidade de tempo");
			System.out.println("Número máximo de processos na fila de prontos: " + mediaNumeroMaximoDeProcessosNaFilaDeProntos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
