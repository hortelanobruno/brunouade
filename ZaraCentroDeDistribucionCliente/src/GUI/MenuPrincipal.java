/*
 * MenuPrincipal.java
 *
 * Created on 21 de mayo de 2008, 23:59
 */
package GUI;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Paneles.PanelConfig;
import Paneles.PanelDB;
import Paneles.PanelEnvios;
import Paneles.PanelGenSolFab;
import Paneles.PanelNewArt;
import Paneles.PanelRepArt;
import Paneles.PanelSolDist;
import Varios.Constantes;
import Vistas.VistaConfig;
import Vistas.VistaEnvios;
import Vistas.VistaGenSolFab;
import Vistas.VistaMain;
import Vistas.VistaNewArt;
import Vistas.VistaRepArt;
import Vistas.VistaSolDis;

/**
 *
 * @author  Administrador
 */
public class MenuPrincipal extends javax.swing.JFrame 
{
	private static final long serialVersionUID = 7725034541164342296L;
	private VistaEnvios vistaEnvios;
	private VistaGenSolFab vistaGenSolFab;
	private VistaNewArt vistaNewArt;
	private VistaRepArt vistaRepArt;
    private VistaConfig vistaConfig;
    private VistaSolDis vistaSolDis;
    private VistaMain vistaMain;
    
    private JPanel activePanel; //este atributo es para saber cual es el panel que se esta mostrando para 
    //hacer el update de la vista.

    private String defaltXmlPath;
    private String serverIP;
    private File confFile;

    /** Creates new form MenuPrincipal 
     * @param main */

    public MenuPrincipal(VistaMain main, VistaSolDis solDis, VistaEnvios envios, VistaGenSolFab genSolFab, VistaRepArt repArt, VistaNewArt newArt, VistaConfig config) {
        initComponents();
        setLookAndFeel();
        isPanel1Selected = true;
        this.setUpRootFile();
        this.setDefaltXmlPath(this.getParam("Xmlroot ", 8).replace("\\\\", "\\"));
        this.setServerIP(this.getParam("SRV ", 4));
        
        this.vistaMain = main;
        this.vistaSolDis = solDis;
        this.vistaEnvios = envios;
        this.vistaGenSolFab = genSolFab;
        this.vistaRepArt = repArt;
        this.vistaNewArt = newArt;
        this.vistaConfig = config;
        cargarHora();
    }

    public void cargarHora(){
        Timer timer = new Timer(true);
        timer.schedule(new Runtimer(), 1000, 1000);
    }
    
    
    class Runtimer extends TimerTask {

        Calendar cal;
        
        DateFormat format;
        @Override
        public void run() {
            cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
            date = cal.getTime();
            format = DateFormat.getTimeInstance();
            labelHora.setText(format.format(date));
        }
        
    }
    
