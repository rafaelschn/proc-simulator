package com.schnrfl.procsimulator.simulation;

public class NoopLogger extends FileLogger {

	@Override
	public void log(String message) {
	}

	@Override
	public void save() {
	}

	@Override
	public String toString() {
		return "";
	}

}
