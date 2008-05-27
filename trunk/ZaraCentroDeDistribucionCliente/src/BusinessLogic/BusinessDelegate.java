package BusinessLogic;

import RemoteMVCFramework.ProxyModelo;
import Varios.Constantes;

import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BusinessDelegate extends ProxyModelo
{
	private ServerFacade modCD = null;
	private InitialContext initialContect = null;
	private String naming = Constantes.BEAN_STRING;
   // private Conexion con; //Conexion SQL Temporal

    public BusinessDelegate()
    {
        super();
        this.inicializarContexto();
        this.getServerFacade();
        //this.getConnection();
    }
    
    public int getTestNumber()
    {
    	return this.getModCD().getTest();
    }
    
    @SuppressWarnings({ "unchecked", "unchecked", "unused" })
	private void inicializarContexto() 
    {
		try
		{
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			//Url completa de ubicacion del servidor de aplicaciones

			props.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099"/*"jnp://" + ((VistaMain)this.getVista()).getPrinc().getServerIP()+":1099"*/);
//			Objeto del tipo InitialContext
			initialContect = new InitialContext(props);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
    
    @SuppressWarnings("unused")
	private ServerFacade getServerFacade()
    {
    	try
    	{
    		if(this.modCD == null) this.modCD = (ServerFacade) this.initialContect.lookup(naming);
    	}
    	catch(NamingException ex)
    	{
    		
    	}
    	return this.modCD;
    }
 
    /* protected void getConnection() 
     {
    	if (modCD == null) 
        {
            if(System.getSecurityManager() == null) System.setSecurityManager(new RMISecurityManager());
            try 
            {
                String name = "//localhost/ServidorZara";
                modCD = (ModeloCDBusinessDelegate) Naming.lookup(name);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    	}
    }*/
     
    public Vector<String> getDescripciones(Vector<Integer> codigos){
    	
    	return
    }
    
    
     public void guardarSolicitud(SolicitudDistribucionVO soldis)
     {
        try
        {
        	this.getModCD().guardarSolicitud(soldis);
            //Aca actualiza la db, pero en realidad esa implementacion viene del servidor.
            //Aca seria algo asi.
            //try {
        	//this.getBusinessDelegate().setSolicitud(dtn);
            //	vista.actualizar();
            //} catch (RemoteException e) {
            //	e.printStackTrace();
            //}
            
           // System.out.println("estoy en guardar");
         /*   Conexion.driverOdbc();
           // con = new Conexion("sa", "123456");
           // System.out.println("estoy x conectar");
            if (con.abrirConexion()) 
            {
                java.sql.Statement stmt = con.getCon().createStatement();
                java.sql.CallableStatement cs;
                int ref = 0;
                //System.out.println("cantidad de filas "+dtm.getRowCount());
                for (int i = 0; i < dtm.getRowCount(); i++) 
                {
                    ref = Integer.parseInt("" + dtm.getValueAt(i, 1));
                    if(Integer.valueOf(""+dtm.getValueAt(i,5)) < Integer.valueOf(""+dtm.getValueAt(i,3)))
                    {
                        //System.out.println("updateeeeeeeeeeeee");  
                       // System.out.println("CANTIDAD PEDIDA= " + dtm.getValueAt(i, 5).toString());
                        cs = con.getCon().prepareCall("{call dbo.actualizarStock(?,?)}");
                        cs.setString(1, String.valueOf(ref));
                        cs.setInt(2,Integer.parseInt(dtm.getValueAt(i, 5).toString()));
                       // System.out.println("Filas devueltas: " + cs.executeUpdate());
                        cs.close();
                        //stmt.executeUpdate("update articulos set [stock disponible]=[stock disponible] - "+dtm.getValueAt(i, 5)+" where referencia = '"+ref+"'");
                        //System.out.println("ya actualice el stock");
                        //int pedir = Integer.valueOf(""+dtm.getValueAt(i, 3)) - Integer.valueOf(""+dtm.getValueAt(i, 5));
                        
                        cs = con.getCon().prepareCall("{call dbo.agregarSolicitudFabricacion(?,?,?)}");
                        cs.setInt(1, Integer.parseInt(dtm.getValueAt(i, 0).toString()));
                        cs.setString(2, String.valueOf(ref));
                        cs.setInt(3,Integer.parseInt(dtm.getValueAt(i, 5).toString()));
                        
                        cs.executeUpdate();
                        cs.close();
                        //System.out.println("ya inserte sol fab");
                        //stmt.executeUpdate("insert into [solicitudes fabricacion] values("+dtm.getValueAt(i, 0)+","+String.valueOf(ref)+","+pedir+")");
                        
                        cs = con.getCon().prepareCall("{call dbo.agregarSolicitudDistribucion(?,?,?,?)}");
                        cs.setInt(1, Integer.parseInt(dtm.getValueAt(i, 0).toString()));
                        cs.setString(2, String.valueOf(ref));
                        cs.setInt(3,Integer.parseInt(dtm.getValueAt(i, 3).toString()));
                        cs.setInt(4,Integer.parseInt(dtm.getValueAt(i, 5).toString()));
                        
                        cs.executeUpdate();
                        cs.close();
                        //stmt.executeUpdate("insert into [solicitudes distribucion] values("+dtm.getValueAt(i, 0)+","+String.valueOf(ref)+","+dtm.getValueAt(i, 3)+","+dtm.getValueAt(i, 5)+")");
                        
                       // System.out.println("ya inserte sol dist");
                    }
                    else
                    {
                        //System.out.println("EN EL ELSE\n");
                        //System.out.println("Ref: " + String.valueOf(ref)+ "\nCANTIDAD PEDIDA= " + dtm.getValueAt(i, 5).toString());
                        cs = con.getCon().prepareCall("{call dbo.actualizarStock(?,?)}");
                        cs.setString(1, String.valueOf(ref));
                        cs.setInt(2,Integer.parseInt(dtm.getValueAt(i, 5).toString()));
                        //System.out.println("Filas devueltas: " + cs.executeUpdate());
                        cs.close();
                        
                       // System.out.println("actualizo stock en el else\n");
                      //stmt.executeUpdate("update articulos set [stock disponible]=[stock disponible] - "+dtm.getValueAt(i, 5)+" where referencia = '"+ref+"'");
                        cs = con.getCon().prepareCall("{call dbo.agregarSolicitudDistribucion(?,?,?,?)}");
                        cs.setInt(1, Integer.parseInt(dtm.getValueAt(i, 0).toString()));
                        cs.setString(2, String.valueOf(ref));
                        cs.setInt(3,Integer.parseInt(dtm.getValueAt(i, 3).toString()));
                        cs.setInt(4,Integer.parseInt(dtm.getValueAt(i, 5).toString()));
                        
                        cs.executeUpdate();
                        cs.close();
                        
                       // System.out.println("inserto sol dist en el else\n");
                        //stmt.executeUpdate("insert into [solicitudes distribucion] values("+dtm.getValueAt(i, 0)+","+ref+","+dtm.getValueAt(i, 3)+","+dtm.getValueAt(i, 5)+")");
                    }
                }
                stmt.close();
                con.cerrarConexion();
            }*/
            vista.actualizar();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
     
     public int getStock(int codigo)
     {
    	 return 0;
          /*  Conexion.driverOdbc();
            con = new Conexion("sa", "123456");
            //System.out.println("estoy x conectar");
            int a = 0;
            if (con.abrirConexion()) 
            {
               // System.out.println("pase1");
                try 
                {
                    ResultSet rs = null;
                    CallableStatement cs = con.getCon().prepareCall("{call dbo.getStock(?)}");//select [stock disponible] from articulos where referencia = " + codigo);
                    cs.setString(1, String.valueOf(codigo));
                    
                    rs = cs.executeQuery();
                    while (rs.next()) a = rs.getInt(1);
                        
                       // System.out.println(""+a);
                   rs.close();
                   cs.close();
                   con.cerrarConexion();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return a;*/
     }
     
     public String getDescripcion(int codigo)
     {
    	 return null;
         	/*Conexion.driverOdbc();
            con = new Conexion("sa", "123456");
            //System.out.println("estoy x conectar");
            String a = new String();
            if (con.abrirConexion()) 
            {
                //System.out.println("pase2");
                try 
                {
                    ResultSet rs = null;
                    CallableStatement cs = con.getCon().prepareCall("{call dbo.getDescripcion(?)}");//"select descripcion from articulos where referencia = " + codigo);
                    cs.setString(1, String.valueOf(codigo));
                   
                    rs = cs.executeQuery();
                    while (rs.next()) a = rs.getString(1);
                        //System.out.println(a);
                    
                    rs.close();
                    cs.close();
                    con.cerrarConexion();
                } 
                catch (SQLException ex)
                {
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return a;*/
     }

	public ServerFacade getModCD() 
	{
		return modCD;
	}
}
