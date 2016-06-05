package com.schnrfl.procsimulator.simulation;

import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

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
	
	public FilaDeEventos getFilaDeEventos() {
		return filaDeEventos;
	}
	
	public FilaDeProntos getFilaDeProntos() {
		return filaDeProntos;
	}
	
	public long getTempoExecucao() {
		return finalExecucao - inicioExecucao;
	}
	
	public void iniciar(Gerador gerador, Logger logger) {
		this.gerador = gerador;
		iniciar(logger);
	}
	
	public void iniciar(Logger logger) {
		
		if(gerador == null)
			throw new RuntimeException("Informe um gerador de eventos!");
		
		//Colocar na Fila de Eventos
		filaDeEventos = new FilaDeEventos(gerador.getElementos());
		filaDeProntos = new FilaDeProntos(new LinkedList<>());
		
		System.out.println("Iniciando simulação...\n");
		logger.log("Iniciando simulação...\n");
		
		inicioExecucao = System.currentTimeMillis();
		
		executar(logger);
		
		finalExecucao = System.currentTimeMillis();
		
		System.out.println("\nFinalizando simulação...");
		logger.log("\nFinalizando simulação...");
	}

	private void executar(Logger logger) {
		
		Relogio relogio = new Relogio();
		resultado = new ResultadoProcessos();
		
		//filaDeEventos.getEventos().forEach(evento->System.out.println(evento));
		//System.out.println();
		
		filaDeEventos.getEventos().forEach(evento->logger.log(evento.toString()));
		
		// Fila de Eventos = NULL
		while( !filaDeEventos.estaVazia() ) {
			
			//Busca próximo evento
			ProcessoEvento evento = (ProcessoEvento) filaDeEventos.proximo();
			
			//Controle do relógio
			relogio.avancaAte(evento.getTempoDoEvento());
			
			//Insere dados do gráfico
			resultado.informaEstadoDaFilaDeProntos(evento.getTempoDoEvento(), (long)filaDeProntos.size());
			
			//Trata o evento
			evento.acionaTratamento(filaDeEventos, filaDeProntos, resultado, logger);
			
		}
		
		long duracao = relogio.getInstanteNoTempo();
		
		//Define os resultados finais da simulação
		resultado.setNumeroMaximoDeProcessosNaFilaDeProntos(filaDeProntos.getNumeroMaximoDeProcessosNaFila());
		resultado.setDuracao(duracao);
		
	}
	
	public Resultado getResultado() {
		resultado.setDuracaoReal(getTempoExecucao());
		
		return resultado;
	}
	
}
