package GUI;

import controladores.ControladorPanelConfig;
import controladores.ControladorPanelEnvios;
import controladores.ControladorPanelGenSolFab;
import controladores.ControladorPanelNewArt;
import controladores.ControladorPanelRepArt;
import controladores.ControladorPanelSolDis;
import Vistas.VistaConfig;
import Vistas.VistaEnvios;
import Vistas.VistaGenSolFab;
import Vistas.VistaMain;
import Vistas.VistaNewArt;
import Vistas.VistaRepArt;
import Vistas.VistaSolDis;
import BusinessLogic.BusinessDelegate;

public class Main 
{
	public Main()
	{
	    BusinessDelegate modelo = new BusinessDelegate();
	    //Inicializo todas las vistas
	    VistaMain vMain = new VistaMain(modelo);
	    MenuPrincipal menu = vMain.getPrinc();
	    VistaConfig vConfig = new VistaConfig(modelo,menu);
	    VistaEnvios vEnvios = new VistaEnvios(modelo,menu);
	    VistaGenSolFab vGenSolFab = new VistaGenSolFab(modelo,menu);
	    VistaNewArt vNewArt = new VistaNewArt(modelo,menu);
	    VistaRepArt vRepArt = new VistaRepArt(modelo,menu);
	    VistaSolDis vSolDis = new VistaSolDis(modelo,menu);
		//Inicializo todos los controladores
	    ControladorPanelConfig cConfig = new ControladorPanelConfig(modelo,vConfig);
	    ControladorPanelEnvios cEnvios = new ControladorPanelEnvios(modelo,vEnvios);
	    ControladorPanelGenSolFab cGenSolFab = new ControladorPanelGenSolFab(modelo,vGenSolFab);
	    ControladorPanelNewArt cNewArt = new ControladorPanelNewArt(modelo,vNewArt);
	    ControladorPanelRepArt cRepArt = new ControladorPanelRepArt(modelo,vRepArt);
	    ControladorPanelSolDis cSolDis = new ControladorPanelSolDis(modelo,vSolDis);
	}
    
    public static void main(String[] args) 
    {
		new Main();
    }
}
