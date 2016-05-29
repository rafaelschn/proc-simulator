package com.schnrfl.procsimulator.model;

import java.util.LinkedList;

import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa Evento exógeno
 */
public class TipoEventoNovoProcesso implements TipoEvento {

	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado) {
		
		//System.out.println(evento);
		
		PCB pcb = evento.getPCB();

		//System.out.println("Tratando Evento Novo Processo (" + pcb.getNumero() + ")...");

		pcb.chegouNaFilaNoInstante(evento.getTempoDoEvento());
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(pcb);
		
		//Único processo?
		if ( !filaDeProntos.unicoProcesso() ) {
			System.out.println("Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila");

			return;
		}

		pcb.foiAtendidoNaFilaNoInstante(evento.getTempoDoEvento());
		filaDeProntos.iniciaProcessamento();
		
		// Gera evento fim CPU
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
		
		//filaDeProntos.removePrimeiroProcesso();

		// incrementa contador de ciclos
		//pcb.executa();
	}
	
	@Override
	public String toString() {
		return "[TipoEvento: Novo Processo]";
	}

}
