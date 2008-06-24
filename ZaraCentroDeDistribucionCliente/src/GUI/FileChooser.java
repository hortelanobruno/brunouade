/*
 * FileChooser.java
 *
 * Created on 24 de mayo de 2008, 15:45
 */

package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.UnsupportedLookAndFeelException;

import Varios.XMLFilter;

/**
 * 
 * @author Administrador
 */
public class FileChooser extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;

	/** Creates new form FileChooser */
	public String path;

	public String file;

	public String button;

	public FileChooser(java.awt.Frame parent, boolean modal, String XmlPath) {
		super(parent, modal);
		XmlPath = XmlPath + "\\";
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		}
		initComponents();

		fileChooser.addChoosableFileFilter(new XMLFilter());
		path = "";
		button = "";
		fileChooser.setDialogTitle("Elija un archivo XML");
		fileChooser.setCurrentDirectory(new File(XmlPath));
		this.setVisible(true);
	}

	public String getButton() {
		return button;
	}

	public String getPath() {
		return path;
	}

	public String getFile() {
		return file;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		fileChooser = new javax.swing.JFileChooser();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

		fileChooser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fileChooserActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(fileChooser,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(fileChooser,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>

	private void closeWindow() {
		button = "Cancel";
	}
	
	private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getActionCommand().equals("ApproveSelection")) {
			path = fileChooser.getSelectedFile().getPath()
					.replace("\\", "\\\\");
			file = fileChooser.getSelectedFile().getName();
			button = "Aceptar";
		} else if (evt.getActionCommand().equals("CancelSelection")) {
			path = "";
			file = "";
			button = "Cancel";
		}else {
			path = "";
			file = "";
			button = "";
		}
		this.setVisible(false);
	}

	// Variables declaration - do not modify
	private javax.swing.JFileChooser fileChooser;
	// End of variables declaration

}
