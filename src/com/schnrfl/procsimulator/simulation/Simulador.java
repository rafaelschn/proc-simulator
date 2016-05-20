package com.schnrfl.procsimulator.simulation;

import java.util.Collection;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.ProcessoInput;

public class Simulador {
	
	private Gerador gerador;
	private Collection<ProcessoInput> processos;
	
	public static final int PROCESSO_TEMPO_ESPERA_DE  = 0;
	public static final int PROCESSO_TEMPO_ESPERA_ATE = 50;
	
	public Simulador(Gerador gerador) {
		this.gerador = gerador;
		processos = this.gerador.getProcessos();
		
		processos.forEach(processo -> System.out.println(processo));
	}

}
