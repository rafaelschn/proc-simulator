package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.Logger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa Evento exógeno da entrada de um processo no sistema
 */
public class TipoEventoNovoProcesso implements TipoEvento {

	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado, Logger logger) {
		
		//Recupera o pcb do evento
		PCB pcb = evento.getPCB();

		System.out.println(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " entrou no sistema");
		logger.log(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " entrou no sistema");
		
		//Informa instante de chegada na fila
		pcb.chegouNaFilaNoInstante(evento.getTempoDoEvento());
		
		//Coloca processo na fila de prontos
		filaDeProntos.adiciona(pcb);
		
		//Único processo?
		if ( !filaDeProntos.unicoProcesso() ) {
			//Processador ocupado, pode ir para o próximo evento
			System.out.println("[Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila]");
			logger.log("[Processador ocupado para o PID " + pcb.getNumero() + ": " + filaDeProntos.size() + " processo(s) na fila]");

			return;
		}

		//Informa instante de atendimento na fila
		pcb.foiAtendidoNaFilaNoInstante(evento.getTempoDoEvento());
		filaDeProntos.iniciaProcessamento();
		
		// Gera evento fim CPU
		evento.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(evento);
		
	}
	
	@Override
	public String toString() {
		return "[TipoEvento: Novo Processo]";
	}

}
