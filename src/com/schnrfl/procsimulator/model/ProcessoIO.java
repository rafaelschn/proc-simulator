package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.Logger;

public class ProcessoIO extends ProcessoTipoGenerico implements ProcessoTipo {

	public ProcessoIO(Logger logger) {
		this.logger = logger;
		tempoCicloCPU = getTempoRandomico(1, 10);
	}
	
	public ProcessoIO(boolean homolog) {
		tempoCicloCPU = 6;
		tempoCicloES = 2;
		logger = new FileLogger();
	}

	protected int randomizaES() {
		int tempoRandomizado = getTempoRandomico(200, 600);
		logger.log("[Randomizou ES: " + tempoRandomizado + "]");
		
		return tempoRandomizado;
	}

	@Override
	public String toString() {
		return "[Processo Tipo IO: tcpu " + tempoCicloCPU + ", tes " + tempoCicloES + "]";
	}

}
