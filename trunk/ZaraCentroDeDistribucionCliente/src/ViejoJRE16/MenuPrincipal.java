package ViejoJRE16;

import BusinessLogic.SolicitudDistribucionVO;
import Paneles.PanelConfig;
import Paneles.PanelDB;
import Paneles.PanelEnvios;
import Paneles.PanelGenSolFab;
import Paneles.PanelNewArt;
import Paneles.PanelRepArt;
import Paneles.PanelSolDist;
import Varios.Constantes;
import Vistas.VistaConfig;
import Vistas.VistaMain;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controladores.ControladorPanelConfig;
import controladores.ControladorPanelEnvios;

public class MenuPrincipal extends javax.swing.JFrame 
{
    private static final long serialVersionUID = 7605330696253689864L;
    private VistaMain vistaPadre;
    private VistaConfig vistaConfig;
    private JPanel activePanel; //este atributo es para saber cual es el panel que se esta mostrando para 
                                //hacer el update de la vista.
    private String defaltXmlPath;
    private String serverIP;
    private static MenuPrincipal instance;
    private File confFile;
    
    private MenuPrincipal(VistaMain m) 
    {
        this.vistaPadre = m;
        initComponents();
        setLookAndFeel();
        isPanel3Selected = true;
        this.setUpRootFile();
        this.setDefaltXmlPath(this.getParam("Xmlroot ", 8).replace("\\\\","\\"));
        this.setServerIP(this.getParam("SRV ", 4));
    }
    
    public static MenuPrincipal getInstance(VistaMain m)
    {
        if(instance == null) instance = new MenuPrincipal(m);
        return instance;
    }
    
    private void setUpRootFile()
    {
        try
        {
            File rootFile = new File(".");
            this.confFile = new File(rootFile.getCanonicalPath() + "/conf/zara.conf");
        }
        catch (IOException ex)
        {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillSDTable(SolicitudDistribucionVO soldis)
    {
    	if(activePanel instanceof PanelSolDist) ((PanelSolDist)activePanel).fillSDTable(soldis);
    }
    
    public void updatePanel()
    {
    	if(activePanel instanceof PanelEnvios) ((PanelEnvios)activePanel).update();
    	if(activePanel instanceof PanelConfig) ((PanelConfig)activePanel).update();
    	if(activePanel instanceof PanelGenSolFab) ((PanelGenSolFab)activePanel).update();
    	if(activePanel instanceof PanelNewArt) ((PanelNewArt)activePanel).update();
    	if(activePanel instanceof PanelRepArt) ((PanelRepArt)activePanel).update();
    	if(activePanel instanceof PanelSolDist) ((PanelSolDist)activePanel).update();
        if(activePanel instanceof PanelDB) ((PanelDB)activePanel).update();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu16 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu17 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu18 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu19 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu20 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu21 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jToolBar2 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar8 = new javax.swing.JMenuBar();
        jMenu22 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu23 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu24 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Constantes.APPLICATION_NAME);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consola", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historial", jPanel2);

/*<<<<<<< .mine
        //jLabel1.setIcon(new javax.swing.ImageIcon(	("../ZaraCentroDeDistribucionCliente/icons/background.jpg"))); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/background.jpg")); // NOI18N
=======*/
        jLabel1.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/background.jpg")); // NOI18N
//>>>>>>/> .r55


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu16.setText("Archivo"); // NOI18N

        jMenu1.setText("Servicios"); // NOI18N

        jMenuItem2.setText("Solicitud de distribucion"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Generar envio a tienda"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Solicitud de fabricacion"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Reposicion articulos de fabrica"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Informacion nuevo articulo"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenu16.add(jMenu1);
        jMenu16.add(jSeparator1);

        jMenuItem7.setText("Salir"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem7);

        jMenuBar6.add(jMenu16);

        jMenu17.setText("Opciones"); // NOI18N

        jMenuItem8.setText("Configuracion"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem8);

        jMenuBar6.add(jMenu17);

        jMenu18.setText("Ayuda"); // NOI18N

        jMenuItem1.setText("Acerca de.."); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem1);

        jMenuBar6.add(jMenu18);

        jMenu19.setText("Archivo"); // NOI18N

        jMenu2.setText("Servicios"); // NOI18N

        jMenuItem9.setText("Solicitud de distribucion"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Generar envio a tienda"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Solicitud de fabricacion"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Reposicion articulos de fabrica"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Informacion nuevo articulo"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenu19.add(jMenu2);
        jMenu19.add(jSeparator2);

        jMenuItem14.setText("Salir"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem14);

        jMenuBar7.add(jMenu19);

        jMenu20.setText("Opciones"); // NOI18N

        jMenuItem15.setText("Configuracion"); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem15);

        jMenuBar7.add(jMenu20);

        jMenu21.setText("Ayuda"); // NOI18N

        jMenuItem16.setText("Acerca de.."); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu21.add(jMenuItem16);

        jMenuBar7.add(jMenu21);

        jToolBar2.setFloatable(false);

        jButton1.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/showchild_mode.gif")); // NOI18N
        jButton1.setText("Sol. Distr.");
        jButton1.setToolTipText("Solicitud de Distribucion");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/forward_nav.gif")); // NOI18N
        jButton2.setText("Env. Tienda");
        jButton2.setToolTipText("Generar envios a Tienda");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/addtsk_tsk.gif")); // NOI18N
        jButton3.setText("Sol. Fabr.");
        jButton3.setToolTipText("Solicitud de Fabricacion");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/import_wiz.gif")); // NOI18N
        jButton4.setText("Rep. Art.");
        jButton4.setToolTipText("Reponer Articulos de Fabrica");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon("../ZaraCentroDeDistribucionCliente/icons/file_obj.gif")); // NOI18N
        jButton5.setText("New Art.");
        jButton5.setToolTipText("Informacion de Nuevo Articulo");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jMenu22.setText("Archivo"); // NOI18N

