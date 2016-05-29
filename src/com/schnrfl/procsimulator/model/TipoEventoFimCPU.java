package com.schnrfl.procsimulator.model;

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
		
		long instante = evento.getTempoDoEvento() + 1;
		
		pcb.foiAtendidoNaFilaNoInstante(instante);
		filaDeProntos.iniciaProcessamento();
		
		// Gera evento fim CPU
		ProcessoEvento fimCpu = new ProcessoEvento(instante, evento.getTipo(), pcb);
		fimCpu.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(fimCpu);
	}
	
	@Override
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos, ResultadoProcessos resultado) {
		
		//System.out.println(evento);
		
		PCB pcb = evento.getPCB();
		
		//System.out.println("Tratando Evento Fim CPU (" + pcb.getNumero() + ")...");
		
		pcb.executa();
		
		filaDeProntos.finalizouProcessamento();
		
		System.out.println(evento.getTempoDoEvento() + " - PID " + pcb.getNumero() + " saiu do processador");
		
		//filaDeProntos.removePrimeiroProcesso();
		
		if(pcb.finalizou()) {
			
			//Contabiliza
			pcb.contabilizar(resultado);
			
			//Destrói processo
			//evento = null;
			
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
			System.out.println("Fila de prontos vazia");
			return;
		}
		
		atendeProximoProcessoDaFila(evento, filaDeEventos, filaDeProntos);
		
	}
	
	@Override
	public String toString() {
		return "[TipoEvento: Fim CPU]";
	}
	
}