    private void setUpRootFile() {
        try {
            File rootFile = new File(".");
            this.confFile = new File(rootFile.getCanonicalPath() + "/conf/zara.conf");
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void setLookAndFeel() throws HeadlessException
    {
        try
        {
            UIManager.setLookAndFeel(Constantes.WINDOWS);
            SwingUtilities.updateComponentTreeUI(this);

            Toolkit t = Toolkit.getDefaultToolkit();
            this.setLocation((int) (t.getScreenSize().getWidth() - this.getWidth()) / 2, (int)( t.getScreenSize().getHeight() - this.getHeight() )/ 2);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePanel() {
        if (activePanel instanceof PanelEnvios) {
            ((PanelEnvios) activePanel).update();
        }
        if (activePanel instanceof PanelConfig) {
            ((PanelConfig) activePanel).update();
        }
        if (activePanel instanceof PanelGenSolFab) {
            ((PanelGenSolFab) activePanel).update();
        }
        if (activePanel instanceof PanelNewArt) {
            ((PanelNewArt) activePanel).update();
        }
        if (activePanel instanceof PanelRepArt) {
            ((PanelRepArt) activePanel).update();
        }
        if (activePanel instanceof PanelSolDist) {
            ((PanelSolDist) activePanel).update();
        }
        if (activePanel instanceof PanelDB) {
            ((PanelDB) activePanel).update();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	this.setTitle(Constantes.APPLICATION_NAME);
        jToolBar1 = new javax.swing.JToolBar();
        buttonSolDist = new javax.swing.JButton();
        buttonEnvTienda = new javax.swing.JButton();
        buttonSolFabr = new javax.swing.JButton();
        buttonRepArt = new javax.swing.JButton();
        buttonNewArt = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        menuItemSolDistr = new javax.swing.JMenuItem();
        menuItemEnvTienda = new javax.swing.JMenuItem();
        menuItemSolFab = new javax.swing.JMenuItem();
        menuItemRepArt = new javax.swing.JMenuItem();
        menuItemNewArt = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuItemBaseDatos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        menuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemConfiguracion = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        buttonSolDist.setIcon(new javax.swing.ImageIcon(".\\icons\\showchild_mode.gif")); // NOI18N
        buttonSolDist.setText("Solicitud de Distribucion");
        buttonSolDist.setFocusable(false);
        buttonSolDist.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonSolDist.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSolDist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSolDistActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonSolDist);

        buttonEnvTienda.setIcon(new javax.swing.ImageIcon(".\\icons\\forward_nav.gif")); // NOI18N
        buttonEnvTienda.setText("Envios a Tienda");
        buttonEnvTienda.setFocusable(false);
        buttonEnvTienda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonEnvTienda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEnvTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnvTiendaActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonEnvTienda);

        buttonSolFabr.setIcon(new javax.swing.ImageIcon(".\\icons\\addtsk_tsk.gif")); // NOI18N
        buttonSolFabr.setText("Solicitud de Fabricacion");
        buttonSolFabr.setFocusable(false);
        buttonSolFabr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonSolFabr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSolFabr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSolFabrActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonSolFabr);

        buttonRepArt.setIcon(new javax.swing.ImageIcon(".\\icons\\import_wiz.gif")); // NOI18N
        buttonRepArt.setText("Reposicion de Articulos");
        buttonRepArt.setFocusable(false);
        buttonRepArt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonRepArt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonRepArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRepArtActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonRepArt);

        buttonNewArt.setIcon(new javax.swing.ImageIcon(".\\icons\\file_obj.gif")); // NOI18N
        buttonNewArt.setText("Nuevo Articulo");
        buttonNewArt.setFocusable(false);
        buttonNewArt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonNewArt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNewArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewArtActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonNewArt);

        jLabel2.setText("                                                                                     ");
        jToolBar1.add(jLabel2);

        labelHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelHora.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar1.add(labelHora);

        jLabel1.setIcon(new javax.swing.ImageIcon(".\\icons\\background.jpg")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 959, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consola", jPanel2);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historial", jPanel3);

        jMenu1.setText("Archivo");

        jMenu4.setText("Servicios");

        menuItemSolDistr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSolDistr.setText("Solicitud de distribucion");
        menuItemSolDistr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolDistrActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemSolDistr);

        menuItemEnvTienda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuItemEnvTienda.setText("Generar envio a tienda");
        menuItemEnvTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEnvTiendaActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemEnvTienda);

        menuItemSolFab.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSolFab.setText("Generar solicitud de fabricacion");
        menuItemSolFab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSolFabActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemSolFab);

        menuItemRepArt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuItemRepArt.setText("Reposicion articulos de fabrica");
        menuItemRepArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRepArtActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemRepArt);

        menuItemNewArt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuItemNewArt.setText("Informacion nuevo articulo");
        menuItemNewArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNewArtActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemNewArt);

        jMenu1.add(jMenu4);

        jMenu5.setText("Base de Datos");

        menuItemBaseDatos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuItemBaseDatos.setText("Base de Datos");
        menuItemBaseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBaseDatosActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemBaseDatos);

        jMenu1.add(jMenu5);
        jMenu1.add(jSeparator1);

        menuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        menuItemConfiguracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemConfiguracion.setText("Configuracion");
        menuItemConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfiguracionActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemConfiguracion);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");

        menuItemAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAyuda.setText("Acerca de ...");
        menuItemAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAyudaActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemAyuda);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .add(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

private void buttonSolDistActionPerformed(java.awt.event.ActionEvent evt) {                                              
    // solicitud de distribucion
    if (!isPanelSDSelected) {
        panelSD = new PanelSolDist(this,vistaSolDis);
        ponerPanel(panelSD);
    }
}                                             

private void menuItemAyudaActionPerformed(java.awt.event.ActionEvent evt) {                                              
    JOptionPane.showMessageDialog(this, Constantes.ABOUT_TEXT, Constantes.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
}                                             

private void menuItemConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {                                                      
    // panel configuracion
    if (!isPanelConfigSelected) {
        panelConfig = new PanelConfig(this,vistaConfig);
        ponerPanel(panelConfig);
    }
}                                                     

private void menuItemSolDistrActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    // solicitud de distribucion
    if (!isPanelSDSelected) {
        panelSD = new PanelSolDist(this,vistaSolDis);
        ponerPanel(panelSD);
    }
}                                                

private void menuItemEnvTiendaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    // generar envio a tienda
    if (!isPanelEnviosSelected) {
        panelEnvios = new PanelEnvios(this,vistaEnvios);
        ponerPanel(panelEnvios);
    }
}                                                 

