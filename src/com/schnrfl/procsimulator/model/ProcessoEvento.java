package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.Logger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

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

	public ProcessoEvento(long tempoDoEvento, TipoEvento tipo, int pid, int timeSlice, ProcessoTipo tipoProcesso, Logger logger) {

		this.tipo = tipo;
		this.tempoDoEvento = tempoDoEvento;
		
		//Criar PCB do Processo
		pcb = new PCB(pid, tipoProcesso, timeSlice, tempoDoEvento, logger);
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
	
	public void acionaTratamento(FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado, Logger logger) {
		tipo.trata(this, filaDeEventos, filaDeProntos, resultado, logger);
	}
	
	public void avancaNoTempo(long tempoParaAvancar, TipoEvento tipo) {
		tempoDoEvento += tempoParaAvancar;
		this.tipo = tipo;
	}
	
	public void destroi() {
		
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
