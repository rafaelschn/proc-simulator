package com.schnrfl.procsimulator.model;

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
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos) {
		System.out.println("Tratando Evento Fim ES");
		
		PCB pcb = evento.getPCB();
		System.out.println("PID " + pcb.getNumero() + " chegou na fila em: " + evento.getTempoDoEvento());
		pcb.chegouNaFilaNoInstante(evento.getTempoDoEvento());
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(evento.getPCB());
		
		//Único processo?
		if( !filaDeProntos.unicoProcesso() )
			return;
		
		pcb = filaDeProntos.atendeProcesso();
		
		pcb.foiAtendidoNaFilaNoInstante(evento.getTempoDoEvento());
		System.out.println("PID " + pcb.getNumero() + " foi atendido na fila em: " + evento.getTempoDoEvento());
		
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
	}
	
}
