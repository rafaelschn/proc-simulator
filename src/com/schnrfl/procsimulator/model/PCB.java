package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa um bloco de controle de processo (Process Control Block)
 * 
 **/
public class PCB {

	/**
	 * Quantos ciclos executa a cada entrada na CPU
	 */
	private int timeSlice;

	/**
	 * Número do processo: PID
	 */
	private int numero;

	/**
	 * Tipo do Processo: CPU ou I/O Bound
	 */
	private ProcessoTipo tipo;

	/**
	 * Número total de ciclos que o processo necessita
	 */
	private int ciclosTotal;

	/**
	 * Ciclos já executados pelo processo
	 */
	private int ciclosExecutados;

	/**
	 * Instante no qual o processo chegou ao sistema - Verificar se muda ou
	 * sempre é o mesmo
	 */
	private long tempoDeChegada;

	/**
	 * Tempo inicial de um período na fila
	 */
	private long tempoFila;

	/**
	 * Tempo acumulado de espera na fila
	 */
	private long tempoDeEsperaAcumulado;

	//

	private long instanteDeChegadaNaFila;
	
	private long instanteDeAtendimentoNaFila;
	
	/**
	 * Quantas passagens esse processo sofreu
	 */
	private int passagensPelaFila;

	/**
	 * Numero de execucoes já realizadas pelo processo
	 */
	private int execucoes;

	public PCB(int numero, ProcessoTipo tipo, int timeSlice, long tempoDeChegada) {
		this.numero = numero;
		this.tipo = tipo;
		this.timeSlice = timeSlice;
		this.tempoDeChegada = tempoDeChegada;

		ciclosTotal = tipo.getTempoCicloCPU();
		ciclosExecutados = 0;
		tempoFila = 0;
		tempoDeEsperaAcumulado = 0;
		execucoes = 0;
	}

	public int getNumero() {
		return numero;
	}

	public ProcessoTipo getTipo() {
		return tipo;
	}

	public int getCiclosTotal() {
		return ciclosTotal;
	}

	public int getCiclosExecutados() {
		return ciclosExecutados;
	}

	public long getTempoChegada() {
		return tempoDeChegada;
	}

	public long getTempoFila() {
		return tempoFila;
	}

	public long getTempoDeEsperaAcumulado() {
		return tempoDeEsperaAcumulado;
	}

	public long getInstanteDeChegadaNaFila() {
		return instanteDeChegadaNaFila;
	}

	public long getInstanteDeAtendimentoNaFila() {
		return instanteDeAtendimentoNaFila;
	}
	
	public int getExecucoes() {
		return execucoes;
	}
	
	public long getTempoCicloES() {
		return tipo.getTempoCicloES();
	}

	public void chegouNaFilaNoInstante(long instante) {
		
		++passagensPelaFila;
		
		instanteDeChegadaNaFila = instante;
		
		System.out.println("[PID " + numero + " chegou na fila(" + passagensPelaFila + ") no instante " + instante + "]");
		
	}

	public void foiAtendidoNaFilaNoInstante(long instante) {
		instanteDeAtendimentoNaFila = instante;
		
		tempoFila = instanteDeAtendimentoNaFila - instanteDeChegadaNaFila;
		tempoDeEsperaAcumulado += tempoFila;
		
		System.out.println("[PID " + numero + " foi atendido na fila(" + passagensPelaFila + ") no instante " + instanteDeAtendimentoNaFila + " [TE: " + tempoFila + " TA: " + tempoDeEsperaAcumulado + "]");
	}
	
	public int executa() {

		if (ciclosExecutados >= ciclosTotal)
			throw new RuntimeException("PCB " + numero + " já finalizado!");

		int ciclosParaExecutar = getCiclosParaExecutar();

		ciclosExecutados += ciclosParaExecutar;
		
		System.out.println("[PID " + numero + " executou " + ciclosExecutados + " de " + ciclosTotal + " ciclos]");

		++execucoes;

		return ciclosParaExecutar;
	}

	public int getCiclosParaExecutar() {
		return Math.min(timeSlice, ciclosTotal - ciclosExecutados);
	}

	public boolean finalizou() {
		boolean finalizou = ciclosExecutados == ciclosTotal;
		
		if(finalizou)
			System.out.println("[PID " + numero + " finalizado!]");
		
		return finalizou;
	}

	public void contabilizar(ResultadoProcessos resultado, ProcessoEvento evento) {
		resultado.acumulaTempoEmFila(tempoDeEsperaAcumulado);
		resultado.acumulaExecucoes(execucoes);
		resultado.concluiuProcesso();
		
		System.out.println("[PID " + numero + " ficou no sistema durante " + getDuracaoEmSistema(evento.getTempoDoEvento()) + " unidades de tempo]");
	}
	
	private long getDuracaoEmSistema(long tempoDoEvento) {
		return tempoDoEvento - tempoDeChegada;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append("PCB");
		sb.append(" pid: " + numero);
		sb.append(" time slice: " + timeSlice);
		sb.append(" " + tipo);
		sb.append(" ciclos total: " + ciclosTotal);
		sb.append(" ciclos executados: " + ciclosExecutados);
		sb.append(" tempo de chegada: " + tempoDeChegada);
		sb.append(" tempo de fila: " + tempoFila);
		sb.append(" tempo acumulado de fila: " + tempoDeEsperaAcumulado);
		sb.append("]");

		return sb.toString();
	}

}
