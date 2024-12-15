/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simakademik;

/**
 *
 * @author ALFARIS
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
//import com.jakwanaf.SimMahasiswa;

public class Login extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500); // Ukuran frame diubah menjadi lebih kompak
        setResizable(true); // Memungkinkan resizing window
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(50, 50, 50)); // Warna latar belakang diubah

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(255, 203, 5)); // Warna teks diubah
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Font dan ukuran teks diubah
        lblNewLabel.setBounds(310, 30, 180, 80); // Posisi dan ukuran label diubah
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setBounds(260, 150, 280, 50);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        passwordField.setBounds(260, 220, 280, 50);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(new Color(255, 255, 255)); // Warna teks diubah
        lblUsername.setFont(new Font("Arial", Font.BOLD, 24)); // Font dan ukuran teks diubah
        lblUsername.setBounds(130, 150, 130, 50);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(new Color(255, 255, 255)); // Warna teks diubah
        lblPassword.setFont(new Font("Arial", Font.BOLD, 24)); // Font dan ukuran teks diubah
        lblPassword.setBounds(130, 220, 130, 50);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 22));
        btnNewButton.setBounds(310, 300, 180, 50);
        btnNewButton.setBackground(new Color(70, 130, 180)); // Warna tombol diubah
        btnNewButton.setForeground(Color.WHITE); // Warna teks tombol diubah
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = String.valueOf(password);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sim_akademik",
                            "root", "");

                    PreparedStatement st = connection.prepareStatement("select name from user where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, passwordString);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        String name = rs.getString("name");
                        JOptionPane.showMessageDialog(btnNewButton, "Login sukses!");

                        // Membuka form SimMahasiswa
                        //DataMahasiswa dataMahasiswa = new DataMahasiswa(name);
                        //dataMahasiswa.setVisible(true);

                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Login gagal. Username atau password salah");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 794, 494);
        contentPane.add(label);
    }
}

