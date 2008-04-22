package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dialogo3Opciones extends JDialog
{
    private JButton btnPrinc;
    private JButton btnStay;
    private JButton btnDetails;
    private JLabel lblMsg;
    private MenuPrincipal ref;
    /**
     * 
     * @param mensaje
     * @param ref
     */
    public Dialogo3Opciones(String mensaje,MenuPrincipal ref)
    {
        super();
        this.ref = ref;
        initComponents(mensaje);
    }
    
    private void initComponents(String msg)
    {
        this.setTitle("Seleccione una opcion");
        this.lblMsg = new JLabel(msg);
        this.btnPrinc = new JButton("Menu Principal");
        this.btnDetails = new JButton("Ver detalles");
        this.btnStay = new JButton("Quedarse aqui");
        this.lblMsg.setFont(new Font("Verdana",Font.PLAIN,12));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(450,100);
        Toolkit t = Toolkit.getDefaultToolkit();
        this.setLocation((int) (t.getScreenSize().getWidth()  - this.getWidth()) / 2, 
                         (int) (t.getScreenSize().getHeight() - this.getHeight())/ 2);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));
        
        panel.add(new JLabel(""));
        panel.add(lblMsg);
        panel.add(new JLabel(""));
        panel.add(btnPrinc);
        panel.add(btnStay);
        panel.add(btnDetails);
        
        btnStay.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        
        btnPrinc.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                ref.ponerPanelPrincipal();
                dispose();
            }
        });
        
        this.add(panel); 
    }
}
