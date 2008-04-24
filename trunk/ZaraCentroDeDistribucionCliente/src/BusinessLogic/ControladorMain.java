package BusinessLogic;

import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import Varios.FileReaderWrapper;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ControladorMain extends Controlador
{
    public ControladorMain(ProxyModelo mod, Vista vis) 
    {
        super(mod, vis);
    }

    private SolicitudDistribucion parseXMLSD(String url)
    {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();


        xstream.alias("solicituddistribucion", SolicitudDistribucion.class);
        xstream.alias("articuloropa", ArticuloRopa.class);
        xstream.alias("articulohogar", ArticuloHogar.class);
        xstream.alias("tienda", Tienda.class);

        SolicitudDistribucion sol = (SolicitudDistribucion) xstream.fromXML(XML);

        return sol;
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

    public Vector<SolicitudDistribucion> doGetSolicitudes(String tienda)
    {
    	return null;
    }

    public void doMostrarSolicitud(String url)
    {
        ((VistaMain)this.getVista()).fillSDTable(this.parseXMLSD(url));
        ((VistaMain)this.getVista()).actualizar();
    }

    public void doGuardarSolicitud(SolicitudDistribucion soldis)
    {
        ((BusinessDelegate)(this.getModelo())).guardarSolicitud(soldis);
    }
    
    public Vector<Articulo> doGetArticulosEnvio(String tienda, String tipo)
    {
        return null;
    }
}