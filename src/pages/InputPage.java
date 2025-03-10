package pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class InputPage extends JFrame implements ActionListener {
    private JTextField dnama, bnama;
    private JTextArea text;
    private JButton btnSave, btnConvert;
    private JRadioButton rbLaki, rbPerempuan;
    private JLabel lblWarning, lblErrorConvert;
    private ButtonGroup genderGroup;
    private ArrayList<String> dataList;

    public InputPage() {
        setTitle("Halaman Input Data");
        setLayout(new BorderLayout(10, 10));

        dataList = new ArrayList<>();
 
        JPanel panelNama = new JPanel(new GridLayout(2, 2, 5, 5));
        panelNama.add(new JLabel("Nama Depan:"));
        panelNama.add(new JLabel("Nama Belakang:"));
        dnama = new JTextField(15);
        panelNama.add(dnama);
        bnama = new JTextField(15);
        panelNama.add(bnama);

        JPanel panelGender = new JPanel();
        panelGender.setLayout(new BoxLayout(panelGender, BoxLayout.Y_AXIS));  
        
        JPanel panellblGender = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        JLabel lblGender = new JLabel("Jenis Kelamin:");  
        panellblGender.add(lblGender);  

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.CENTER));  
        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbLaki);
        genderGroup.add(rbPerempuan);

        panelRadio.add(rbLaki);
        panelRadio.add(rbPerempuan);
        panelGender.add(panellblGender);  
        panelGender.add(panelRadio); 
        
        JPanel panelButtonSave = new JPanel();
        btnSave = new JButton("Simpan");
        btnSave.addActionListener(this);
        panelButtonSave.add(btnSave);
 
        lblWarning = new JLabel("");
 
        text = new JTextArea(6, 10);
        text.setEditable(false);
        text.setLineWrap(false);  
        text.setWrapStyleWord(false);

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 
        lblErrorConvert = new JLabel("");
        lblErrorConvert.setVisible(true);
 
        JPanel panelButtonConvert = new JPanel(new BorderLayout());
        btnConvert = new JButton("Convert to .txt File");
        btnConvert.addActionListener(this);
        panelButtonConvert.add(btnConvert, BorderLayout.NORTH);
        panelButtonConvert.add(lblErrorConvert, BorderLayout.SOUTH);
 
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(panelGender, BorderLayout.NORTH);
        panelCenter.add(panelButtonSave, BorderLayout.CENTER);
        panelCenter.add(lblWarning, BorderLayout.SOUTH);
 
        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(scrollPane, BorderLayout.CENTER);
        panelBottom.add(panelButtonConvert, BorderLayout.SOUTH);
 
        add(panelNama, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
 
        setSize(400, 400); 
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            if (dnama.getText().isEmpty() || bnama.getText().isEmpty() || (!rbLaki.isSelected() && !rbPerempuan.isSelected())) {
                lblWarning.setText("Data Harus Lengkap!");
            } else {
                lblWarning.setText(" ");
                lblErrorConvert.setText(" ");

                String gender = rbLaki.isSelected() ? "Laki-laki" : "Perempuan";
                String data = dnama.getText() + " " + bnama.getText() + " | Gender: " + gender;

                dataList.add(data);
                updateTextArea();
 
                dnama.setText("");
                bnama.setText("");
                genderGroup.clearSelection();
            }
        } else if (e.getSource() == btnConvert) {
            if (dataList.isEmpty()) {
                lblErrorConvert.setText("Tidak ada data.");
            } else {
                lblErrorConvert.setText(" ");
                try (FileWriter writer = new FileWriter("data_nim.txt")) {
                    for (String entry : dataList) {
                        writer.write(entry + "\n");
                    }
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan file", "Hasil", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
    }

    private void updateTextArea() {
        text.setText("");
        for (String entry : dataList) {
            text.append(entry + "\n");
        }
    }
}
