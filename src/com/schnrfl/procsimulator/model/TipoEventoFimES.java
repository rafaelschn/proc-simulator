package com.schnrfl.procsimulator.model;

/**
 * Classe que representa Fim de atendimento da E/S. 
 * Este evento deverá colocar o processo na fila de prontos.
 * */
public class TipoEventoFimES implements TipoEvento {
	
	@Override
	public String toString() {
		return "[TipoEvento: Fim_ES]";
	}
	
}
