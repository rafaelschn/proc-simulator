package com.schnrfl.procsimulator.generation;

import com.schnrfl.procsimulator.model.ProcessoCPU;
import com.schnrfl.procsimulator.model.ProcessoIO;
import com.schnrfl.procsimulator.model.ProcessoTipo;
import com.schnrfl.procsimulator.simulation.Logger;

public class ProcessoTipoFactory {

	public static final int TIPO_CPU = 0;
	public static final int TIPO_IO = 1;

	public static ProcessoTipo build(int id, Logger logger) {

		switch (id) {
		case ProcessoTipoFactory.TIPO_CPU:
			return new ProcessoCPU(logger);
		case ProcessoTipoFactory.TIPO_IO:
			return new ProcessoIO(logger);
		}

		throw new RuntimeException("Tipo de processo com id " + id + " desconhecido!");
	}

}
