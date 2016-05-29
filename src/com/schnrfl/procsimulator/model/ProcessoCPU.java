package com.schnrfl.procsimulator.model;

public class ProcessoCPU extends ProcessoTipoGenerico implements ProcessoTipo {

	public ProcessoCPU() {
		tempoCicloCPU = getTempoRandomico(1, 100);
		tempoCicloES = getTempoRandomico(50, 200);
	}
	
	public ProcessoCPU(boolean homolog) {
		tempoCicloCPU = 4;
		tempoCicloES = 2;
	}

	@Override
	public String toString() {
		return "[Processo Tipo CPU: tcpu " + tempoCicloCPU + ", tes " + tempoCicloES + "]";
	}

}
