package com.schnrfl.procsimulator.simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.Map.Entry;

public class GeradorHtml {

	private ResultadoProcessos resultado;

	public GeradorHtml(ResultadoProcessos resultado) {
		this.resultado = resultado;
	}

	public String gera() throws IOException {
		System.out.println("Gerando html...");

		String template = "files/template.html";
		String content = new String(Files.readAllBytes(Paths.get(template)));

		String tempoDeSimulacao = String.valueOf(resultado.getDuracaoDaSimulacao());
		String tempoRealDeSimulacao = String.valueOf(resultado.getDuracaoRealDaSimulacao());
		String totalDeTempoNaFila = String.valueOf(resultado.getTotalDeTempoAcumuladoEmFila());
		String totalDeProcessosConcluidos = String.valueOf(resultado.getTotalDeProcessosConcluidos());
		String totalDeExecucoes = String.valueOf(resultado.getTotalDeExecucoes());
		
		String tempoMedioDeEsperaNaFilaDeProntos = String.valueOf(resultado.getTempoMedioDeEsperaNaFilaDeProntos());
		String numeroDeProcessosConcluidosPorUnidadeDeTempo = String
				.valueOf(resultado.getNumeroDeProcessosConcluidosPorUnidadeDeTempo());
		String numeroMedioDeExecucoes = String.valueOf(resultado.getNumeroMedioDeExecucoes());
		String numeroMedioDeExecucoesPorUnidadeDeTempo = String.valueOf(resultado.getNumeroMedioDeExecucoesPorUnidadeDeTempo());
		String numeroMaximoDeProcessosNaFilaDeProntos = String
				.valueOf(resultado.getNumeroMaximoDeProcessosNaFilaDeProntos());

		content = content.replace("${tempo_de_simulacao}", tempoDeSimulacao);
		content = content.replace("${tempo_real_de_simulacao}", tempoRealDeSimulacao);
		content = content.replace("${total_de_tempo_na_fila}", totalDeTempoNaFila);
		content = content.replace("${total_de_processos_concluidos}", totalDeProcessosConcluidos);
		content = content.replace("${total_de_execucoes}", totalDeExecucoes);
		
		content = content.replace("${tempo_medio_de_espera_na_fila_de_prontos}", tempoMedioDeEsperaNaFilaDeProntos);
		content = content.replace("${numero_de_processos_concluidos_por_unidade_de_tempo}", numeroDeProcessosConcluidosPorUnidadeDeTempo);
		content = content.replace("${numero_medio_de_execucoes}", numeroMedioDeExecucoes);
		content = content.replace("${numero_medio_de_execucoes_por_unidade_de_tempo}", numeroMedioDeExecucoesPorUnidadeDeTempo);
		content = content.replace("${numero_maximo_de_processos_na_fila_de_prontos}", numeroMaximoDeProcessosNaFilaDeProntos);

		Set<Entry<Long, Long>> valoresDoGrafico = resultado.getDadosGraficoFilaDeProntos().getDados().entrySet();
		String relatorio = "files/resultado.html";
		File fout = new File(relatorio);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		StringBuilder sb = new StringBuilder();

		sb.append("data.addRows(" + valoresDoGrafico.size() + ");");
		int linha = 0;
		for (Entry<Long, Long> entry : valoresDoGrafico) {
			Long instante = entry.getKey();
			Long emFila = entry.getValue();
			
			//System.out.println(instante + ": " + emFila);
			
			sb.append("data.setValue(" + linha + ", 0, '" + instante + "');");// instante
			sb.append("data.setValue(" + linha + ", 1, " + emFila + ");");// processos

			++linha;
		}
		content = content.replace("${popula_data_table}", sb.toString());

		bw.write(content);
		bw.close();

		System.out.println("Html criado...");
		
		return relatorio;
	}
}
