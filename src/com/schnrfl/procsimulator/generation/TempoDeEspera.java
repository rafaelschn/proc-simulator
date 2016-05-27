package com.schnrfl.procsimulator.generation;

import java.util.concurrent.ThreadLocalRandom;

import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;


/**
 * Classe responsável por randomizar o tempo entre processos
 * */
public class TempoDeEspera {

	public static long getTempoDeEspera() {
		
		int min = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_DE;
		int max = SimulacaoProcessos.PROCESSO_TEMPO_ESPERA_ATE;
		
		int tempoDeEspera = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		System.out.println("Randomizou: " + tempoDeEspera);
		
		return tempoDeEspera;
	}
	
}
