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
		try
		{
			File fdir = new File("logs");
			if(!(fdir.exists())) fdir.mkdirs();
			
			String nombre = getNombre();
			File fd = new File("logs/"+nombre+".log");
			if(!(fd.exists())) fd.createNewFile();
		
			FileOutputStream output = new FileOutputStream(fd,true);
			
			for( int i = 0; i < msg.length(); i++) 
                output.write((int) msg.charAt(i));

			output.flush();
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
