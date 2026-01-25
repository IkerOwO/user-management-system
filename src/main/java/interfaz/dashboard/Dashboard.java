package main.java.interfaz.dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Vector;

import main.java.resources.SQLConnection.SQLConnection;
import main.java.usuarios.GestorUsuarios;
import main.java.usuarios.User;

public class Dashboard extends JFrame{
    // DECLARACIÓN DE CADA ELEMENTO
    public JTable tablaUsuarios;
    public DefaultTableModel modeloTabla;
    public JButton addUsuario, deleteUsuario, banUsuario;
    public JTextField idField, userField, groupField;
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
        gestor = new GestorUsuarios();
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

        banUsuario = new JButton("Banear usuario");
        banUsuario.setBounds(700,700,120,30);
        banUsuario.setVisible(true);
        banUsuario.addActionListener(this::banUser);

        panelBotones.add(addUsuario);
        panelBotones.add(deleteUsuario);
        panelBotones.add(banUsuario);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void createTable(){
        String[] columns = { "User ID", "Username", "Group", "Banned" };

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
        groupField = new JTextField();
        Vector<Boolean> comboBoxItems = new Vector<>();
        comboBoxItems.add(Boolean.TRUE);
        comboBoxItems.add(Boolean.FALSE);
        JComboBox<Boolean> baneado = new JComboBox<>(comboBoxItems);
        try{
            Object[] message = {
                    "ID:", idField,
                    "Usuario:", userField,
                    "Grupo:", groupField,
                    "Baneado?:", baneado
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
                String grupo = groupField.getText();
                Boolean estaBaneado = (Boolean) baneado.getSelectedItem();
                // CREAR USUARIO CON LA CLASE USER (SOLO ID Y NOMBRE DE USUARIO)
                User usuarios = new User(idUsuario, nombreUsuario, grupo);
                // GUARDAR USUARIO EN ARRAYLIST CON EL GESTOR
                gestor.createUser(usuarios);
                // MANDAR A LA TABLA
                modeloTabla.addRow(new Object[]{ idUsuario, nombreUsuario, grupo, estaBaneado });
                // AGREGAR USUARIO A LA BBDD
                String insertUser = String.format("INSERT INTO users(id, nombreUsuario, banned) VALUES (%s, %s, %b) ", idUsuario, nombreUsuario, estaBaneado);
                Connection(insertUser);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void deleteUser(ActionEvent e){
        userField = new JTextField();
        try{
            Object[] message = {
                    "Username:", userField
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Introduce el username del usuario a borrar",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                String username = userField.getText();
                // BORRAR USUARIO CON EL GESTOR
                gestor.borrarUsuario(username);
                // BORRAR EL USUARIO DE LA TABLA
                for(int i = 0; i < modeloTabla.getRowCount(); i++){
                    String userTabla = modeloTabla.getValueAt(i, 1).toString();
                    if(userTabla.equalsIgnoreCase(username.trim())){
                        modeloTabla.removeRow(i);
                        break;
                    }
                }
                // BORRAR EL USUARIO DE LA BBDD
                String deleteUser = String.format("DELETE FROM users WHERE nombreUsuario = %s", username);
                Connection(deleteUser);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void banUser(ActionEvent e){
        userField = new JTextField();
        try{
            Object[] message = {
                    "Username:", userField
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Introduce el username del usuario a banear",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                String username = userField.getText();
                // BANEAR USUARIO CON EL GESTOR
                gestor.banUsuario(username);
                // MODIFICAR USUARIO EN TABLA
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    String usernameTabla = modeloTabla.getValueAt(i, 1).toString();
                    if (usernameTabla.equalsIgnoreCase(username.trim())) {
                        modeloTabla.setValueAt(Boolean.TRUE, i, 3);
                        break;
                    }
                }
                // MODIFICAR USUARIO EN BBDD
                String banUser = String.format("UPDATE users SET banned = true WHERE nombreUsuario = %s", username);
                Connection(banUser);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // PARA LA CONEXION CON LA BBDD
    private void Connection(String query){
        SQLConnection.Connection(query);
    }
}
