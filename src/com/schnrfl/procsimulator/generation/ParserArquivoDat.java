package com.schnrfl.procsimulator.generation;

import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;

public class ParserArquivoDat {

	public ProcessoEvento parse(String linha, int ordem) {
		String[] fields = linha.split(";");

		if (fields.length != 3)
			throw new RuntimeException("O formato de cada linha da entrada deve ser numero;cliclos;tipo!");

		int numero = Integer.parseInt(fields[0]);
		
		if (numero < -1)
			throw new RuntimeException("PID deve ser >= 0!");
			
		if (numero == -1)
			return null;
		
		if(ordem != numero)
			throw new RuntimeException("Processos fora de ordem! Esperado " + ordem + " e recebido " + numero + "!");
		
		int ciclos = Integer.parseInt(fields[1]);
		int tipo = Integer.parseInt(fields[2]);

		if (!(tipo == 0 || tipo == 1))
			throw new RuntimeException("Tipo deve ser 0 ou 1!");

		return new ProcessoEvento(numero, ciclos, tipo, new TipoEventoNovoProcesso());
	}
	
}
