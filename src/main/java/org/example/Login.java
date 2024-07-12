package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    public JPanel MainPanel;
    public JTextField usuario;
    public JTextField contrasenia;
    public JPanel iconUser;
    private JButton iniciarSesionButton;
    private JButton cancelarButton;
    private JLabel estado;
    private JLabel alerta;

    String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user="root";
    String passw="123456";


    public login() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.setText(null);
                contrasenia.setText(null);
                estado.setText(null);
            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection connection= DriverManager.getConnection(url,user,passw)){
                    String pass=null;
                    String query = "select * from usuario where username='"+usuario.getText()+"'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()){
                        System.out.println(resultSet.getString("username"));
                        System.out.println(resultSet.getString("password"));
                        user=resultSet.getString("username");
                        pass=resultSet.getString("password");
                    }



                if (usuario.getText().equals(user)){
                    if (contrasenia.getText().equals(pass)){
                        System.out.println("Ingreso Exitoso");
                        estado.setText("Ingresando...");

                        JFrame frame = new JFrame();
                        frame.setContentPane(new Register().MainPanel);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(550,520);
                        frame.setVisible(true);
                        ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                    }else {
                        estado.setText("Contraseña Incorrecta");
                        JFrame frame = new JFrame();
                        frame.setContentPane(new login().MainPanel);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(500,450);
                        frame.setVisible(true);

                        ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                    }
                }else {
                    estado.setText("Usuario Incorrecto");
                    estado.setText("Contraseña Incorrecta");
                    JFrame frame = new JFrame();
                    frame.setContentPane(new login().MainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500,450);
                    frame.setVisible(true);

                    ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                }

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                }




            }
        });
    }
}
