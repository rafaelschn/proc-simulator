package com.schnrfl.procsimulator.simulation;

import java.io.IOException;

/**
 * Classe responsável pelo encapsulamento do resultado da simulação
 */
public class ResultadoProcessos implements Resultado {

	/**
	 * Número máximo de processos na fila de prontos
	 */
	private long numeroMaximoDeProcessosNaFilaDeProntos;

	private long totalDeTempoAcumuladoEmFila;

	private long totalDeExecucoes;
	
	private long duracaoDaSimulacao;
	
	private long totalDeProcessosConcluidos;

	private long duracaoRealDaSimulacao;
	
	private DadosDoGraficoDaFilaDeProntos dadosGraficoFilaDeProntos;

	public ResultadoProcessos() {
		this.numeroMaximoDeProcessosNaFilaDeProntos = 0;
		
		totalDeTempoAcumuladoEmFila = 0;
		totalDeExecucoes = 0;
		duracaoDaSimulacao = 0;
		totalDeProcessosConcluidos = 0;
		
		dadosGraficoFilaDeProntos = new DadosDoGraficoDaFilaDeProntos();
	}
	
	public DadosDoGraficoDaFilaDeProntos getDadosGraficoFilaDeProntos() {
		return dadosGraficoFilaDeProntos;
	}

	/**
	 * Tempo médio de espera na fila de prontos
	 */
	public double getTempoMedioDeEsperaNaFilaDeProntos() {
		double tempoMedioDeEsperaNaFilaDeProntos = (double) totalDeTempoAcumuladoEmFila / totalDeExecucoes;
		
		return tempoMedioDeEsperaNaFilaDeProntos;
	}

	/**
	 * Número de processos concluídos por unidade de tempo
	 */
	public double getNumeroDeProcessosConcluidosPorUnidadeDeTempo() {
		double numeroDeProcessosConcluidosPorUnidadeDeTempo = (double) totalDeProcessosConcluidos / duracaoDaSimulacao;
		
		return numeroDeProcessosConcluidosPorUnidadeDeTempo;
	}

	/**
	 * Número médio de execuções
	 */
	public double getNumeroMedioDeExecucoes() {
		double numeroMedioDeExecucoes = (double) totalDeExecucoes / totalDeProcessosConcluidos;
		
		return numeroMedioDeExecucoes;
	}
	
	public double getNumeroMedioDeExecucoesPorUnidadeDeTempo() {
		double numeroMedioDeExecucoes = (double) totalDeExecucoes / duracaoDaSimulacao;
		
		return numeroMedioDeExecucoes;
	}

	public long getNumeroMaximoDeProcessosNaFilaDeProntos() {
		return numeroMaximoDeProcessosNaFilaDeProntos;
	}
	
	public void setNumeroMaximoDeProcessosNaFilaDeProntos(long numeroMaximoDeProcessosNaFilaDeProntos) {
		this.numeroMaximoDeProcessosNaFilaDeProntos = numeroMaximoDeProcessosNaFilaDeProntos;
	}
	
	public void acumulaTempoEmFila(long tempoEmFila) {
		this.totalDeTempoAcumuladoEmFila += tempoEmFila;
	}
	
	public void acumulaExecucoes(long execucoes) {
		this.totalDeExecucoes += execucoes;
	}
	
	public void setDuracao(long duracao) {
		this.duracaoDaSimulacao = duracao;
	}
	
	public void setDuracaoReal(long duracaoRealDaSimulacao) {
		this.duracaoRealDaSimulacao = duracaoRealDaSimulacao;
	}
	
	public void concluiuProcesso() {
		++totalDeProcessosConcluidos;
	}
	
	public String geraHtml() throws IOException {
		return new GeradorHtml(this).gera();
	}
	
	public void informaEstadoDaFilaDeProntos(Long instante, Long quantidadeEmFila) {
		dadosGraficoFilaDeProntos.adiciona(instante, quantidadeEmFila);
	}
	
	public long getDuracaoDaSimulacao() {
		return duracaoDaSimulacao;
	}
	
	public long getDuracaoRealDaSimulacao() {
		return duracaoRealDaSimulacao;
	}
	
	public long getTotalDeTempoAcumuladoEmFila() {
		return totalDeTempoAcumuladoEmFila;
	}
	
	public long getTotalDeProcessosConcluidos() {
		return totalDeProcessosConcluidos;
	}
	
	public long getTotalDeExecucoes() {
		return totalDeExecucoes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append("Resultado da Simulação:");
		sb.append("\nTempo de simulação: " + duracaoDaSimulacao + " unidades de tempo");
		sb.append("\nTempo real de simulação: " + duracaoRealDaSimulacao + "ms");
		sb.append("\nTotal de tempo na fila: " + totalDeTempoAcumuladoEmFila + " unidades de tempo");
		sb.append("\nTotal de processos concluídos: " + totalDeProcessosConcluidos);
		sb.append("\nTotal de execuções: " + totalDeExecucoes);
		sb.append("\n");
		sb.append("\nTempo médio de espera na fila de prontos: " + getTempoMedioDeEsperaNaFilaDeProntos() + " unidades de tempo");
		sb.append("\nNúmero de processos concluídos por unidade de tempo: " + getNumeroDeProcessosConcluidosPorUnidadeDeTempo());
		sb.append("\nNúmero médio de execuções: " + getNumeroMedioDeExecucoes() + " a cada unidade de tempo");
		sb.append("\nNúmero máximo de processos na fila de prontos: " + getNumeroMaximoDeProcessosNaFilaDeProntos());

		return sb.toString();
	}
	
}
