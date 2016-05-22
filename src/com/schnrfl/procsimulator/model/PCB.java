package com.schnrfl.procsimulator.model;

/**
 * Classe que representa um Process Control Block
 * */

public class PCB implements Evento {
	
	private int numero; // Número do processo
	private ProcessoTipo tipo; // Tipo do Processo: CPU ou I/O Bound
	private int ciclosTotal; // Número de ciclos do processo
	private int ciclosExecutados; // Ciclos já executados pelo processo
	private int tempoChegada; // Tempo de chegada do processo
	private int tempoFila; // Tempo inicial de um período na fila
	private int tempoEspera; // Tempo de espera acumulado
	
	
	
}
