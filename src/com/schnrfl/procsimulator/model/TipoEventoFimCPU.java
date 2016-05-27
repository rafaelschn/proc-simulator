package com.schnrfl.procsimulator.model;

/**
 * Classe que representa o Fim do atendimento da CPU. 
 * Este evento deverá escalonar um evento FIM_ES 
 * ou destruir o processo, se for o último ciclo de CPU.
 * */
public class TipoEventoFimCPU implements TipoEvento {

	@Override
	public String toString() {
		return "[TipoEvento: Fim CPU]";
	}
	
	public void trata(ProcessoEvento evento, FilaDeEventos filaDeEventos, FilaDeProntos filaDeProntos) {
		System.out.println("Tratando Evento Fim CPU");
		
		PCB pcb = evento.getPCB();
		
		pcb.executa();
		
		//filaDeProntos.removePrimeiroProcesso();
		
		if(pcb.finalizou()) {
			//Contabiliza
			pcb.contabilizar();
			//Destrói processo
			//evento = null;
			
			return;
		}
		
		//Gera evento Fim ES
		
		ProcessoEvento fimES = new ProcessoEvento(evento.getTempoDoEvento(), evento.getTipo(), pcb);
		fimES.avancaNoTempo(pcb.getTempoCicloES(), new TipoEventoFimES());
		filaDeEventos.adiciona(fimES);
		
		//Fila Vazia?
		if(filaDeProntos.size() == 0)
			return;
		
		//Retira primeiro processo da fila de prontos
		pcb = filaDeProntos.atendeProcesso();
		
		pcb.foiAtendidoNaFilaNoInstante(evento.getTempoDoEvento());
		System.out.println("PID " + pcb.getNumero() + " foi atendido na fila em: " + evento.getTempoDoEvento());
		
		// Gera evento fim CPU
		ProcessoEvento fimCpu = new ProcessoEvento(evento.getTempoDoEvento(), evento.getTipo(), pcb);
		fimCpu.avancaNoTempo(pcb.getCiclosParaExecutar(), new TipoEventoFimCPU());
		filaDeEventos.adiciona(fimCpu);
		
	}
	
}
