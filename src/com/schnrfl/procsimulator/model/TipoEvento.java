package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.Logger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

public interface TipoEvento {
	
	public void trata(ProcessoEvento evento, 
			FilaDeEventos filaDeEventos, 
			FilaDeProntos filaDeProntos,
			ResultadoProcessos resultado, Logger logger);
	
}
