package main.java.interfaz.dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

import main.java.usuarios.GestorUsuarios;
import main.java.usuarios.User;

public class Dashboard extends JFrame{
    // DECLARACIÓN DE CADA ELEMENTO
    public JTable tablaUsuarios;
    public DefaultTableModel modeloTabla;
    public JButton addUsuario, deleteUsuario, banUsuario;
    public JTextField idField, userField;
    public GestorUsuarios gestor;

    public Dashboard(){
        setSize(1200,800);
        setResizable(false);
        setTitle("Dashboard");
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon(
                Objects.requireNonNull(Dashboard.class.getResource("../../resources/icon.png"))).getImage()
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUI();
        setVisible(true);
    }

    private void setUI(){
        // MOSTRAR TABLA
        createTable();

        // PANEL BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // BOTONES
        addUsuario = new JButton("Crear usuario");
        addUsuario.setBounds(400,700,120,30);
        addUsuario.setVisible(true);
        addUsuario.addActionListener(this::createUser);

        deleteUsuario = new JButton("Borrar usuario");
        deleteUsuario.setBounds(550,700,120,30);
        deleteUsuario.setVisible(true);
        deleteUsuario.addActionListener(this::deleteUser);

        banUsuario = new JButton("Borrar usuario");
        banUsuario.setBounds(700,700,120,30);
        banUsuario.setVisible(true);
        banUsuario.addActionListener(this::banUser);

        panelBotones.add(addUsuario);
        panelBotones.add(deleteUsuario);
        panelBotones.add(banUsuario);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void createTable(){
        String[] columns = { "User ID", "Username" };

        modeloTabla = new DefaultTableModel(columns, 0);
        tablaUsuarios = new JTable(modeloTabla);

        this.add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    // IMPLEMENTACIÓN DE LOS BOTONES
    private void createUser(ActionEvent e){
        idField = new JTextField();
        userField = new JTextField();
        try{
            Object[] message = {
                    "ID:", idField,
                    "Usuario:", userField
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Crear usuario",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                int idUsuario = Integer.parseInt(idField.getText());
                String nombreUsuario = userField.getText();
                // CREAR USUARIO CON LA CLASE USER
                User usuarios = new User(idUsuario, nombreUsuario);
                // MANDAR EL ID Y NOMBRE DE USUARIO A LA TABLA
                modeloTabla.addRow(new Object[]{ idUsuario, nombreUsuario });
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void deleteUser(ActionEvent e){
        idField = new JTextField();
        try{
            Object[] message = {
                    "ID:", idField,
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Introduce el ID del usuario a borrar",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                int idUsuario = Integer.parseInt(idField.getText());
                // BORRAR USUARIO CON EL GESTOR
                gestor.borrarUsuario(idUsuario);
                // BORRAR EL USUARIO DE LA TABLA
                modeloTabla.removeRow(idUsuario);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }

    private void banUser(ActionEvent e){



    }
}
