package com.schnrfl.procsimulator.generation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import com.schnrfl.procsimulator.model.Evento;
import com.schnrfl.procsimulator.model.ProcessoEvento;

/*
 * Classe responsável pela leitura do arquivo .DAT 
 * para construção dos eventos iniciais (exógenos) da simulação
 * */
public class GeradorArquivoDat implements Gerador {

	private String fileName;
	private ParserArquivoDat parser;
	private LinkedList<Evento> eventos;
	private int pid;

	public GeradorArquivoDat(String fileName, ParserArquivoDat parser) throws FileNotFoundException, IOException {
		this.fileName = fileName;
		this.parser = parser;

		leArquivo(this.fileName);
	}

	private void leArquivo(String fileName) throws IOException, FileNotFoundException {
		pid = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String linha;
			eventos = new LinkedList<>();
			long instante = 0;
			while ((linha = br.readLine()) != null) {
				ProcessoEvento evento = parser.parse(linha, pid++, instante);
				
				//Achou processo com pid = -1 -> finaliza leitura
				if (evento == null)
					break;
				
				instante = evento.getTempoDoEvento();

				eventos.add(evento);
			}
		}
	}

	@Override
	public LinkedList<Evento> getElementos() {
		return eventos;
	}

}
