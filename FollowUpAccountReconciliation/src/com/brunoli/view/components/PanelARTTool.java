/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.view.components;

import com.brunoli.MainView;
import com.brunoli.core.art.ARTCore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author brunoli
 */
public class PanelARTTool extends javax.swing.JPanel {

    private String selectedDatabaseFileName;
    private String selectedNamesFileName;
    private String selectedOutputFileName;
    private String abcd = "abcdefghijklmnopqrstuvwxyz";
    private final String artConfigFile = "./art.properties";
    private MainView main;
    private ARTCore artCore = new ARTCore();
    private JFileChooser chooser;

    /**
     * Creates new form PanelARTTool
     */
    public PanelARTTool(MainView main) {
        initComponents();
        this.main = main;
        this.lblLoading.setVisible(false);
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        
        chooser.setFileFilter(new FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx")
                        || f.isDirectory();
            }

            public String getDescription() {
                return "XLS/XLSX Files";
            }
        });
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDatabaseFileName = new javax.swing.JLabel();
        buttonSelectFileDatabase = new javax.swing.JButton();
        buttonSelectFileName = new javax.swing.JButton();
        lblNamesFilesName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonSelectOutputFile = new javax.swing.JButton();
        lblOutputFileName = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        buttonRun = new javax.swing.JButton();
        lblLoading = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tfColumnReconciler = new javax.swing.JTextField();
        tfColumnReconStatus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfColumnLocalME = new javax.swing.JTextField();
        tfDatabasePageNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        tfColumnMEName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfNamesPageNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfColumnLocalME2 = new javax.swing.JTextField();
        tfColumnReconciler2 = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Input Settings"));

        jLabel1.setText("Database File:");

        lblDatabaseFileName.setText("No File Selected");

        buttonSelectFileDatabase.setText("Select file");
        buttonSelectFileDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFileDatabaseActionPerformed(evt);
            }
        });

        buttonSelectFileName.setText("Select file");
        buttonSelectFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFileNameActionPerformed(evt);
            }
        });

        lblNamesFilesName.setText("No File Selected");

        jLabel4.setText("Names File:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDatabaseFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSelectFileDatabase))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNamesFilesName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSelectFileName)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDatabaseFileName)
                    .addComponent(buttonSelectFileDatabase))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNamesFilesName)
                    .addComponent(buttonSelectFileName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Output Settings"));

        buttonSelectOutputFile.setText("Select file");
        buttonSelectOutputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectOutputFileActionPerformed(evt);
            }
        });

        lblOutputFileName.setText("No File Selected");

        jLabel9.setText("Output File:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblOutputFileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(buttonSelectOutputFile)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblOutputFileName)
                    .addComponent(buttonSelectOutputFile))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Execute"));

        buttonRun.setText("Run");
        buttonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRun, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRun, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 311, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Database File"));

        jLabel10.setText("Page Number:");

        jLabel5.setText("Column Reconciler:");

        jLabel2.setText("Column Recon Status:");

        jLabel3.setText("Column Local ME:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addGap(93, 93, 93)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfColumnReconStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfColumnLocalME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfColumnReconciler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDatabasePageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfColumnLocalME, tfColumnReconStatus, tfColumnReconciler, tfDatabasePageNumber});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(tfColumnReconStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfColumnLocalME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfColumnReconciler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfDatabasePageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Names File"));

        jLabel7.setText("Column Local ME:");

        jLabel11.setText("Page Number:");

        jLabel8.setText("Column ME Name:");

        jLabel6.setText("Column Reconciler:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(109, 109, 109)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfColumnLocalME2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfColumnMEName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNamesPageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfColumnReconciler2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfColumnLocalME2, tfColumnMEName, tfColumnReconciler2, tfNamesPageNumber});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfColumnReconciler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfColumnLocalME2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfColumnMEName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfNamesPageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(409, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Advance", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSelectFileDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFileDatabaseActionPerformed
        if (selectedDatabaseFileName != null) {
            chooser.setSelectedFile(new File(selectedDatabaseFileName));
        }
        int r = chooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            String zipname = chooser.getSelectedFile().getPath();
            selectedDatabaseFileName = zipname;
            this.lblDatabaseFileName.setText(zipname);
            System.out.println(zipname);
        }
    }//GEN-LAST:event_buttonSelectFileDatabaseActionPerformed

    private void buttonSelectFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFileNameActionPerformed
