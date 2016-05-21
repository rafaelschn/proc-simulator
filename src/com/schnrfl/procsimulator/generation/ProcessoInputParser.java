package com.schnrfl.procsimulator.generation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*
 * Classe respons�vel pela leitura do arquivo .dat
 * */
public class ProcessoInputParser implements Gerador {

	private String fileName;
	private Collection<ProcessoInput> processos;

	public ProcessoInputParser(String fileName) throws FileNotFoundException, IOException {
		this.fileName = fileName;

		leArquivo(this.fileName);
	}

	private void leArquivo(String fileName) throws IOException, FileNotFoundException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String linha;
			this.processos = new ArrayList<>();
			while ((linha = br.readLine()) != null) {
				ProcessoInput processo = parse(linha);

				if (processo == null)
					break;

				processos.add(processo);
			}
		}
	}

	private ProcessoInput parse(String linha) {
		String[] fields = linha.split(";");

		if (fields.length != 3)
			throw new RuntimeException("O formato de cada linha da entrada deve ser numero;cliclos;tipo!");

		int numero = Integer.parseInt(fields[0]);
		
		if (numero < -1)
			throw new RuntimeException("PID deve ser >= 0!");
			
		if (numero == -1)
			return null;

		int ciclos = Integer.parseInt(fields[1]);
		int tipo = Integer.parseInt(fields[2]);

		if (!(tipo == 0 || tipo == 1))
			throw new RuntimeException("Tipo deve ser 0 ou 1!");

		return new ProcessoInput(numero, ciclos, tipo);
	}

	@Override
	public Collection<ProcessoInput> getProcessos() {
		return Collections.unmodifiableCollection(processos);
	}

}
