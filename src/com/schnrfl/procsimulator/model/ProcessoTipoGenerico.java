package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.generation.Randomizador;
import com.schnrfl.procsimulator.simulation.Logger;

public abstract class ProcessoTipoGenerico {

	protected int tempoCicloCPU;
	protected int tempoCicloES;
	protected Logger logger;
	
	protected int randomizaES() {
		return 0;
	}
	
	protected int getTempoRandomico(int min, int max) {
		return (int) Randomizador.randomiza(min, max);
	}
	
	public int getTempoCicloCPU() {
		return tempoCicloCPU;
	}
	
	public int getTempoCicloES() {
		tempoCicloES = randomizaES();
		
		System.out.println("[Tempo de ES randomizado: " + tempoCicloES + "]");
		logger.log("[Tempo de ES randomizado: " + tempoCicloES + "]");
		
		return tempoCicloES;
	}
	
}
