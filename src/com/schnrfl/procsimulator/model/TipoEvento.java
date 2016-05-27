package com.schnrfl.procsimulator.model;

public interface TipoEvento {
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos);
}
