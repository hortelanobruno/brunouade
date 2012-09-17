/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli;

import com.brunoli.view.components.PanelARTTool;
import com.brunoli.view.components.PanelGBSTool;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author brunoli
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        addWindowListener(new AreYouSure());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lblMessage = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miExit = new javax.swing.JMenuItem();
        miTools = new javax.swing.JMenu();
        miARTTool = new javax.swing.JMenuItem();
        miGBSTool = new javax.swing.JMenuItem();
        miHelp = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.add(lblMessage);

        panelPrincipal.setLayout(new java.awt.GridLayout(0, 1));

        jMenu1.setText("File");

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        miTools.setText("Tools");

        miARTTool.setText("ART Tool");
        miARTTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miARTToolActionPerformed(evt);
            }
        });
        miTools.add(miARTTool);

        miGBSTool.setText("GBS Tool");
        miGBSTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGBSToolActionPerformed(evt);
            }
        });
        miTools.add(miGBSTool);

        jMenuBar1.add(miTools);

        miHelp.setText("Help");

        miAbout.setText("About");
        miHelp.add(miAbout);

        jMenuBar1.add(miHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miARTToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miARTToolActionPerformed
        cargarPanelARTTool();
    }//GEN-LAST:event_miARTToolActionPerformed

    private void miGBSToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGBSToolActionPerformed
        cargarPanelGBSTool();
    }//GEN-LAST:event_miGBSToolActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        exit();
    }//GEN-LAST:event_miExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JMenuItem miARTTool;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miGBSTool;
    private javax.swing.JMenu miHelp;
    private javax.swing.JMenu miTools;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
    private PanelARTTool panelARTTool;
    private PanelGBSTool panelGBSTool;

    private void cargarPanelARTTool() {
        panelPrincipal.removeAll();
        if (panelARTTool == null) {
            panelARTTool = new PanelARTTool(this);
        }
        panelPrincipal.add(panelARTTool);
        panelPrincipal.updateUI();
    }

    private void cargarPanelGBSTool() {
        panelPrincipal.removeAll();
        if (panelGBSTool == null) {
            panelGBSTool = new PanelGBSTool(this);
        }
        panelPrincipal.add(panelGBSTool);
        panelPrincipal.updateUI();
    }

    private void exit() {
        if (panelARTTool != null) {
            panelARTTool.saveData();
        }
        if (panelGBSTool != null) {
            panelGBSTool.saveData();
        }
        System.exit(0);
    }

    public JLabel getConsole() {
        return lblMessage;
    }

    private class AreYouSure extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
//            int option = JOptionPane.showOptionDialog(  
//                    MainView.this,  
//                    "Are you sure you want to quit?",  
//                    "Exit Dialog", JOptionPane.YES_NO_OPTION,  
//                    JOptionPane.WARNING_MESSAGE, null, null,  
//                    null );  
//            if( option == JOptionPane.YES_OPTION ) {  
//                System.exit( 0 );  
//            }  
            exit();
        }
    }
}
