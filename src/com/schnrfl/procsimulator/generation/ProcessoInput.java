package com.schnrfl.procsimulator.generation;

import java.util.concurrent.ThreadLocalRandom;

import com.schnrfl.procsimulator.simulation.Simulador;

/**
 * Classe que representa as entradas da simulação, oriundas do arquivo .DAT
 */
public class ProcessoInput {

	private int numero;
	private int ciclos;
	private int tipo;

	public ProcessoInput(int numero, int cliclos, int tipo) {
		this.numero = numero;
		this.ciclos = cliclos;
		this.tipo = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public int getCliclos() {
		return ciclos;
	}

	public int getTipo() {
		return tipo;
	}
	
	public int getTempoEspera() {
		int min = Simulador.PROCESSO_TEMPO_ESPERA_DE;
		int max = Simulador.PROCESSO_TEMPO_ESPERA_ATE;
		
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("ProcessoInput");
		sb.append(" numero: " + numero);
		sb.append(" ciclos: " + ciclos);
		sb.append(" tipo: " + tipo);
		sb.append(" TE: "+getTempoEspera());
		sb.append("]");
		
		return sb.toString();
	}

}
