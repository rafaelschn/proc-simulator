package com.schnrfl.procsimulator.generation;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ProcessoInputParserTest {
	@Test
	public void deveCarregarNumeroDeProcessosCorretamente() throws FileNotFoundException, IOException {
		ProcessoInputParser generator = new ProcessoInputParser("files/entrada_test_4_processos.dat");
		assertEquals(generator.getProcessos().size(), 4);
	}
}
