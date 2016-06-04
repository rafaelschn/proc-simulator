package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.Logger;

public class ProcessoCPU extends ProcessoTipoGenerico implements ProcessoTipo {

	public ProcessoCPU(Logger logger) {
		this.logger = logger;
		tempoCicloCPU = getTempoRandomico(1, 100);
	}

	public ProcessoCPU(boolean homolog) {
		tempoCicloCPU = 6;
		tempoCicloES = 2;
		logger = new FileLogger();
	}

	protected int randomizaES() {
		return getTempoRandomico(50, 200);
	}

	@Override
	public String toString() {
		return "[Processo Tipo CPU: tcpu " + tempoCicloCPU + ", tes " + tempoCicloES + "]";
	}

}
