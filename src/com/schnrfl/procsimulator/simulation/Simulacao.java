package com.schnrfl.procsimulator.simulation;

public interface Simulacao {

	public void iniciar();

	public long getTempoExecucao();

	public Resultado getResultado();

}
