package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;
import com.schnrfl.procsimulator.simulation.Logger;

public class GeradorEstatico implements Gerador {

	private Logger logger;
	
	public GeradorEstatico(Logger logger) {
		this.logger = logger;
	}
	
	
	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		long instante = 0;
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0, logger);
		ProcessoTipo io = ProcessoTipoFactory.build(1, logger);
		
		instante += TempoDeEspera.getTempoDeEspera(logger);
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 0, 7, cpu, logger));
		
		instante += TempoDeEspera.getTempoDeEspera(logger);
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 1, 4, cpu, logger));
		
		instante += TempoDeEspera.getTempoDeEspera(logger);
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 2, 3, io, logger));
		
		instante += TempoDeEspera.getTempoDeEspera(logger);
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 3, 9, cpu, logger));
		
		return eventos;
	}

}
