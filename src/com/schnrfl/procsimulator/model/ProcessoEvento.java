package com.schnrfl.procsimulator.model;

import java.util.concurrent.ThreadLocalRandom;

import com.schnrfl.procsimulator.generation.ProcessoTipoFactory;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;

/**
 * Classe que representa as entradas da simulação, oriundas do arquivo .DAT
 */
public class ProcessoEvento implements Evento {

	/**
	 * 
	 * */
	private int tempoEspera;
	
	/**
	 * Tempo do evento
	 * */
	private long tempoDoEvento;
	
	/**
	 * Tipo do evento
	 * */
	private TipoEvento tipo;
	
	/**
	 * Process Control Block
	 */ 
	private PCB pcb;

	public ProcessoEvento(int numero, int ciclos, int processoTipo, TipoEvento tipo) {

		this.tipo = tipo;
		
		int min = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_DE;
		int max = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_ATE;
		
		// Randomizar tempo de entrada deste processo
		tempoEspera = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		//Criar PCB do Processo
		pcb = new PCB(numero, ProcessoTipoFactory.build(processoTipo), ciclos);
	}

	public int getTempoEspera() {
		return tempoEspera;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}
	
	public PCB getPCB() {
		return pcb;
	}
	
	public void gera(TipoEvento tipo) {
		this.tipo = tipo;
	}
	
	public void destroi() {
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append("ProcessoEvento");
		sb.append(" tipo: " + tipo);
		sb.append(" processo numero: " + pcb.getNumero());
		sb.append(" processo ciclos: " + pcb.getCiclosTotal());
		sb.append(" processo tipo: " + pcb.getTipo());
		sb.append(" TE: " + getTempoEspera());
		sb.append("]");

		return sb.toString();
	}

}
