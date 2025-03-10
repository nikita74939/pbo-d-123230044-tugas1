package pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener{
    final JTextField fnama = new JTextField(15);
    final JTextField fpass = new JTextField(15);
    private JLabel lbl;
    private JButton btnLogin;
    
    JLabel lnama = new JLabel("Username:");
    JLabel lpass = new JLabel("Password:");
    
    public LoginPage(){
        setTitle("Halaman Login");
        
        lbl = new JLabel("", SwingConstants.CENTER); 
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 8, 8));
        panelForm.add(lnama);
        panelForm.add(fnama);
        panelForm.add(lpass);
        panelForm.add(fpass);
        
        JPanel panelButton = new JPanel();
        panelButton.add(btnLogin);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(lbl, BorderLayout.CENTER);
        panelBottom.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH); 

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(panelForm, BorderLayout.NORTH);
        wrapper.add(panelButton, BorderLayout.CENTER);
        wrapper.add(panelBottom, BorderLayout.SOUTH);
        
        add(wrapper);
        
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = fnama.getText();
        String password = fpass.getText();

        if ((username.equals("pbo") || username.equals("PBO")) &&  (password.equals("if-d") || password.equals("IF-D"))) {
            new InputPage();
            dispose();
            
        } else {
            lbl.setText("Gagal Login");
        }
    }
}
