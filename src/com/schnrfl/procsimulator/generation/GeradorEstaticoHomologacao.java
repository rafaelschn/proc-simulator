package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;
import com.schnrfl.procsimulator.simulation.Logger;

public class GeradorEstaticoHomologacao implements Gerador {

private Logger logger;
	
	public GeradorEstaticoHomologacao(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		long instante = 0;
		
		ProcessoTipo cpu = ProcessoTipoFactoryHomolog.build(0);
		ProcessoTipo io = ProcessoTipoFactoryHomolog.build(1);
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 0, 2, cpu, logger));
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 1, 2, cpu, logger));
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 2, 3, io, logger));
		
		/*
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 3, 9, cpu));
		*/
		return eventos;
	}

}
