package com.schnrfl.procsimulator.generation;

import com.schnrfl.procsimulator.model.ProcessoCPU;
import com.schnrfl.procsimulator.model.ProcessoIO;
import com.schnrfl.procsimulator.model.ProcessoTipo;

public class ProcessoTipoFactoryHomolog {

	public static final int TIPO_CPU = 0;
	public static final int TIPO_IO = 1;

	public static ProcessoTipo build(int id) {

		switch (id) {
		case ProcessoTipoFactoryHomolog.TIPO_CPU:
			return new ProcessoCPU(true);
		case ProcessoTipoFactoryHomolog.TIPO_IO:
			return new ProcessoIO(true);
		}

		throw new RuntimeException("Tipo de processo com id " + id + " desconhecido!");
	}

}
