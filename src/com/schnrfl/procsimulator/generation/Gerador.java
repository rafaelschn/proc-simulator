package com.schnrfl.procsimulator.generation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;

public interface Gerador {
	
	public LinkedList<Evento> getElementos();
	
}
