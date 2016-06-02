package com.schnrfl.procsimulator.model;

public class ProcessoIO extends ProcessoTipoGenerico implements ProcessoTipo {

	public ProcessoIO() {
		tempoCicloCPU = getTempoRandomico(1, 10);
	}

	public ProcessoIO(boolean homolog) {
		tempoCicloCPU = 6;
		tempoCicloES = 2;
	}

	protected int randomizaES() {
		return getTempoRandomico(200, 600);
	}

	@Override
	public String toString() {
		return "[Processo Tipo IO: tcpu " + tempoCicloCPU + ", tes " + tempoCicloES + "]";
	}

}