        jMenu3.setText("Servicios"); // NOI18N

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setText("Solicitud de distribucion"); // NOI18N
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setText("Generar envio a tienda"); // NOI18N
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem18);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setText("Solicitud de fabricacion"); // NOI18N
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setText("Reposicion articulos de fabrica"); // NOI18N
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem20);

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setText("Informacion nuevo articulo"); // NOI18N
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);

        jMenu22.add(jMenu3);

        jMenu4.setText("Base de Datos");

        jMenuItem25.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem25.setText("Base de Datos");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem25);

        jMenu22.add(jMenu4);
        jMenu22.add(jSeparator3);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem22.setText("Salir"); // NOI18N
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem22);

        jMenuBar8.add(jMenu22);

        jMenu23.setText("Opciones"); // NOI18N

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem23.setText("Configuracion"); // NOI18N
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu23.add(jMenuItem23);

        jMenuBar8.add(jMenu23);

        jMenu24.setText("Ayuda"); // NOI18N

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem24.setText("Acerca de.."); // NOI18N
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu24.add(jMenuItem24);

        jMenuBar8.add(jMenu24);

        setJMenuBar(jMenuBar8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ponerPanelPrincipal()
    {
        this.setTitle(Constantes.APPLICATION_NAME + "Menu principal - ");
        
        this.isPanelSDSelected = false;
        this.isPanelGSFSelected = false;
        this.isPanelEnviosSelected = false;
        this.isPanelNewArtSelected = false;
        this.isPanelRepArtSelected = false;
        this.isPanelConfigSelected = false;
        
        if(isPanel3Selected) return;
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        if (this.isAncestorOf(panelSD)) this.remove(panelSD);
        if (this.isAncestorOf(panelGSF)) this.remove(panelGSF);
        if (this.isAncestorOf(panelEnvios)) this.remove(panelEnvios);
        if (this.isAncestorOf(panelNewArt)) this.remove(panelNewArt);
        if (this.isAncestorOf(panelRepArt)) this.remove(panelRepArt);
        if (this.isAncestorOf(panelConfig)) this.remove(panelConfig);
        this.setActivePanel(jPanel3);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        this.isPanel3Selected = true;
    }
  
    private void ponerPanel(JPanel panel)
    {
        if (isPanel3Selected)
        {
            this.remove(jPanel3);
            this.repaint();
        }
        
        if (isPanelGSFSelected)
        {
            this.remove(panelGSF);
            this.repaint();
        }
        
        if (isPanelSDSelected)
        {
            this.remove(panelSD);
            this.repaint();
        }
        
        if (isPanelEnviosSelected)
        {
            this.remove(panelEnvios);
            this.repaint();
        }
        
        if (isPanelRepArtSelected)
        {
            this.remove(panelRepArt);
            this.repaint();
        }
        
        if (isPanelNewArtSelected)
        {
            this.remove(panelNewArt);
            this.repaint();
        }
        
        if (isPanelConfigSelected)
        {
            this.remove(panelConfig);
            this.repaint();
        }
        
        if (isPanelDB)
        {
            this.remove(panelDB);
            this.repaint();
        }
        
        if(panel instanceof PanelEnvios)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelGenSolFab)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelNewArt)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelRepArt)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelSolDist)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelConfig)
        {
            this.isPanel3Selected = false;
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
        
        if(panel instanceof PanelDB)
        {
            this.isPanel3Selected = false;
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
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
               
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       this.CloseApplication();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.CloseApplication();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(this, Constantes.ABOUT_TEXT, Constantes.APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
                
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // solicitud de distribucion
        if(!isPanelSDSelected)
        {
            panelSD = new PanelSolDist(this);
            ponerPanel(panelSD);   
        }
        
        //ponerPanelSolicitudDistribucion();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // generar envio a tienda
        if(!isPanelEnviosSelected)
        {
            panelEnvios = new PanelEnvios(this);
            ponerPanel(panelEnvios);   
        }
        
        //ponerPanelGenerarEnvios();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // solicitud a fabricacion
        if(!isPanelGSFSelected)
        {
            panelGSF = new PanelGenSolFab(this);
            ponerPanel(panelGSF);  
        }
        
        //ponerPanelGenerarSolicitudFabricacion();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // reponer articulos de fabrica
        if(!isPanelRepArtSelected)
        {
            panelRepArt = new PanelRepArt(this);
            ponerPanel(panelRepArt); 
        }
        
        //ponerPanelReponerArticuloFabrica();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // informacion nuevo articulo
        if(!isPanelNewArtSelected)
        {
            panelNewArt = new PanelNewArt(this);
            ponerPanel(panelNewArt);   
        }
        
        //ponerPanelNuevoArticulo();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // panel configuracion
        if(!isPanelConfigSelected)
        {
        	this.vistaConfig = new VistaConfig(this.getVistaPadre().getModelo());
        	ControladorPanelConfig cConf = new ControladorPanelConfig(this.getVistaPadre().getModelo(),this.getVistaConfig());
        	panelConfig = new PanelConfig(this);
            ponerPanel(panelConfig);
        }
        
        //ponerPanelConfiguracion();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Sol. Distribucion
        if(!isPanelSDSelected)
        {
            panelSD = new PanelSolDist(this);
            ponerPanel(panelSD);   
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Envio a Tienda
        if(!isPanelEnviosSelected)
        {
            panelEnvios = new PanelEnvios(this);
            ponerPanel(panelEnvios);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Solicitud fabricacion
        if(!isPanelGSFSelected)
        {
            panelGSF = new PanelGenSolFab(this);
            ponerPanel(panelGSF);  
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Reponer articulo
        if(!isPanelRepArtSelected)
        {
            panelRepArt = new PanelRepArt(this);
            ponerPanel(panelRepArt); 
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Nuevo articulo
        if(!isPanelNewArtSelected)
        {
            panelNewArt = new PanelNewArt(this);
            ponerPanel(panelNewArt);   
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // Panel Base de datos
        if(!isPanelDB)
        {
            panelDB = new PanelDB(this);
            ponerPanel(panelDB);   
        }
    }//GEN-LAST:event_jMenuItem25ActionPerformed
    
    
    private void CloseApplication()
    {
        if(JOptionPane.showConfirmDialog(this, "Esta seguro que desea cerrar la aplicacion?", Constantes.APPLICATION_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)        
            this.dispose();
    }            
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
 
    private PanelSolDist panelSD;
    private PanelGenSolFab panelGSF;
    private PanelEnvios panelEnvios;
    private PanelNewArt panelNewArt;
    private PanelRepArt panelRepArt;
    private PanelConfig panelConfig;
    private PanelDB panelDB;
    
    private boolean isPanelConfigSelected;
    private boolean isPanelSDSelected;
    private boolean isPanel3Selected;
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

    public JPanel getActivePanel()
    {
        return activePanel;
    }

    public void setActivePanel(JPanel activePanel)
    {
        this.activePanel = activePanel;
    }

    public VistaMain getVistaPadre()
    {
        return vistaPadre;
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
}
