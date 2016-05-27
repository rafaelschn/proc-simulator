package com.schnrfl.procsimulator.simulation;

public class Simulador {
	
	Simulacao simulacao;
	
	public void iniciar(Simulacao simulacao) {
		this.simulacao = simulacao;
		this.simulacao.iniciar();
	}
	
	public long getTempoExecucao() {
		return simulacao.getTempoExecucao();
	}
	
	public Resultado getResultado() {
		return simulacao.getResultado();
	}

}
