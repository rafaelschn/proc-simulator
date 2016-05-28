package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class GeradorEstaticoHomologacao implements Gerador {

	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		long instante = 0;
		
		ProcessoTipo cpu = ProcessoTipoFactoryHomolog.build(0);
		ProcessoTipo io = ProcessoTipoFactoryHomolog.build(1);
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 0, 7, cpu));
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 1, 4, cpu));
		/*
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 2, 3, io));
		
		instante += 4;
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 3, 9, cpu));
		*/
		return eventos;
	}

}
