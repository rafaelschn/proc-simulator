package com.schnrfl.procsimulator.model;

import java.util.LinkedList;

/**
 * Classe que representa evento da simulação
 */
public class ProcessoEvento implements Evento {

	/**
	 * Instante no tempo no qual o evento acontece
	 * */
	private long tempoDoEvento;
	
	/**
	 * Tipo do evento
	 * */
	private TipoEvento tipo;
	
	/**
	 * Bloco de controle do processo
	 */ 
	private PCB pcb;
	
	public ProcessoEvento(long tempoDoEvento, TipoEvento tipo, PCB pcb) {

		this.tipo = tipo;
		this.tempoDoEvento = tempoDoEvento;
		
		this.pcb = pcb;
	}

	public ProcessoEvento(long tempoDoEvento, TipoEvento tipo, int pid, int timeSlice, ProcessoTipo tipoProcesso) {

		this.tipo = tipo;
		this.tempoDoEvento = tempoDoEvento;
		
		//Criar PCB do Processo
		pcb = new PCB(pid, tipoProcesso, timeSlice, tempoDoEvento);
	}
	
	public long getTempoDoEvento() {
		return tempoDoEvento;
	}

	public TipoEvento getTipo() {
		return tipo;
	}
	
	public PCB getPCB() {
		return pcb;
	}
	
	public void acionaTratamento(FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos) {
		tipo.trata(this, filaDeEventos, filaDeProntos);
	}
	
	public void destroi() {
		
	}
	
	public void avancaNoTempo(long tempoParaAvancar, TipoEvento tipo) {
		tempoDoEvento += tempoParaAvancar;
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append("ProcessoEvento");
		sb.append(" tempo do evento: " + tempoDoEvento);
		sb.append(" " + tipo);
		sb.append(" " + pcb);
		sb.append("]");

		return sb.toString();
	}

}
