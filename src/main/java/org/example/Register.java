package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    public JPanel MainPanel;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField edad;
    private JTextField descripcion;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JLabel Estado;
    private JTextField apellido;
    private JTextField n_historial;
    private JButton regresarButton;

    String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user="root";
    String passw="123456";

    Paciente paciente = new Paciente();

    public Register() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paciente.setCedula(cedula.getText());
                paciente.setN_historial(Integer.parseInt(n_historial.getText()));
                paciente.setNombre(nombre.getText());
                paciente.setApellido(apellido.getText());
                paciente.setTelefono(telefono.getText());
                paciente.setEdad(Integer.parseInt(edad.getText()));
                paciente.setDescripcion(descripcion.getText());



                try (Connection connection = DriverManager.getConnection(url,user,passw)) {
                    System.out.println("Connected");
                    String sql = "insert into paciente(cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) values (?,?,?,?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, paciente.getCedula());
                    statement.setInt(2, paciente.getN_historial());
                    statement.setString(3, paciente.getNombre());
                    statement.setString(4, paciente.getApellido());
                    statement.setString(5, paciente.getTelefono());
                    statement.setInt(6, paciente.getEdad());
                    statement.setString(7, paciente.getDescripcion());

                    statement.executeUpdate();
                    Estado.setText("Registro Exitoso");

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    Estado.setText("Algo Salió mal :(");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cedula.setText(null);
                n_historial.setText(null);
                nombre.setText(null);
                apellido.setText(null);
                telefono.setText(null);
                edad.setText(null);
                descripcion.setText(null);
            }
        });


        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Search().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,520);
                frame.setVisible(true);

                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();
            }
        });
    }
}
