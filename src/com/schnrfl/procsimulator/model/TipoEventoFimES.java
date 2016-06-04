package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.Logger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa Fim de atendimento da E/S. 
 * Este evento deverá colocar o processo na fila de prontos.
 * */
public class TipoEventoFimES implements TipoEvento {
	
	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado, Logger logger) {
		
		PCB pcb = evento.getPCB();
		
		System.out.println(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " finalizou ES");
		logger.log(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " finalizou ES");
		
		//long proximoInstante = evento.getTempoDoEvento() + 1;
		long proximoInstante = evento.getTempoDoEvento();
		pcb.chegouNaFilaNoInstante(proximoInstante);
		
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(pcb);
		
		//Único processo?
		if( !filaDeProntos.unicoProcesso() ) {
			//Processador ocupado, pode ir para o próximo evento
			System.out.println("[Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila]");
			logger.log("[Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila]");
			return;
		}
		
		//Atende o próximo processo na fila
		pcb = filaDeProntos.atendeProcesso();
		
		//Informa instante de atendimento na fila
		pcb.foiAtendidoNaFilaNoInstante(proximoInstante);
		filaDeProntos.iniciaProcessamento();
		
		// Gera evento fim CPU
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
	}
	
	@Override
	public String toString() {
		return "[TipoEvento: Fim ES]";
	}
	
}
