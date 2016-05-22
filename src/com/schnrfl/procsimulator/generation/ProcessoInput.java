package com.schnrfl.procsimulator.generation;

import java.util.concurrent.ThreadLocalRandom;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;

/**
 * Classe que representa as entradas da simulação, oriundas do arquivo .DAT
 */
public class ProcessoInput implements Evento {

	private int numero;
	private int ciclos;
	private ProcessoTipo tipo;
	private int tempoEspera;

	public ProcessoInput(int numero, int cliclos, int tipo) {
		this.numero = numero;
		this.ciclos = cliclos;
		this.tipo = ProcessoTipoFactory.build(tipo);

		int min = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_DE;
		int max = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_ATE;

		tempoEspera = ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public int getNumero() {
		return numero;
	}

	public int getCliclos() {
		return ciclos;
	}

	public ProcessoTipo getTipo() {
		return tipo;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append("ProcessoInput");
		sb.append(" numero: " + numero);
		sb.append(" ciclos: " + ciclos);
		sb.append(" tipo: " + tipo);
		sb.append(" TE: " + getTempoEspera());
		sb.append("]");

		return sb.toString();
	}

}
