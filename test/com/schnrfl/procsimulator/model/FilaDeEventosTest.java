package com.schnrfl.procsimulator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.schnrfl.procsimulator.generation.ProcessoTipoFactory;
import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.Logger;

public class FilaDeEventosTest {

	@Test
	public void deveOrdenarCorretamente1() {
		
		Logger logger = new FileLogger();
		
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0, logger);
		ProcessoTipo io = ProcessoTipoFactory.build(1, logger);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(10, new TipoEventoNovoProcesso(), ++id, 7, cpu, logger);
		ProcessoEvento e2 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 4, cpu, logger);
		ProcessoEvento e3 = new ProcessoEvento(7, new TipoEventoNovoProcesso(), ++id, 3, io, logger);
		ProcessoEvento e4 = new ProcessoEvento(4, new TipoEventoNovoProcesso(), ++id, 9, cpu, logger);
		
		filaDeEventos.adiciona(e1);
		filaDeEventos.adiciona(e2);
		filaDeEventos.adiciona(e3);
		filaDeEventos.adiciona(e4);
		
		//filaDeEventos.getEventos().forEach(evento->System.out.println(evento));
		
		assertEquals(e2, filaDeEventos.proximo());
		assertEquals(e4, filaDeEventos.proximo());
		assertEquals(e3, filaDeEventos.proximo());
		assertEquals(e1, filaDeEventos.proximo());
	}
	
	@Test
	public void deveOrdenarCorretamente2() {
		
		Logger logger = new FileLogger();
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0, logger);
		ProcessoTipo io = ProcessoTipoFactory.build(1, logger);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 7, cpu, logger);
		ProcessoEvento e2 = new ProcessoEvento(4, new TipoEventoNovoProcesso(), ++id, 4, cpu, logger);
		ProcessoEvento e3 = new ProcessoEvento(7, new TipoEventoNovoProcesso(), ++id, 3, io, logger);
		ProcessoEvento e4 = new ProcessoEvento(10, new TipoEventoNovoProcesso(), ++id, 9, cpu, logger);
		
		filaDeEventos.adiciona(e1);
		filaDeEventos.adiciona(e2);
		filaDeEventos.adiciona(e3);
		filaDeEventos.adiciona(e4);
		
		assertEquals(e1, filaDeEventos.proximo());
		assertEquals(e2, filaDeEventos.proximo());
		assertEquals(e3, filaDeEventos.proximo());
		assertEquals(e4, filaDeEventos.proximo());
	}
	
	@Test
	public void deveOrdenarCorretamente3() {
		
		Logger logger = new FileLogger();
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0, logger);
		ProcessoTipo io = ProcessoTipoFactory.build(1, logger);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 7, cpu, logger);
		ProcessoEvento e2 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 4, cpu, logger);
		ProcessoEvento e3 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 3, io, logger);
		ProcessoEvento e4 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 9, cpu, logger);
		
		filaDeEventos.adiciona(e1);
		filaDeEventos.adiciona(e2);
		filaDeEventos.adiciona(e3);
		filaDeEventos.adiciona(e4);
		
		assertEquals(e1, filaDeEventos.proximo());
		assertEquals(e2, filaDeEventos.proximo());
		assertEquals(e3, filaDeEventos.proximo());
		assertEquals(e4, filaDeEventos.proximo());
	}
	
}
