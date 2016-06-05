package com.schnrfl.procsimulator.simulation;

import java.util.LinkedHashMap;
import java.util.Map;

public class DadosDoGraficoDaFilaDeProntos {
	
	Map<Long, Long> dados;
	
	public DadosDoGraficoDaFilaDeProntos() {
		dados = new LinkedHashMap<>();
		dados.put(0L, 0L);
	}
	
	public Map<Long, Long> getDados() {
		return dados;
	}
	
	public void adiciona(Long instante, Long quantidadeEmFila) {
		dados.put(instante, quantidadeEmFila);
	}
	
}