private void menuItemSolFabActionPerformed(java.awt.event.ActionEvent evt) {                                               
    // solicitud a fabricacion
    if (!isPanelGSFSelected) {
        panelGSF = new PanelGenSolFab(this,vistaGenSolFab);
        ponerPanel(panelGSF);
    }
}                                              

private void menuItemRepArtActionPerformed(java.awt.event.ActionEvent evt) {                                               
    // reponer articulos de fabrica
    if (!isPanelRepArtSelected) {
        panelRepArt = new PanelRepArt(this,vistaRepArt);
        ponerPanel(panelRepArt);
    }
}                                              

private void menuItemNewArtActionPerformed(java.awt.event.ActionEvent evt) {                                               
    // informacion nuevo articulo
    if (!isPanelNewArtSelected) {
        panelNewArt = new PanelNewArt(this,vistaNewArt);
        ponerPanel(panelNewArt);
    }
}                                              

private void menuItemBaseDatosActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    // Panel Base de datos
    if (!isPanelDB) {
        panelDB = new PanelDB(this);
        ponerPanel(panelDB);
    }
}                                                 

private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {                                              

    this.CloseApplication();
}                                             

private void buttonEnvTiendaActionPerformed(java.awt.event.ActionEvent evt) {                                                
    // generar envio a tienda
    if (!isPanelEnviosSelected) {
        panelEnvios = new PanelEnvios(this,vistaEnvios);
        ponerPanel(panelEnvios);
    }
}                                               

private void buttonSolFabrActionPerformed(java.awt.event.ActionEvent evt) {                                              
    // solicitud a fabricacion
    if (!isPanelGSFSelected) {
        panelGSF = new PanelGenSolFab(this,vistaGenSolFab);
        ponerPanel(panelGSF);
    }
}                                             

private void buttonRepArtActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // reponer articulos de fabrica
    if (!isPanelRepArtSelected) {
        panelRepArt = new PanelRepArt(this,vistaRepArt);
        ponerPanel(panelRepArt);
    }
}                                            

