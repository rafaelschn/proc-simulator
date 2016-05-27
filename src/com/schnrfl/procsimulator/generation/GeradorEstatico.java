package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class GeradorEstatico implements Gerador {

	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		long instante = 0;
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0);
		ProcessoTipo io = ProcessoTipoFactory.build(1);
		
		instante += TempoDeEspera.getTempoDeEspera();
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 0, 7, cpu));
		
		instante += TempoDeEspera.getTempoDeEspera();
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 1, 4, cpu));
		
		instante += TempoDeEspera.getTempoDeEspera();
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 2, 3, io));
		
		instante += TempoDeEspera.getTempoDeEspera();
		eventos.add(new ProcessoEvento(instante, new TipoEventoNovoProcesso(), 3, 9, cpu));
		
		return eventos;
	}

}
