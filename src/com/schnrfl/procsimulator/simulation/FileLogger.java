package com.schnrfl.procsimulator.simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileLogger implements Logger {

	private StringBuilder sb = new StringBuilder();

	/* (non-Javadoc)
	 * @see com.schnrfl.procsimulator.simulation.Logger#log(java.lang.String)
	 */
	@Override
	public void log(String message) {
		sb.append("\n" + message);
	}

	public void save() throws IOException {
		File fout = new File("files/resultado.txt");
		FileOutputStream fos = new FileOutputStream(fout);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(sb.toString());
		bw.close();
	}

	@Override
	public String toString() {
		return sb.toString();
	}

}
