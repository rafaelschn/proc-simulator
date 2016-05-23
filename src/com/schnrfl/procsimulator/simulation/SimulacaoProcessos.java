package com.schnrfl.procsimulator.simulation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.TipoEventoFimCPU;
import com.schnrfl.procsimulator.model.TipoEventoFimES;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class SimulacaoProcessos implements Simulacao {

	private Gerador gerador;
	private Resultado resultado;
	private LinkedList<Evento> eventos;
	private LinkedList<ProcessoEvento> prontos;
	
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
		
		//Colocar na Fila de Eventos
		eventos = gerador.getElementos();
		
		System.out.println("Iniciando simulação...");
		
		inicioExecucao = System.currentTimeMillis();
		
		executar();
		
		finalExecucao = System.currentTimeMillis();
		
		System.out.println("Finalizando simulação...");
	}

	private void executar() {
		//eventos.forEach(evento -> System.out.println(evento));
		
		prontos = new LinkedList<>();
		
		// Fila de Eventos = NULL
		int tempoEsperaTotal = 0;
		while( !eventos.isEmpty() ) {
			ProcessoEvento evento = (ProcessoEvento) eventos.pop();
			
			tempoEsperaTotal += evento.getTempoEspera();
			/*
			if(evento.getTipo() instanceof TipoEventoNovoProcesso) {
				
				//Coloca Processo na Fila de Prontos
				prontos.addLast(evento);
				
				boolean finalizou = evento.getPCB().finalizou();
				if(finalizou)
					continue;
				
				ProcessoEvento processoPronto = prontos.pop();
				evento.gera(new TipoEventoFimCPU());
				
				processoPronto.getPCB().executa();
				
				
			} else if (evento.getTipo() instanceof TipoEventoFimCPU) {
				
				if(evento.getPCB().finalizou()) {
					//contabiliza
					evento.destroi();
					continue;
				} else {
					//Gera Evento FIM_ES
					evento.gera(new TipoEventoFimES());
					if(prontos.pop() == null) {
						evento.gera(new TipoEventoFimCPU());
					}
					continue;
				}
				
			} else if (evento.getTipo() instanceof TipoEventoFimES) {
				prontos.addLast(evento);
			}
			*/
			
			//prontos.addLast(evento);
			
			System.out.println(evento);
		}
		
		System.out.println("Tempo espera total: " + tempoEsperaTotal);
		
		long tempoMedioDeEsperaNaFilaDeProntos = 0;
		int numeroDeProcessosConcluidosPorUnidadeDeTempo = 0;
		long numeroMedioDeExecucoes = 0;
		int numeroMaximoDeProcessosNaFilaDeProntos = 0;
		resultado = new ResultadoProcessos(tempoMedioDeEsperaNaFilaDeProntos, 
				numeroDeProcessosConcluidosPorUnidadeDeTempo, 
				numeroMedioDeExecucoes, 
				numeroMaximoDeProcessosNaFilaDeProntos);
	}
	
	public Resultado getResultado() {
		return resultado;
	}
}
