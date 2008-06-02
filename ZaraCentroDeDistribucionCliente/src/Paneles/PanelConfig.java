/*
 * PanelConfig.java
 *
 * Created on 22 de mayo de 2008, 22:18
 */

package Paneles;

import javax.swing.JOptionPane;

import controladores.ControladorPanelConfig;
import controladores.ControladorPanelNewArt;

import Varios.Constantes;
import Vistas.VistaConfig;

import GUI.DirectoryChooser;
import GUI.MenuPrincipal;

/**
 * 
 * @author Administrador
 */
public class PanelConfig extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private MenuPrincipal ref;

	private VistaConfig vistaConfig;

	/** Creates new form PanelConfig */
	public PanelConfig(MenuPrincipal menu, VistaConfig vista) {
		this.ref = menu;
		this.vistaConfig = vista;
		initComponents();
		this.textFieldDirectorioXML.setText(ref.getDefaltXmlPath());
		this.textFieldServidorIP.setText(ref.getServerIP());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textFieldDirectorioXML = new javax.swing.JTextField();
        textFieldServidorIP = new javax.swing.JTextField();
        buttonSeleccionarDirectorio = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();

        jLabel1.setText("Configuracion");

        jLabel2.setText("Directorio de archivos XML");

        jLabel3.setText("IP del servidor");

        buttonSeleccionarDirectorio.setText("Seleccionar Directorio");
        buttonSeleccionarDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeleccionarDirectorioActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(buttonGuardar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 525, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(jLabel3))
                            .add(121, 121, 121)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(textFieldServidorIP)
                                .add(textFieldDirectorioXML, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                            .add(buttonSeleccionarDirectorio))
                        .add(layout.createSequentialGroup()
                            .add(jLabel1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 530, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(416, 416, 416))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(39, 39, 39)
                .add(jLabel1)
                .add(59, 59, 59)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(textFieldDirectorioXML, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(buttonSeleccionarDirectorio))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(textFieldServidorIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(75, 75, 75)
                .add(buttonGuardar)
                .addContainerGap(191, Short.MAX_VALUE))
        );
	}// </editor-fold>

	private void buttonSeleccionarDirectorioActionPerformed(
			java.awt.event.ActionEvent evt) {
		// Accion Boton SeleccionarDirectorio XML
		DirectoryChooser c = new DirectoryChooser(ref, true);
		String folder = c.getPath();
		textFieldDirectorioXML.setText(folder);
	}

	private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		// Accion Boton Guardar
		
		String ret = ((ControladorPanelConfig) vistaConfig.getControlador()).doSaveConfigFile(textFieldDirectorioXML.getText(), textFieldServidorIP.getText());
		// String ret = this.controlador.doSaveConfigFile(jTextField1.getText(),
		// jTextField2.getText());

		if (ret.equalsIgnoreCase("Archivo grabado")) {
			JOptionPane.showMessageDialog(this, ret,
					Constantes.APPLICATION_NAME,
					JOptionPane.INFORMATION_MESSAGE);
			ref.setDefaltXmlPath(textFieldDirectorioXML.getText());
			ref.setServerIP(textFieldServidorIP.getText());
		} else
			JOptionPane.showMessageDialog(this, ret,
					Constantes.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);

	}

	public void update() {

	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonGuardar;

	private javax.swing.JButton buttonSeleccionarDirectorio;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JTextField textFieldDirectorioXML;

	private javax.swing.JTextField textFieldServidorIP;

	// End of variables declaration
	public VistaConfig getVistaConfig() {
		return vistaConfig;
	}

	public void setVistaConfig(VistaConfig vistaConfig) {
		this.vistaConfig = vistaConfig;
	}

}
