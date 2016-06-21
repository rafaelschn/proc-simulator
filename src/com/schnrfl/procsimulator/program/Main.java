package com.schnrfl.procsimulator.program;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.schnrfl.procsimulator.generation.Gerador;
import com.schnrfl.procsimulator.generation.GeradorArquivoDat;
import com.schnrfl.procsimulator.generation.GeradorEstaticoHomologacao;
import com.schnrfl.procsimulator.generation.ParserArquivoDat;
import com.schnrfl.procsimulator.simulation.FileLogger;
import com.schnrfl.procsimulator.simulation.Resultado;
import com.schnrfl.procsimulator.simulation.ResultadoProcessos;
import com.schnrfl.procsimulator.simulation.Simulacao;
import com.schnrfl.procsimulator.simulation.SimulacaoProcessos;
import com.schnrfl.procsimulator.simulation.Simulador;

public class Main {

	public static void main(String[] args) {
		
		FileLogger logger = new FileLogger();
		//Criação do simulador
		Simulador simulador = new Simulador(logger);
		
		try {
			//Criação do gerador de eventos
			//Gerador gerador = new GeradorArquivoDat("files/randomico5000.dat", new ParserArquivoDat(), logger);
			//Gerador gerador = new GeradorArquivoDat("files/randomico500.dat", new ParserArquivoDat(), logger);
			Gerador gerador = new GeradorArquivoDat("files/entrada.dat", new ParserArquivoDat(), logger);
			//Gerador gerador = new GeradorEstaticoHomologacao(logger);
			
			//Criação do Motor de Simulação com eventos exógenos oriundos do arquivo .DAT
			Simulacao simulacao = new SimulacaoProcessos(gerador);
			
			//Inicia a simulação
			simulador.iniciar(simulacao);
			
			//Mostra os resultados da simulação
			Resultado resultado = simulador.getResultado();
			System.out.println(resultado);
			
			//Geração do relatório
			String caminhoParaOArquivoHtml = ((ResultadoProcessos)resultado).geraHtml();
			File arquivo = new File(caminhoParaOArquivoHtml);
			
			//Abre o navegador para mostrar o relatório
			Desktop.getDesktop().browse(arquivo.toURI());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
