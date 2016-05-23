package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class GeradorEstatico implements Gerador {

	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		eventos.add(new ProcessoEvento(0, 7, 0, new TipoEventoNovoProcesso()));
		eventos.add(new ProcessoEvento(1, 4, 0, new TipoEventoNovoProcesso()));
		eventos.add(new ProcessoEvento(2, 3, 1, new TipoEventoNovoProcesso()));
		eventos.add(new ProcessoEvento(3, 9, 0, new TipoEventoNovoProcesso()));
		
		return eventos;
	}

}
