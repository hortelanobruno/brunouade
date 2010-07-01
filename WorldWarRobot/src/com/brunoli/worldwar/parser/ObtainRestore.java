package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.util.RestoreValue;
import com.brunoli.worldwar.util.UtilsWW;

public class ObtainRestore {

	public static void main(String[] arg) {
		ObtainRestore o = new ObtainRestore();
		StringBuilder page = o.leerArchivo("./files/restore.html");
		if (page != null) {
			o.parsearPagina(page);
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}

	public ObtainRestore() {
	}

	public void parsearPagina(StringBuilder page) {
		page = new StringBuilder(page.toString().replaceAll("\"", "'"));
		RestoreValue restore = leerDatos(page);
		mostrarDatos(restore);
	}

	private void mostrarDatos(RestoreValue restore) {
		System.out.println("Restore URL: "+restore.getUrlRestore());
		System.out.println("Restore Value: "+restore.getValueRestore());
		System.out.println("Vault Value: "+restore.getValueVault());
	}

	private RestoreValue leerDatos(StringBuilder page) {
		RestoreValue rv = new RestoreValue();
		String a = page.toString().split("hospitalText")[1];
		rv.setUrlRestore("/hospital.php"+a.split("/hospital.php")[1].split("'")[0]);
		rv.setValueVault(UtilsWW.parsearMoney(a.split("<img")[1].split("</")[0].split(">")[1]));
		rv.setValueRestore(UtilsWW.parsearMoney(a.split("<img")[2].split("</")[0].split(">")[1]));
		return rv;
	}

	public StringBuilder leerArchivo(String pathFile) {
		try {
			Scanner scanner = new Scanner(new File(pathFile));

			StringBuilder page = new StringBuilder();

			while (scanner.hasNext()) {
				page.append(scanner.nextLine());
			}

			return page;
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (Exception e) {
			System.out.println("Exception. " + e.getMessage());
		}
		return null;
	}

}
