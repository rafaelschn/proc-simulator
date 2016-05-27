package com.schnrfl.procsimulator.simulation;

public class Relogio {

	private long instanteNoTempo;

	public Relogio() {
		this.instanteNoTempo = 0;
	}
	
	public Relogio(long instanteNoTempo) {
		this.instanteNoTempo = instanteNoTempo;
	}

	public long getInstanteNoTempo() {
		return instanteNoTempo;
	}
	
	public void avanca(long unidadeDeTempo) {
		instanteNoTempo += unidadeDeTempo;
	}
	
	public void retrocede(long unidadeDeTempo) {
		instanteNoTempo -= unidadeDeTempo;
	}
	
	public void avancaAte(long instanteNoTempo) {
		this.instanteNoTempo = instanteNoTempo;
	}

	@Override
	public String toString() {
		return "[Relogio: " + instanteNoTempo + "]";
	}

}
