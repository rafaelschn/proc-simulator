package com.schnrfl.procsimulator.simulation;

import java.io.IOException;

public class Simulador {
	
	private Simulacao simulacao;
	private FileLogger logger;
	
	public Simulador(FileLogger logger) {
		this.logger = logger;
	}
	
	public Simulador() {
		this(new FileLogger());
	}
	
	public Simulacao getSimulacao() {
		return simulacao;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void iniciar(Simulacao simulacao) {
		this.simulacao = simulacao;
		this.simulacao.iniciar(logger);
	}
	
	public long getTempoExecucao() {
		return simulacao.getTempoExecucao();
	}
	
	public Resultado getResultado() throws IOException {
		
		logger.log(simulacao.getResultado().toString());
		logger.save();
		
		return simulacao.getResultado();
	}

}
