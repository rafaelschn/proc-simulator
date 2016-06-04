package com.schnrfl.procsimulator.generation;

import com.schnrfl.procsimulator.model.ProcessoEvento;
import com.schnrfl.procsimulator.model.TipoEventoNovoProcesso;
import com.schnrfl.procsimulator.simulation.Logger;

public class ParserArquivoDat {

	public ProcessoEvento parse(String linha, int ordem, long instante, Logger logger) {
		String[] fields = linha.split(";");

		if (fields.length != 3)
			throw new RuntimeException("O formato de cada linha da entrada deve ser numero;cliclos;tipo!");

		int pid = Integer.parseInt(fields[0]);
		
		if (pid < -1)
			throw new RuntimeException("PID deve ser >= 0!");
			
		if (pid == -1)
			return null;
		
		if(ordem != pid)
			throw new RuntimeException("Processos fora de ordem! Esperado " + ordem + " e recebido " + pid + "!");
		
		int timeSlice = Integer.parseInt(fields[1]);
		int tipoDoProcesso = Integer.parseInt(fields[2]);

		if (!(tipoDoProcesso == 0 || tipoDoProcesso == 1))
			throw new RuntimeException("Tipo do processo deve ser 0 ou 1!");

		return new ProcessoEvento(instante + TempoDeEspera.getTempoDeEspera(logger), new TipoEventoNovoProcesso(), pid, timeSlice, ProcessoTipoFactory.build(tipoDoProcesso, logger), logger);
	}
	
}
