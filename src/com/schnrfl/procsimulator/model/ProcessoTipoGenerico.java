package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.generation.Randomizador;

public abstract class ProcessoTipoGenerico {

	protected int tempoCicloCPU;
	protected int tempoCicloES;
	
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
		
		return tempoCicloES;
	}
	
}
