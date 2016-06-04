package com.schnrfl.procsimulator.generation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CriaArquivoDat {

	public static void main(String args[]) throws IOException {
		File fout = new File("files/randomico.dat");
		FileOutputStream fos = new FileOutputStream(fout);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (int i = 0; i < 5000; i++) {
			int pid = i;
			long timeSlice = Randomizador.randomiza(1, 10);
			long tipo = Randomizador.randomiza(0, 1);
			
			bw.write(pid + ";" + timeSlice + ";" + tipo);
			bw.newLine();
		}

		bw.write("-1;0;0");

		bw.close();
	}

}
