package main.java;

import main.java.interfaz.dashboard.Dashboard;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(Dashboard::new);
    }
}
