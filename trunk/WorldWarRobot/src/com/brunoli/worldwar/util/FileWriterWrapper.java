package com.brunoli.worldwar.util;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileWriterWrapper {

	public FileWriterWrapper() {

	}

	public void write(String file, String contenido) {
		try {
			PrintStream printStream = new PrintStream(
					new FileOutputStream(file));
			printStream.print(contenido);
			printStream.close();
		} catch (Exception e) {
			System.err.println("Error al escribir en el archivo");
		}
	}
}
