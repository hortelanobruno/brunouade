package GUI;

import javax.swing.JOptionPane;

import controladores.ControladorPanelConfig;
import controladores.ControladorPanelEnvios;
import controladores.ControladorPanelGenSolFab;
import controladores.ControladorPanelNewArt;
import controladores.ControladorPanelRepArt;
import controladores.ControladorPanelSolDis;
import Varios.Constantes;
import Vistas.VistaConfig;
import Vistas.VistaEnvios;
import Vistas.VistaGenSolFab;
import Vistas.VistaMain;
import Vistas.VistaNewArt;
import Vistas.VistaRepArt;
import Vistas.VistaSolDis;
import BusinessLogic.BusinessDelegate;
import Exceptions.ErrorConeccionException;

public class Main 
{
	public Main()
	{
	    BusinessDelegate modelo;
		try {
			modelo = new BusinessDelegate();
			VistaConfig vConfig = new VistaConfig(modelo);
		    VistaEnvios vEnvios = new VistaEnvios(modelo);
		    VistaGenSolFab vGenSolFab = new VistaGenSolFab(modelo);
		    VistaNewArt vNewArt = new VistaNewArt(modelo);
		    VistaRepArt vRepArt = new VistaRepArt(modelo);
		    VistaSolDis vSolDis = new VistaSolDis(modelo);
		    VistaMain vMain = new VistaMain(modelo);
		    MenuPrincipal princ = new MenuPrincipal(vMain,vSolDis,vEnvios,vGenSolFab,vRepArt,vNewArt,vConfig);
	        princ.setVisible(true);
	        
	        vMain.setRef(princ);
	        vSolDis.setRef(princ);
	        vEnvios.setRef(princ);
	        vGenSolFab.setRef(princ);
	        vNewArt.setRef(princ);
	        vRepArt.setRef(princ);
	        vConfig.setRef(princ);
	        
			//Inicializo todos los controladores
		    ControladorPanelConfig cConfig = new ControladorPanelConfig(modelo,vConfig);
		    ControladorPanelEnvios cEnvios = new ControladorPanelEnvios(modelo,vEnvios);
		    ControladorPanelGenSolFab cGenSolFab = new ControladorPanelGenSolFab(modelo,vGenSolFab);
		    ControladorPanelNewArt cNewArt = new ControladorPanelNewArt(modelo,vNewArt);
		    ControladorPanelRepArt cRepArt = new ControladorPanelRepArt(modelo,vRepArt);
		    ControladorPanelSolDis cSolDis = new ControladorPanelSolDis(modelo,vSolDis);
		} catch (ErrorConeccionException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),Constantes.APPLICATION_NAME,JOptionPane.ERROR_MESSAGE);
		}
	    //Inicializo todas las vistas
	    
	    
	    
	}
    
    public static void main(String[] args) 
    {
		new Main();
    }
}
