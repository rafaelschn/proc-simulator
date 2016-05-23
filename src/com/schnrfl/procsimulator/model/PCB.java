package com.schnrfl.procsimulator.model;

/**
 * Classe que representa um Process Control Block
 **/

public class PCB {
	
	/**
	 * Quantos ciclos executa
	 */
	private int quantum;
	
	/**
	 * Número do processo
	 */
	private int numero;
	
	/**
	 * Tipo do Processo: CPU ou I/O Bound
	 */
	private ProcessoTipo tipo;
	
	/**
	 * Número de ciclos do processo
	 */
	private int ciclosTotal;
	
	/**
	 * Ciclos já executados pelo processo
	 */
	private int ciclosExecutados;
	
	/**
	 * Tempo de chegada do processo
	 */
	private int tempoChegada;
	
	/**
	 * Tempo inicial de um período na fila
	 */
	private int tempoFila;
	
	/**
	 * Tempo de espera acumulado
	 */
	private int tempoDeEsperaAcumulado;

	public PCB(int numero, ProcessoTipo tipo, int quantum) {
		this.numero = numero;
		this.tipo = tipo;
		this.quantum = quantum;
		
		ciclosExecutados = 0;
		
		//?
		ciclosTotal = tipo.getTempoCicloCPU();
		
		//?
		tempoChegada = 0;
		tempoDeEsperaAcumulado = 0;
		tempoFila = 0;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ProcessoTipo getTipo() {
		return tipo;
	}

	public void setTipo(ProcessoTipo tipo) {
		this.tipo = tipo;
	}

	public int getCiclosTotal() {
		return ciclosTotal;
	}

	public void setCiclosTotal(int ciclosTotal) {
		this.ciclosTotal = ciclosTotal;
	}

	public int getCiclosExecutados() {
		return ciclosExecutados;
	}

	public void setCiclosExecutados(int ciclosExecutados) {
		this.ciclosExecutados = ciclosExecutados;
	}

	public int getTempoChegada() {
		return tempoChegada;
	}

	public void setTempoChegada(int tempoChegada) {
		this.tempoChegada = tempoChegada;
	}

	public int getTempoFila() {
		return tempoFila;
	}

	public void setTempoFila(int tempoFila) {
		this.tempoFila = tempoFila;
	}

	public int getTempoDeEsperaAcumulado() {
		return tempoDeEsperaAcumulado;
	}

	public void setTempoDeEsperaAcumulado(int tempoDeEsperaAcumulado) {
		this.tempoDeEsperaAcumulado = tempoDeEsperaAcumulado;
	}
	
	public boolean executa() {
		
		ciclosExecutados += quantum;
		
		if(ciclosExecutados >= ciclosTotal)
			return true;
		
		return false;
	}
	
	public boolean finalizou() {
		return ciclosExecutados + quantum >= ciclosTotal;
	}
	
}
