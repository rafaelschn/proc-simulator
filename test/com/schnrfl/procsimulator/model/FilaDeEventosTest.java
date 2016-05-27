package com.schnrfl.procsimulator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.schnrfl.procsimulator.generation.ProcessoTipoFactory;

public class FilaDeEventosTest {

	@Test
	public void deveOrdenarCorretamente1() {
		
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0);
		ProcessoTipo io = ProcessoTipoFactory.build(1);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(10, new TipoEventoNovoProcesso(), ++id, 7, cpu);
		ProcessoEvento e2 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 4, cpu);
		ProcessoEvento e3 = new ProcessoEvento(7, new TipoEventoNovoProcesso(), ++id, 3, io);
		ProcessoEvento e4 = new ProcessoEvento(4, new TipoEventoNovoProcesso(), ++id, 9, cpu);
		
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
		
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0);
		ProcessoTipo io = ProcessoTipoFactory.build(1);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 7, cpu);
		ProcessoEvento e2 = new ProcessoEvento(4, new TipoEventoNovoProcesso(), ++id, 4, cpu);
		ProcessoEvento e3 = new ProcessoEvento(7, new TipoEventoNovoProcesso(), ++id, 3, io);
		ProcessoEvento e4 = new ProcessoEvento(10, new TipoEventoNovoProcesso(), ++id, 9, cpu);
		
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
		
		FilaDeEventos filaDeEventos = new FilaDeEventos();
		
		ProcessoTipo cpu = ProcessoTipoFactory.build(0);
		ProcessoTipo io = ProcessoTipoFactory.build(1);
		int id = -1;
		
		ProcessoEvento e1 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 7, cpu);
		ProcessoEvento e2 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 4, cpu);
		ProcessoEvento e3 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 3, io);
		ProcessoEvento e4 = new ProcessoEvento(3, new TipoEventoNovoProcesso(), ++id, 9, cpu);
		
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
