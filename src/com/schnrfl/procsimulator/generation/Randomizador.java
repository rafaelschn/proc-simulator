package com.schnrfl.procsimulator.generation;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizador {

	public static long randomiza(int min, int max) {
		int randomizado = ThreadLocalRandom.current().nextInt(min, max + 1);

		return randomizado;
	}

}
