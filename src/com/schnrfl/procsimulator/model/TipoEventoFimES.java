package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa Fim de atendimento da E/S. 
 * Este evento deverá colocar o processo na fila de prontos.
 * */
public class TipoEventoFimES implements TipoEvento {
	
	@Override
	public String toString() {
		return "[TipoEvento: Fim ES]";
	}

	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado) {
		
		//System.out.println(evento);
		
		PCB pcb = evento.getPCB();
		
		//System.out.println("Tratando Evento Fim ES (" + pcb.getNumero() + ")...");
		System.out.println("PID " + pcb.getNumero() + " finalizou ES no instante " + evento.getTempoDoEvento());
		
		long proximoInstante = evento.getTempoDoEvento() + 1;
		pcb.chegouNaFilaNoInstante(proximoInstante);
		
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(pcb);
		
		//Único processo?
		if( !filaDeProntos.unicoProcesso() ) {
			System.out.println("Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila");
			return;
		}
		
		pcb = filaDeProntos.atendeProcesso();
		
		pcb.foiAtendidoNaFilaNoInstante(proximoInstante);
		filaDeProntos.iniciaProcessamento();
		
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
	}
	
}
