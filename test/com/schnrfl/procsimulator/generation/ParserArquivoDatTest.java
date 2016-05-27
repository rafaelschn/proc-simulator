package com.schnrfl.procsimulator.generation;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ParserArquivoDatTest {
	
	@Test
	public void deveCarregarNumeroDeProcessosCorretamente() throws FileNotFoundException, IOException {
		String fileName = "files/entrada_test_4_processos.dat";
		Gerador gerador = new GeradorArquivoDat(fileName, new ParserArquivoDat());
		
		assertEquals(gerador.getElementos().size(), 4);
	}
	
}
