package com.schnrfl.procsimulator.program;

import java.io.IOException;

import com.schnrfl.procsimulator.generation.ProcessoInputParser;
import com.schnrfl.procsimulator.simulation.Simulador;

public class Main {

	public static void main(String[] args) {
		try {
			new Simulador(new ProcessoInputParser("files/entrada.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
