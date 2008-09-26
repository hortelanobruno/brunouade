package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Logger 
{
	@SuppressWarnings("deprecation")
	private static String getNombre()
	{
		Date d = new Date();
		String nombre = d.getYear() + "-" + d.getMonth() + "-"+d.getDay();
		return nombre;
	}
	
	public static void log(String msg)
	{
		File fd = new File("logs");
		if(!(fd.exists())) fd.mkdirs();
		
		try
		{
			String nombre = getNombre();
			FileOutputStream output = new FileOutputStream(new File("logs/"+nombre+".log"),true);
			output.write(msg.getBytes());
			output.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("No se encuentra el archivo.");
		}
		catch(IOException e)
		{
			System.out.println("Error al grabar el archivo.");
		}
	}
}
