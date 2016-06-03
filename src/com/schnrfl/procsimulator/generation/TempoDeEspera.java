package com.schnrfl.procsimulator.generation;

import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;


/**
 * Classe responsável por randomizar o tempo entre processos
 * */
public class TempoDeEspera {

	public static long getTempoDeEspera() {
		
		int min = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_DE;
		int max = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_ATE;
		
		long tempoDeEspera = Randomizador.randomiza(min, max);
		
		System.out.println("Randomizou tempo de espera: " + tempoDeEspera);
		
		return tempoDeEspera;
	}
	
}
