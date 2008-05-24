package ViejoJRE16;

import javax.swing.JFileChooser;

public class DirectoryChooser extends javax.swing.JDialog 
{
	private static final long serialVersionUID = 4548597435807669182L;
	private String path;
    
    public DirectoryChooser(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        jFileChooser1.setDialogTitle("Elija la carpeta");
        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.setAcceptAllFileFilterUsed(false);
        this.setVisible(true);
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
     /*
            if (evt.getActionCommand().equals("ApproveSelection")) path = jFileChooser1.getCurrentDirectory().getCanonicalPath(); //getSelectedFile().getPath().replace("\\", "\\\\");//GEN-LAST:event_jFileChooser1ActionPerformed
        */
       
        if (evt.getActionCommand().equals("ApproveSelection")) path = jFileChooser1.getSelectedFile().getPath(); //getSelectedFile().getPath().replace("\\", "\\\\");                                             
        else path = "";

        this.dispose();
       
    }                                             
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
    
}
