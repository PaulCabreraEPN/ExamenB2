package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Search {
    public JPanel MainPanel;
    private JTextField campo;
    private JButton buscarButton;
    private JButton regresarButton;
    private JLabel Observaciones;
    private JButton cerrarSesiónButton;
    private JLabel cedula;
    private JLabel n_historial;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel telefono;
    private JLabel edad;
    private JLabel enfermedad;


    String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user="root";
    String passw="123456";

    public Search() {
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Register().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,520);
                frame.setVisible(true);

                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection connection= DriverManager.getConnection(url,user,passw)){
                    String query = "select * from paciente where cedula='"+campo.getText()+"'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()){
                        cedula.setText(resultSet.getString("cedula"));
                        n_historial.setText(resultSet.getString("n_historial_clinico"));
                        nombre.setText(resultSet.getString("nombre"));
                        apellido.setText(resultSet.getString("apellido"));
                        telefono.setText(resultSet.getString("telefono"));
                        edad.setText(resultSet.getString("edad"));
                        enfermedad.setText(resultSet.getString("descripcion_enfermedad"));

                        Observaciones.setText("Paciente Encontrado");
                    }


                    if (n_historial.getText()=="-"){
                        Observaciones.setText("Paciente NO Registrado");
                    }

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                }

            }
        });
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();
                frame.setContentPane(new login().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,450);
                frame.setVisible(true);

                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();

            }
        });
    }
}