//        JFileChooser chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new File("."));
        if (selectedNamesFileName != null) {
            chooser.setSelectedFile(new File(selectedNamesFileName));
        }
//        chooser.setFileFilter(new FileFilter() {
//
//            public boolean accept(File f) {
//                return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx")
//                        || f.isDirectory();
//            }
//
//            public String getDescription() {
//                return "XLS/XLSX Files";
//            }
//        });
        int r = chooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            String zipname = chooser.getSelectedFile().getPath();
            selectedNamesFileName = zipname;
            this.lblNamesFilesName.setText(zipname);
            System.out.println(zipname);
        }
    }//GEN-LAST:event_buttonSelectFileNameActionPerformed

    private void buttonSelectOutputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectOutputFileActionPerformed
//        JFileChooser chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new File("."));
        if (selectedOutputFileName != null) {
            chooser.setSelectedFile(new File(selectedOutputFileName));
        }
//        chooser.setFileFilter(new FileFilter() {
//
//            public boolean accept(File f) {
//                return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx")
//                        || f.isDirectory();
//            }
//
//            public String getDescription() {
//                return "XLS/XLSX Files";
//            }
//        });
        int r = chooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            String zipname = chooser.getSelectedFile().getPath();
            selectedOutputFileName = zipname;
            this.lblOutputFileName.setText(zipname);
            System.out.println(zipname);
        }
    }//GEN-LAST:event_buttonSelectOutputFileActionPerformed

    private void buttonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRunActionPerformed
        if (validateData()) {
            try {
                this.lblLoading.setVisible(true);
                Integer dfColReconciler = getCode(tfColumnReconciler.getText());
                Integer dfColReconStatus = getCode(tfColumnReconStatus.getText());
                Integer dfColLocalME = getCode(tfColumnLocalME.getText());
                Integer nfColLocalME = getCode(tfColumnLocalME2.getText());
                Integer nfColReconciler = getCode(tfColumnReconciler2.getText());
                Integer nfColMEName = getCode(tfColumnMEName.getText());
                Integer nfPageNumber = Integer.valueOf(tfNamesPageNumber.getText()) - 1;
                Integer dfPageNumber = Integer.valueOf(tfDatabasePageNumber.getText()) - 1;
                artCore.generateOutput(selectedDatabaseFileName, selectedNamesFileName, selectedOutputFileName, dfColReconciler, dfColReconStatus, dfColLocalME, nfColLocalME, nfColReconciler, nfColMEName,
                        dfPageNumber, nfPageNumber);
                main.getConsole().setText("The file was generated sucessfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
                main.getConsole().setText("Error.");
            }
            this.lblLoading.setVisible(false);
        }
    }//GEN-LAST:event_buttonRunActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRun;
    private javax.swing.JButton buttonSelectFileDatabase;
    private javax.swing.JButton buttonSelectFileName;
    private javax.swing.JButton buttonSelectOutputFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDatabaseFileName;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblNamesFilesName;
    private javax.swing.JLabel lblOutputFileName;
    private javax.swing.JTextField tfColumnLocalME;
    private javax.swing.JTextField tfColumnLocalME2;
    private javax.swing.JTextField tfColumnMEName;
    private javax.swing.JTextField tfColumnReconStatus;
    private javax.swing.JTextField tfColumnReconciler;
    private javax.swing.JTextField tfColumnReconciler2;
    private javax.swing.JTextField tfDatabasePageNumber;
    private javax.swing.JTextField tfNamesPageNumber;
    // End of variables declaration//GEN-END:variables

    private boolean validateData() {
        if (selectedDatabaseFileName != null && selectedNamesFileName != null && selectedOutputFileName != null
                && tfColumnLocalME.getText() != null && tfColumnLocalME2.getText() != null && tfColumnMEName.getText() != null
                && tfColumnReconStatus.getText() != null && tfColumnReconciler.getText() != null && tfColumnReconciler2.getText() != null
                && tfDatabasePageNumber.getText() != null && tfNamesPageNumber.getText() != null) {
            try {
                Integer.valueOf(tfDatabasePageNumber.getText());
                Integer.valueOf(tfNamesPageNumber.getText());
                getCode(tfColumnReconciler.getText());
                getCode(tfColumnReconStatus.getText());
                getCode(tfColumnLocalME.getText());
                getCode(tfColumnLocalME2.getText());
                getCode(tfColumnReconciler2.getText());
                getCode(tfColumnMEName.getText());
            } catch (Exception ex) {
                main.getConsole().setText("Invalid data.");
                return false;
            }
            return true;
        } else {
            main.getConsole().setText("Invalid data.");
            return false;
        }
    }

    private Integer getCode(String text) throws Exception {
        if (text != null && !text.trim().isEmpty()) {
            int count = text.toCharArray().length;
            char c;
            Integer result = 0;
            for (int i = 0; i < count; i++) {
                c = text.toCharArray()[i];
                result += (abcd.indexOf("" + c) + (i * 26));
            }
            return result;
        } else {
            throw new Exception("");
        }
    }

    private void loadData() {
        Properties prop = new Properties();

        try {
            //load a properties file
            prop.load(new FileInputStream(artConfigFile));

            selectedDatabaseFileName = prop.getProperty("selectedDatabaseFileName");
            lblDatabaseFileName.setText(selectedDatabaseFileName);
            selectedNamesFileName = prop.getProperty("selectedNamesFileName");
            lblNamesFilesName.setText(selectedNamesFileName);
            selectedOutputFileName = prop.getProperty("selectedOutputFileName");
            lblOutputFileName.setText(selectedOutputFileName);

            tfColumnLocalME.setText(prop.getProperty("tfColumnLocalME"));
            tfColumnLocalME2.setText(prop.getProperty("tfColumnLocalME2"));
            tfColumnMEName.setText(prop.getProperty("tfColumnMEName"));
            tfColumnReconStatus.setText(prop.getProperty("tfColumnReconStatus"));
            tfColumnReconciler.setText(prop.getProperty("tfColumnReconciler"));
            tfColumnReconciler2.setText(prop.getProperty("tfColumnReconciler2"));
            tfDatabasePageNumber.setText(prop.getProperty("tfDatabasePageNumber"));
            tfNamesPageNumber.setText(prop.getProperty("tfNamesPageNumber"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveData() {
        Properties prop = new Properties();

        try {
            prop.setProperty("selectedDatabaseFileName", selectedDatabaseFileName);
            prop.setProperty("selectedNamesFileName", selectedNamesFileName);
            prop.setProperty("selectedOutputFileName", selectedOutputFileName);

            prop.setProperty("tfColumnLocalME", tfColumnLocalME.getText());
            prop.setProperty("tfColumnLocalME2", tfColumnLocalME2.getText());
            prop.setProperty("tfColumnMEName", tfColumnMEName.getText());
            prop.setProperty("tfColumnReconStatus", tfColumnReconStatus.getText());
            prop.setProperty("tfColumnReconciler", tfColumnReconciler.getText());
            prop.setProperty("tfColumnReconciler2", tfColumnReconciler2.getText());
            prop.setProperty("tfDatabasePageNumber", tfDatabasePageNumber.getText());
            prop.setProperty("tfNamesPageNumber", tfNamesPageNumber.getText());

            prop.store(new FileOutputStream(artConfigFile), null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
