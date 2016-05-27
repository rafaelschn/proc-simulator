package com.schnrfl.procsimulator.simulation;

/**
 * Classe responsável pelo encapsulamento do resultado da simulação
 */
public class ResultadoProcessos implements Resultado {

	/**
	 * Tempo médio de espera na fila de prontos
	 */
	private double tempoMedioDeEsperaNaFilaDeProntos;

	/**
	 * Número de processos concluídos por unidade de tempo
	 */
	private double numeroDeProcessosConcluidosPorUnidadeDeTempo;

	/**
	 * Número médio de execuções
	 */
	private double numeroMedioDeExecucoes;

	/**
	 * Número máximo de processos na fila de prontos
	 */
	private long numeroMaximoDeProcessosNaFilaDeProntos;

	public ResultadoProcessos(double tempoMedioDeEsperaNaFilaDeProntos, double numeroDeProcessosConcluidosPorUnidadeDeTempo,
			double numeroMedioDeExecucoes, long numeroMaximoDeProcessosNaFilaDeProntos) {
		this.tempoMedioDeEsperaNaFilaDeProntos = tempoMedioDeEsperaNaFilaDeProntos;
		this.numeroDeProcessosConcluidosPorUnidadeDeTempo = numeroDeProcessosConcluidosPorUnidadeDeTempo;
		this.numeroMedioDeExecucoes = numeroMedioDeExecucoes;
		this.numeroMaximoDeProcessosNaFilaDeProntos = numeroMaximoDeProcessosNaFilaDeProntos;
	}

	public double getTempoMedioDeEsperaNaFilaDeProntos() {
		return tempoMedioDeEsperaNaFilaDeProntos;
	}

	public double getNumeroDeProcessosConcluidosPorUnidadeDeTempo() {
		return numeroDeProcessosConcluidosPorUnidadeDeTempo;
	}

	public double getNumeroMedioDeExecucoes() {
		return numeroMedioDeExecucoes;
	}

	public long getNumeroMaximoDeProcessosNaFilaDeProntos() {
		return numeroMaximoDeProcessosNaFilaDeProntos;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append("Resultado da Simulação:");
		sb.append("\nTempo médio de espera na fila de prontos: " + tempoMedioDeEsperaNaFilaDeProntos);
		sb.append("\nNúmero de processos concluídos por unidade de tempo: " + numeroDeProcessosConcluidosPorUnidadeDeTempo);
		sb.append("\nNúmero médio de execuções: " + numeroMedioDeExecucoes);
		sb.append("\nNúmero máximo de processos na fila de prontos: " + numeroMaximoDeProcessosNaFilaDeProntos);

		return sb.toString();
	}
	
}
