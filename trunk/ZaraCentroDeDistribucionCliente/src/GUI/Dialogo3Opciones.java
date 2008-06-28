/*
 * Dialogo3Botones.java
 *
 * Created on 24 de junio de 2008, 13:00
 */

package GUI;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;

import Paneles.PanelEnvios;
import Paneles.PanelGenSolFab;
import Paneles.PanelNewArt;
import Paneles.PanelRepArt;
import Paneles.PanelSolDist;
import Varios.Constantes;

/**
 * 
 * @author Administrador
 */
public class Dialogo3Opciones extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7105787642354610795L;

	private JPanel ref;

	
	/** Creates new form Dialogo3Botones */
	public Dialogo3Opciones(String mensaje, JPanel ref, String msj) {
		super();
		this.ref = ref;
		initComponents(mensaje);
		this.jTextArea1.append(msj);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents(String m) {

		btnPrinc = new javax.swing.JButton();
		btnStay = new javax.swing.JButton();
		btnDetails = new javax.swing.JToggleButton();
		jLabel1 = new javax.swing.JLabel(m);
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		this.setTitle(Constantes.APPLICATION_NAME);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		Toolkit t = Toolkit.getDefaultToolkit();
		this.setLocation((int) (t.getScreenSize().getWidth() - this.getWidth()) / 2, 
						 (int) (t.getScreenSize().getHeight() - this.getHeight()) / 2);
		btnPrinc.setText("Menu Principal");
		btnPrinc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPrincActionPerformed(evt);
			}
		});

		btnStay.setText("Quedarse Aqui");
		btnStay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStayActionPerformed(evt);
			}
		});

		btnDetails.setText("Ver Detalle");
		btnDetails.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDetailsActionPerformed(evt);
			}
		});

		jLabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
		jLabel1.setText("Operacion concretada");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 483,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 23,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				46,
																				46,
																				46)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								jLabel1)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												btnPrinc)
																										.add(
																												33,
																												33,
																												33)
																										.add(
																												btnStay)))
																		.add(
																				56,
																				56,
																				56)
																		.add(
																				btnDetails,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				99,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.add(42, 42, 42)
										.add(jLabel1)
										.add(60, 60, 60)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																btnPrinc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnStay,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																39,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnDetails,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(18, 18, 18)
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												0,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {
		if (btnDetails.isSelected()) {
			initComponent2();
		} else {
			initComponent3();
		}
	}

	private void initComponent2() {
		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 483,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 116,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				46,
																				46,
																				46)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								jLabel1)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												btnPrinc)
																										.add(
																												33,
																												33,
																												33)
																										.add(
																												btnStay)))
																		.add(
																				56,
																				56,
																				56)
																		.add(
																				btnDetails,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				99,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.add(42, 42, 42)
										.add(jLabel1)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												60, Short.MAX_VALUE)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																btnPrinc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnStay,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																39,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnDetails,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(18, 18, 18)
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		pack();
	}

	private void initComponent3() {
		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 483,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 23,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				46,
																				46,
																				46)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								jLabel1)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												btnPrinc)
																										.add(
																												33,
																												33,
																												33)
																										.add(
																												btnStay)))
																		.add(
																				56,
																				56,
																				56)
																		.add(
																				btnDetails,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				99,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.add(42, 42, 42)
										.add(jLabel1)
										.add(60, 60, 60)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																btnPrinc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnStay,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																39,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																btnDetails,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																38,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(18, 18, 18)
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												0,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}

	private void btnStayActionPerformed(java.awt.event.ActionEvent evt) {
		if (ref instanceof PanelEnvios) {
			((PanelEnvios) ref).getRef().ponerPanel(ref);
		}
		if (ref instanceof PanelGenSolFab) {
			((PanelGenSolFab) ref).getRef().ponerPanel(ref);
		}
		if (ref instanceof PanelNewArt) {
			((PanelNewArt) ref).getRef().ponerPanel(ref);
		}
		if (ref instanceof PanelRepArt) {
			((PanelRepArt) ref).getRef().ponerPanel(ref);
		}
		if (ref instanceof PanelSolDist) {
			((PanelSolDist) ref).getRef().ponerPanel(ref);
		}
		dispose();
	}

	private void btnPrincActionPerformed(java.awt.event.ActionEvent evt) {
		if (ref instanceof PanelEnvios) {
			((PanelEnvios) ref).getRef().ponerPanelPrincipal();
		}
		if (ref instanceof PanelGenSolFab) {
			((PanelGenSolFab) ref).getRef().ponerPanelPrincipal();
		}
		if (ref instanceof PanelNewArt) {
			((PanelNewArt) ref).getRef().ponerPanelPrincipal();
		}
		if (ref instanceof PanelRepArt) {
			((PanelRepArt) ref).getRef().ponerPanelPrincipal();
		}
		if (ref instanceof PanelSolDist) {
			((PanelSolDist) ref).getRef().ponerPanelPrincipal();
		}
		dispose();
	}

	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnDetails;

	private javax.swing.JButton btnPrinc;

	private javax.swing.JButton btnStay;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration

}
