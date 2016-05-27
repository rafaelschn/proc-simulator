package com.schnrfl.procsimulator.model;

import java.util.Comparator;
import java.util.LinkedList;

public class FilaDeEventos {

	private LinkedList<Evento> eventos;

	public FilaDeEventos() {
		this.eventos = new LinkedList<>();
	}
	
	public FilaDeEventos(LinkedList<Evento> eventos) {
		this.eventos = eventos;
		ordena();
	}
	
	private void ordena() {
		eventos.sort(Comparator.comparing(Evento::getTempoDoEvento));
	}

	public LinkedList<Evento> getEventos() {
		ordena();
		return eventos;
	}
	
	public void adiciona(ProcessoEvento evento) {
		eventos.addLast(evento);
		ordena();
	}
	
	public int size() {
		return eventos.size();
	}
	
	public Evento proximo() {
		return eventos.pop();
	}
	
	public boolean estaVazia() {
		return eventos.isEmpty();
	}
	
}
