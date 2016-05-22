package com.schnrfl.procsimulator.simulation;

import java.util.Collection;
import java.util.LinkedList;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.model.Evento;

public class SimulacaoProcessos implements Simulacao {

	private Gerador gerador;
	private LinkedList<Evento> eventos;
	private LinkedList<Evento> prontos;
	
	public static final int PROCESSO_TEMPO_ESPERA_DE  = 0;
	public static final int PROCESSO_TEMPO_ESPERA_ATE = 50;
	
	private long inicioExecucao;
	private long finalExecucao;
	
	public SimulacaoProcessos(Gerador gerador) {
		this.gerador = gerador;
	}
	
	public SimulacaoProcessos() {
		
	}
	
	public Simulacao setGerador(Gerador gerador) {
		this.gerador = gerador;
		
		return this;
	}
	
	public long getTempoExecucao() {
		return finalExecucao - inicioExecucao;
	}
	
	public void iniciar(Gerador gerador) {
		this.gerador = gerador;
		iniciar();
	}
	
	public void iniciar() {
		
		if(gerador == null)
			throw new RuntimeException("Informe um gerador de eventos!");
		
		eventos = gerador.getElementos();
		
		inicioExecucao = System.currentTimeMillis();
		
		executar();
		
		finalExecucao = System.currentTimeMillis();
	}

	private void executar() {
		eventos.forEach(evento -> System.out.println(evento));
		
		System.out.println("Iniciando simulação...");
		
		prontos = new LinkedList<>();
		while( !eventos.isEmpty() ) {
			Evento evento = eventos.pop();
			prontos.addLast(evento);
			System.out.println(evento);
		}
		
		System.out.println("Finalizando simulação...");
		
	}
}
