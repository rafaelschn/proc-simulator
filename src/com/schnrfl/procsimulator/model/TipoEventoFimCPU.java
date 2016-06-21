package com.schnrfl.procsimulator.model;

import com.schnrfl.procsimulator.simulation.Logger;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;

/**
 * Classe que representa o Fim do atendimento da CPU. 
 * Este evento deverá escalonar um evento FIM_ES 
 * ou destruir o processo, se for o último ciclo de CPU.
 * */
public class TipoEventoFimCPU implements TipoEvento {

	private void atendeProximoProcessoDaFila(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos) {
		
		PCB pcb;
		//Retira primeiro processo da fila de prontos
		pcb = filaDeProntos.atendeProcesso();
		
		//long instante = evento.getTempoDoEvento() + 1;
		long instante = evento.getTempoDoEvento();
		
		//Informa instante de atendimento na fila
		pcb.foiAtendidoNaFilaNoInstante(instante);
		filaDeProntos.iniciaProcessamento();
		
		// Gera evento fim CPU
		ProcessoEvento fimCpu = new ProcessoEvento(instante, evento.getTipo(), pcb);
		fimCpu.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(fimCpu);
	}
	
	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado, Logger logger) {
		
		PCB pcb = evento.getPCB();
		
		//Contabiliza ciclos executados pelo processo
		pcb.executa();
		
		//Informa que o processador está liberado para executar outro processo
		filaDeProntos.finalizouProcessamento();
		
		logger.log(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " saiu do processador");
		
		//O processo executou todos ciclos?
		if(pcb.finalizou()) {
			
			//Contabiliza
			pcb.contabilizar(resultado, evento);
			
			//Existem processos esperando na fila?
			if(filaDeProntos.size() > 0) {
				atendeProximoProcessoDaFila(evento, filaDeEventos, filaDeProntos);
			}
			
			return;
		}
		
		//Gera evento Fim ES
		ProcessoEvento fimES = new ProcessoEvento(evento.getTempoDoEvento(), evento.getTipo(), pcb);
		fimES.avancaNoTempo(pcb.getTempoCicloES(), new TipoEventoFimES());
		filaDeEventos.adiciona(fimES);
		
		//Fila Vazia?
		if(filaDeProntos.size() == 0) {
			logger.log("[Fila de prontos vazia]");
			//Pode ir para o próximo evento
			return;
		}
		
		atendeProximoProcessoDaFila(evento, filaDeEventos, filaDeProntos);
		
	}
	
	@Override
	public String toString() {
		return "[TipoEvento: Fim CPU]";
	}
	
}
