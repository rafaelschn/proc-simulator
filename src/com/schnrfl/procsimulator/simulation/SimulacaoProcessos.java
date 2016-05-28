package com.schnrfl.procsimulator.simulation;

import java.util.LinkedList;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.FilaDeEventos;
import com.schnrfl.procsimulator.model.FilaDeProntos;
import com.schnrfl.procsimulator.model.PCB;
import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.TipoEventoFimCPU;
import com.schnrfl.procsimulator.model.TipoEventoFimES;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class SimulacaoProcessos implements Simulacao {

	private Gerador gerador;
	private ResultadoProcessos resultado;
	private FilaDeEventos filaDeEventos;
	private FilaDeProntos filaDeProntos;
	
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
		filaDeEventos = new FilaDeEventos(gerador.getElementos());
		filaDeProntos = new FilaDeProntos(new LinkedList<>());
		
		System.out.println("Iniciando simulação...\n");
		
		inicioExecucao = System.currentTimeMillis();
		
		executar();
		
		finalExecucao = System.currentTimeMillis();
		
		System.out.println("\nFinalizando simulação...");
	}

	private void executar() {
		//eventos.forEach(evento -> System.out.println(evento));
		
		Relogio relogio = new Relogio();
		
		long tempoMedioDeEsperaNaFilaDeProntos = 0;
		int numeroDeProcessosConcluidosPorUnidadeDeTempo = 0;
		long numeroMedioDeExecucoes = 0;
		int numeroMaximoDeProcessosNaFilaDeProntos = 0;
		
		resultado = new ResultadoProcessos();
		
		// Fila de Eventos = NULL
		while( !filaDeEventos.estaVazia() ) {
			
			ProcessoEvento evento = (ProcessoEvento) filaDeEventos.proximo();
			
			relogio.avancaAte(evento.getTempoDoEvento());
			
			evento.acionaTratamento(filaDeEventos, filaDeProntos, resultado);
			
		}
		
		long duracao = relogio.getInstanteNoTempo();
		
		resultado.setNumeroMaximoDeProcessosNaFilaDeProntos(filaDeProntos.getNumeroMaximoDeProcessosNaFila());
		resultado.setDuracao(duracao);
		
		
		System.out.println("Relógio da simulação avançou " + duracao + " unidades de tempo");
		//System.out.println("Número de processos na fila de prontos: " + filaDeProntos.size());
	}
	
	public Resultado getResultado() {
		return resultado;
	}
	
}