private void buttonNewArtActionPerformed(java.awt.event.ActionEvent evt) {                                             
    // informacion nuevo articulo
    if (!isPanelNewArtSelected) {
        panelNewArt = new PanelNewArt(this,vistaNewArt);
        ponerPanel(panelNewArt);
    }
}                                            

    private void CloseApplication() {
        if (JOptionPane.showConfirmDialog(this, "Esta seguro que desea cerrar la aplicacion?", Constantes.APPLICATION_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
            this.dispose();
        }
    }
                                 

    public void ponerPanelPrincipal() {
        this.setTitle(Constantes.APPLICATION_NAME + "Menu principal - ");

        this.isPanelSDSelected = false;
        this.isPanelGSFSelected = false;
        this.isPanelEnviosSelected = false;
        this.isPanelNewArtSelected = false;
        this.isPanelRepArtSelected = false;
        this.isPanelConfigSelected = false;

        if (isPanel1Selected) {
            return;
        }

        if (this.isAncestorOf(panelSD)) {
            this.remove(panelSD);
        }
        if (this.isAncestorOf(panelGSF)) {
            this.remove(panelGSF);
        }
        if (this.isAncestorOf(panelEnvios)) {
            this.remove(panelEnvios);
        }
        if (this.isAncestorOf(panelNewArt)) {
            this.remove(panelNewArt);
        }
        if (this.isAncestorOf(panelRepArt)) {
            this.remove(panelRepArt);
        }
        if (this.isAncestorOf(panelConfig)) {
            this.remove(panelConfig);
        }
        this.setActivePanel(jPanel1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .add(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.isPanel1Selected = true;
    }

    private void ponerPanel(JPanel panel) {
        if (isPanel1Selected) {
            this.remove(jPanel1);
            this.repaint();
        }

        if (isPanelGSFSelected) {
            this.remove(panelGSF);
            this.repaint();
        }

        if (isPanelSDSelected) {
            this.remove(panelSD);
            this.repaint();
        }

        if (isPanelEnviosSelected) {
            this.remove(panelEnvios);
            this.repaint();
        }

        if (isPanelRepArtSelected) {
            this.remove(panelRepArt);
            this.repaint();
        }

        if (isPanelNewArtSelected) {
            this.remove(panelNewArt);
            this.repaint();
        }

        if (isPanelConfigSelected) {
            this.remove(panelConfig);
            this.repaint();
        }

        if (isPanelDB) {
            this.remove(panelDB);
            this.repaint();
        }

        if (panel instanceof PanelEnvios) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelSDSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelConfigSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Generar envios - ");
            this.setActivePanel(panelEnvios);
            this.isPanelEnviosSelected = true;
        }

        if (panel instanceof PanelGenSolFab) {
            this.isPanel1Selected = false;
            this.isPanelSDSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelConfigSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Generar solicitud de fabricacion - ");
            this.setActivePanel(panelGSF);
            this.isPanelGSFSelected = true;
        }

        if (panel instanceof PanelNewArt) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelSDSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelConfigSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Nuevo articulo - ");
            this.setActivePanel(panelNewArt);
            this.isPanelNewArtSelected = true;
        }

        if (panel instanceof PanelRepArt) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelSDSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelConfigSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Reponer articulos - ");
            this.setActivePanel(panelRepArt);
            this.isPanelRepArtSelected = true;
        }

        if (panel instanceof PanelSolDist) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelConfigSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Panel de solicitud de distribucion - ");
            this.setActivePanel(panelSD);
            this.isPanelSDSelected = true;
        }

        if (panel instanceof PanelConfig) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelSDSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelDB = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Configuracion - ");
            this.setActivePanel(panelConfig);
            this.isPanelConfigSelected = true;
        }

        if (panel instanceof PanelDB) {
            this.isPanel1Selected = false;
            this.isPanelGSFSelected = false;
            this.isPanelSDSelected = false;
            this.isPanelEnviosSelected = false;
            this.isPanelRepArtSelected = false;
            this.isPanelNewArtSelected = false;
            this.isPanelConfigSelected = false;
            this.setTitle(Constantes.APPLICATION_NAME + "Base de Datos - ");
            this.setActivePanel(panelDB);
            this.isPanelDB = true;
        }
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(panel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .add(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
 }
    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonEnvTienda;
    private javax.swing.JButton buttonNewArt;
    private javax.swing.JButton buttonRepArt;
    private javax.swing.JButton buttonSolDist;
    private javax.swing.JButton buttonSolFabr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem menuItemAyuda;
    private javax.swing.JMenuItem menuItemBaseDatos;
    private javax.swing.JMenuItem menuItemConfiguracion;
    private javax.swing.JMenuItem menuItemEnvTienda;
    private javax.swing.JMenuItem menuItemNewArt;
    private javax.swing.JMenuItem menuItemRepArt;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenuItem menuItemSolDistr;
    private javax.swing.JMenuItem menuItemSolFab;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelHora;
    // End of variables declaration                   

    private Date date;
    private PanelSolDist panelSD;
    private PanelGenSolFab panelGSF;
    private PanelEnvios panelEnvios;
    private PanelNewArt panelNewArt;
    private PanelRepArt panelRepArt;
    private PanelConfig panelConfig;
    private PanelDB panelDB;
    
    private boolean isPanelConfigSelected;
    private boolean isPanelSDSelected;
    private boolean isPanel1Selected;
    private boolean isPanelGSFSelected;
    private boolean isPanelEnviosSelected;
    private boolean isPanelNewArtSelected;
    private boolean isPanelRepArtSelected;
    private boolean isPanelDB;
    
    private boolean isParam(String content, String param, int size)
    {
        if(content.length() >= size)
        {
            String aux = new String(content.substring(0,size));
            if(aux.equalsIgnoreCase(param)) return true;
        }
        return false;
    }
    
    private String returnString(String param)
    {
        StringBuffer sb = new StringBuffer();
        
        for(int i = param.indexOf(' ')+1; i<param.length(); i++)
            sb.append(param.charAt(i));
        return sb.toString();
    }
    
    private String getParam(String param, int size)
    {
         StringBuffer contents = null;
         BufferedReader input = null;
         
         try 
         {
               input =  new BufferedReader(new FileReader(confFile));
             
                String line = null;
                boolean a;
                while (( line = input.readLine()) != null)
                {
                    contents = new StringBuffer();
                    contents.append(line);

                    a = this.isParam(contents.toString(),param,size);

                    if(a) 
                        return this.returnString(contents.toString());
                }
                return null;
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(null, ex.getMessage());
          return null;
        }
        finally
        {
            try
            {
                input.close();
                //return null;
            }
            catch(IOException e)
            {
                
            }
        }
    }
    
    public JPanel getActivePanel()
    {
        return activePanel;
    }

    public void setActivePanel(JPanel activePanel)
    {
        this.activePanel = activePanel;
    }

    public javax.swing.JTextArea getJTextArea1() {
        return jTextArea1;
    }

    public void setJTextArea1(javax.swing.JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

    public String getDefaltXmlPath()
    {
        return defaltXmlPath;
    }

    public void setDefaltXmlPath(String defaltXmlPath)
    {
        this.defaltXmlPath = defaltXmlPath;
    }
    
    public String getServerIP()
    {
        return serverIP;
    }

    public void setServerIP(String IPServer)
    {
        this.serverIP = IPServer;
    }

	public VistaConfig getVistaConfig() {
		return vistaConfig;
	}

	public void setVistaConfig(VistaConfig vistaConfig) {
		this.vistaConfig = vistaConfig;
	}

	public VistaSolDis getVistaSolDis() {
		return vistaSolDis;
	}

	public void setVistaSolDis(VistaSolDis vistaSolDis) {
		this.vistaSolDis = vistaSolDis;
	}

	public PanelConfig getPanelConfig() {
		return panelConfig;
	}

	public void setPanelConfig(PanelConfig panelConfig) {
		this.panelConfig = panelConfig;
	}

	public PanelDB getPanelDB() {
		return panelDB;
	}

	public void setPanelDB(PanelDB panelDB) {
		this.panelDB = panelDB;
	}

	public PanelEnvios getPanelEnvios() {
		return panelEnvios;
	}

	public void setPanelEnvios(PanelEnvios panelEnvios) {
		this.panelEnvios = panelEnvios;
	}

	public PanelGenSolFab getPanelGSF() {
		return panelGSF;
	}

	public void setPanelGSF(PanelGenSolFab panelGSF) {
		this.panelGSF = panelGSF;
	}

	public PanelNewArt getPanelNewArt() {
		return panelNewArt;
	}

	public void setPanelNewArt(PanelNewArt panelNewArt) {
		this.panelNewArt = panelNewArt;
	}

	public PanelRepArt getPanelRepArt() {
		return panelRepArt;
	}

	public void setPanelRepArt(PanelRepArt panelRepArt) {
		this.panelRepArt = panelRepArt;
	}

	public PanelSolDist getPanelSD() {
		return panelSD;
	}

	public void setPanelSD(PanelSolDist panelSD) {
		this.panelSD = panelSD;
	}


	public VistaEnvios getVistaEnvios() {
		return vistaEnvios;
	}


	public void setVistaEnvios(VistaEnvios vistaEnvios) {
		this.vistaEnvios = vistaEnvios;
	}


	public VistaGenSolFab getVistaGenSolFab() {
		return vistaGenSolFab;
	}


	public void setVistaGenSolFab(VistaGenSolFab vistaGenSolFab) {
		this.vistaGenSolFab = vistaGenSolFab;
	}


	public VistaMain getVistaMain() {
		return vistaMain;
	}


	public void setVistaMain(VistaMain vistaMain) {
		this.vistaMain = vistaMain;
	}


	public VistaNewArt getVistaNewArt() {
		return vistaNewArt;
	}


	public void setVistaNewArt(VistaNewArt vistaNewArt) {
		this.vistaNewArt = vistaNewArt;
	}


	public VistaRepArt getVistaRepArt() {
		return vistaRepArt;
	}


	public void setVistaRepArt(VistaRepArt vistaRepArt) {
		this.vistaRepArt = vistaRepArt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public javax.swing.JLabel getLabelHora() {
		return labelHora;
	}

	public void setLabelHora(javax.swing.JLabel labelHora) {
		this.labelHora = labelHora;
	}
}
