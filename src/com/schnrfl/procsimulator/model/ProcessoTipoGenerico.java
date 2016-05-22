package com.schnrfl.procsimulator.model;

import java.util.concurrent.ThreadLocalRandom;

public abstract class ProcessoTipoGenerico {

	protected int tempoCicloCPU;
	protected int tempoCicloES;
	
	public int getTempoCicloCPU() {
		return tempoCicloCPU;
	}
	
	public int getTempoCicloES() {
		return tempoCicloES;
	}
	
	protected int getTempoRandomico(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}
