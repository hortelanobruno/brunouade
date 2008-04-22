/*
 * NewJDialog.java
 *
 * Created on March 31, 2008, 3:03 PM
 */

package GUI;

import java.io.File;
import javax.swing.UnsupportedLookAndFeelException;

public class FileChooser extends javax.swing.JDialog 
{
	private static final long serialVersionUID = 5644209101189512949L;
	public String path;
    public String file;
   
    public FileChooser(java.awt.Frame parent, boolean modal, String XmlPath) 
    {
        super(parent, modal);
        XmlPath = XmlPath + "\\";
      //  System.out.println("222222222");
        try 
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException ex) 
        {
            ex.printStackTrace();
        } 
        catch (IllegalAccessException ex) 
        {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        catch (InstantiationException ex) 
        {
            ex.printStackTrace();
        }
        initComponents();
        
        jFileChooser1.addChoosableFileFilter(new XMLFilter());
        path="";
        //System.out.println(XmlPath);
        /*System.out.println(jFileChooser1.getCurrentDirectory() + "/Java/TP/ZaraCentroDeDistribucionCliente/src/xml/");
        System.out.println(jFileChooser1.getCurrentDirectory());*/
        jFileChooser1.setDialogTitle("Elija un archivo XML");
        jFileChooser1.setCurrentDirectory(new File(XmlPath));
        this.setVisible(true);
    }
    
    
    public String getPath()
    {
        return path;
    }
    
    public String getFile()
    {
        return file;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionButton(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActionButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionButton
        // TODO add your handling code here:
        //System.out.println("1111");
        
        if(evt.getActionCommand().equals("ApproveSelection"))
        {
            path = jFileChooser1.getSelectedFile().getPath().replace("\\", "\\\\");
            file = jFileChooser1.getSelectedFile().getName();
        }
        else
        {
            path="";
            file="";
        }   
        this.setVisible(false); 
}//GEN-LAST:event_ActionButton
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
    
}
