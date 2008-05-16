package controladores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;

public class ControladorPanelConfig extends Controlador{

	public ControladorPanelConfig(ProxyModelo mod, Vista vis) {
		super(mod, vis);
	}
	
	 @SuppressWarnings("finally")
	public String doSaveConfigFile(String url, String ip)
    {
        PrintWriter output = null;

        try
        {
            Vector<String> arch = new Vector<String>();
            File rootFile = new File(".");
            File aFile = null;
            Scanner scanner = null;

            try
            {
                aFile = new File(rootFile.getCanonicalPath() + "/conf/zara.conf");

                try
                {
                    scanner = new Scanner(aFile);

                    while (scanner.hasNextLine())
                        arch.add(scanner.nextLine());
                }
                finally
                {
                    scanner.close();
                }
            }
            catch (IOException e)
            {
                //JOptionPane.showMessageDialog(this, e.getMessage(),Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
               return e.getMessage();
            }

            for (int i = 0; i < arch.size(); i++)
            {
                String aux = arch.elementAt(i);

                if (aux.length() >= 8)
                {
                    aux = aux.substring(0, 8);
                    if (aux.equalsIgnoreCase("Xmlroot "))
                    {
                        String path = url;
                        path.replace("\\", "\\\\");
                        path = arch.elementAt(i).substring(0, 8) + path;
                        arch.setElementAt(path, i);
                    }
                    else
                    {
                        aux = aux.substring(0, 4);
                        if (aux.equalsIgnoreCase("SRV "))
                        {
                            String ipS = ip;
                            ipS = arch.elementAt(i).substring(0, 4) + ipS;
                            arch.setElementAt(ipS, i);
                        }  
                    }
                }
            }

            output = new PrintWriter(new FileWriter(aFile));

            for(int i = 0; i < arch.size();i++)
                output.println(arch.elementAt(i));


          //  JOptionPane.showMessageDialog(this, "Archivo grabado",Constantes.APPLICATION_NAME,JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
            //Logger.getLogger(PanelConfig.class.getName()).log(Level.SEVERE, null, ex);
             return ex.getMessage();
        }
        finally
        {
            output.close();
            return "Archivo grabado";
        }
    }
}
