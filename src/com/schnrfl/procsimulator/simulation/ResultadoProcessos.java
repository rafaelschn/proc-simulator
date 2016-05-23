package com.schnrfl.procsimulator.simulation;

/**
 * Classe responsável pelo encapsulamento do resultado da simulação
 */
public class ResultadoProcessos implements Resultado {

	/**
	 * Tempo médio de espera na fila de prontos
	 */
	private long tempoMedioDeEsperaNaFilaDeProntos;

	/**
	 * Número de processos concluídos por unidade de tempo
	 */
	private int numeroDeProcessosConcluidosPorUnidadeDeTempo;

	/**
	 * Número médio de execuções
	 */
	private long numeroMedioDeExecucoes;

	/**
	 * Número máximo de processos na fila de prontos
	 */
	private int numeroMaximoDeProcessosNaFilaDeProntos;

	public ResultadoProcessos(long tempoMedioDeEsperaNaFilaDeProntos, int numeroDeProcessosConcluidosPorUnidadeDeTempo,
			long numeroMedioDeExecucoes, int numeroMaximoDeProcessosNaFilaDeProntos) {
		this.tempoMedioDeEsperaNaFilaDeProntos = tempoMedioDeEsperaNaFilaDeProntos;
		this.numeroDeProcessosConcluidosPorUnidadeDeTempo = numeroDeProcessosConcluidosPorUnidadeDeTempo;
		this.numeroMedioDeExecucoes = numeroMedioDeExecucoes;
		this.numeroMaximoDeProcessosNaFilaDeProntos = numeroMaximoDeProcessosNaFilaDeProntos;
	}

	public long getTempoMedioDeEsperaNaFilaDeProntos() {
		return tempoMedioDeEsperaNaFilaDeProntos;
	}

	public int getNumeroDeProcessosConcluidosPorUnidadeDeTempo() {
		return numeroDeProcessosConcluidosPorUnidadeDeTempo;
	}

	public long getNumeroMedioDeExecucoes() {
		return numeroMedioDeExecucoes;
	}

	public int getNumeroMaximoDeProcessosNaFilaDeProntos() {
		return numeroMaximoDeProcessosNaFilaDeProntos;
	}
	
}
