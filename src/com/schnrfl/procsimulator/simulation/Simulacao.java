package com.schnrfl.procsimulator.simulation;

public interface Simulacao {

	public void iniciar(Logger logger);

	public long getTempoExecucao();

	public Resultado getResultado();

}
