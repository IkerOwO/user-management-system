package test.java.main.java;

import main.java.usuarios.GestorUsuarios;
import main.java.usuarios.User;

public class Main {
    public static void main(String[] args){
        // INSTANCIA GESTOR
        GestorUsuarios gestor = new GestorUsuarios();

        // CREAR USUARIOS
        User usuario1 = new User(1,"Pepe");
        User usuario2 = new User(2,"Jose");
        User usuario3 = new User(3,"Maria");

        gestor.createUser(usuario1);
        gestor.createUser(usuario2);
        gestor.createUser(usuario3);

        // BORRAR USUARIO
        gestor.borrarUsuario(1);

        // UPDATE USUARIO
        gestor.updateUsuario(2,"Francisco");

        // BANEAR USUARIO
        gestor.banUsuario(3);

        // VER USUARIOS
        gestor.seeUsuarios();
    }
}
