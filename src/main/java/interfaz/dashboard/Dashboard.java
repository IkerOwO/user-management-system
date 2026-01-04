package main.java.interfaz.dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Objects;
import main.java.usuarios.User;

public class Dashboard extends JFrame{
    // DECLARACIÓN DE CADA ELEMENTO
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    public JButton addUsuario, deleteUsuario, banUsuario;
    public JTextField idField, userField;

    public Dashboard(){
        setSize(1200,800);
        setResizable(false);
        setTitle("Dashboard");
        setLayout(null);
        setVisible(true);
        setIconImage(new ImageIcon(Objects.requireNonNull(Dashboard.class.getResource("../../resources/icon.png"))).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUI();
    }

    private void setUI(){
        // MOSTRAR TABLA
        createTable();

        // BOTONES

        addUsuario = new JButton("Crear usuario");
        addUsuario.setBounds(400,700,120,30);
        addUsuario.setVisible(true);
        addUsuario.addActionListener(this::createUser);
        add(addUsuario);

        deleteUsuario = new JButton("Borrar usuario");
        deleteUsuario.setBounds(550,700,120,30);
        deleteUsuario.setVisible(true);
        deleteUsuario.addActionListener(this::deleteUser);
        add(deleteUsuario);

        banUsuario = new JButton("Borrar usuario");
        banUsuario.setBounds(700,700,120,30);
        banUsuario.setVisible(true);
        banUsuario.addActionListener(this::banUser);
        add(banUsuario);
    }

    private void createTable(){
        String[] columns = { "User ID", "Username" };

        modeloTabla = new DefaultTableModel(columns, 0);
        tablaUsuarios = new JTable(modeloTabla);

        this.add(new JScrollPane(tablaUsuarios));
    }

    // IMPLEMENTACIÓN DE LOS BOTONES
    private void createUser(ActionEvent e){
        idField = new JTextField();
        userField = new JTextField();

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
    }

    private void deleteUser(ActionEvent e){



    }

    private void banUser(ActionEvent e){

    }

}
