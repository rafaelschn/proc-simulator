package com.schnrfl.procsimulator.model;

import java.util.LinkedList;

/**
 * Classe que representa Evento exógeno
 */
public class TipoEventoNovoProcesso implements TipoEvento {

	@Override
	public String toString() {
		return "[TipoEvento: Novo Processo]";
	}

	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos) {
		PCB pcb = evento.getPCB();

		System.out.println("Tratando Evento Novo Processo (" + pcb.getNumero() + ")...");

		/*
		if(pcb.getNumero() == 1) {
			System.out.println("size:" + filaDeProntos.size());
			System.out.println("1 foi atendido em: " + evento.getTempoDoEvento());
		}
		*/
		
		System.out.println("PID " + pcb.getNumero() + " chegou na fila em: " + evento.getTempoDoEvento());
		
		pcb.chegouNaFilaNoInstante(evento.getTempoDoEvento());
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(pcb);
		
		//Único processo?
		if ( !filaDeProntos.unicoProcesso() ) {
			//PCB pcbAnterior = filaDeProntos.getPenultimo();
			System.out.println("Processador ocupado para o PID " + pcb.getNumero() + "!");

			return;
		}

		pcb.foiAtendidoNaFilaNoInstante(evento.getTempoDoEvento());
		System.out.println("PID " + pcb.getNumero() + " foi atendido na fila em: " + evento.getTempoDoEvento());
		
		/*
		if(pcb.getNumero() == 1) {
			System.out.println("size:" + filaDeProntos.size());
			System.out.println("1 foi atendido em: " + evento.getTempoDoEvento());
		}
		*/

		// Gera evento fim CPU
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
		
		filaDeProntos.removePrimeiroProcesso();

		// incrementa contador de ciclos
		//pcb.executa();
	}

}
