package com.schnrfl.procsimulator.model;

import java.util.LinkedList;

public class FilaDeProntos {

	private LinkedList<PCB> processos;
	private int numeroMaximoDeProcessosNaFila;

	public FilaDeProntos(LinkedList<PCB> processos) {
		this.processos = processos;
		numeroMaximoDeProcessosNaFila = Integer.MIN_VALUE;
	}
	
	public int getNumeroMaximoDeProcessosNaFila() {
		return numeroMaximoDeProcessosNaFila;
	}
	
	public void adiciona(PCB processo) {
		processos.addLast(processo);
		
		int tamanho = processos.size();
		if(tamanho > numeroMaximoDeProcessosNaFila)
			numeroMaximoDeProcessosNaFila = tamanho;
	}
	
	public PCB removePrimeiroProcesso() {
		return processos.pop();
	}
	
	public PCB atendeProcesso() {
		return processos.pop();
		//return processos.getFirst();
	}
	
	public boolean unicoProcesso() {
		int quantidadeDeProcessosNaFila = processos.size();
		boolean unicoProcesso = quantidadeDeProcessosNaFila == 1;
		
		return unicoProcesso;
	}
	
	public PCB getPenultimo() {
		int quantidadeDeProcessosNaFila = processos.size();
		
		if(quantidadeDeProcessosNaFila < 2)
			throw new RuntimeException("Fila de prontos com menos de 2 elementos!");
		
		return processos.get(quantidadeDeProcessosNaFila - 2);
	}
	
	public int size() {
		return processos.size();
	}

	public LinkedList<PCB> getProcessos() {
		return processos;
	}

}
