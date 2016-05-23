package com.schnrfl.procsimulator.model;

/**
 * Classe que representa o Fim do atendimento da CPU. 
 * Este evento deverá escalonar um evento FIM_ES 
 * ou destruir o processo, se for o último ciclo de CPU.
 * */
public class TipoEventoFimCPU implements TipoEvento {

	@Override
	public String toString() {
		return "[TipoEvento: Fim_CPU]";
	}
	
}
