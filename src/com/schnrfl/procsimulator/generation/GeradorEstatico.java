package com.schnrfl.procsimulator.generation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;

public class GeradorEstatico implements Gerador {

	@Override
	public LinkedList<Evento> getElementos() {
		LinkedList<Evento> eventos = new LinkedList<>();
		
		eventos.add(new ProcessoInput(0, 7, 0));
		eventos.add(new ProcessoInput(1, 4, 0));
		eventos.add(new ProcessoInput(2, 3, 1));
		eventos.add(new ProcessoInput(3, 9, 0));
		
		return eventos;
	}

}
