package main.java.usuarios;

import java.util.Objects;

public class User {
    /*
        TODO -> Agregar mas elementos para los usuarios
     */
    private int id;
    private String nombreUsuario;
    private String grupo;
    private boolean banned;
    private int diasBan;

    public User(int id, String nombreUsuario, String grupo){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.grupo = grupo;
        this.banned = false;
        this.diasBan = 0;
    }

    public void setNombre(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    public boolean isBaneado() {
        return banned;
    }

    public void banear(int dias) {
        this.banned = true;
        this.diasBan = dias;
    }

    public void desbanear() {
        this.banned = false;
        this.diasBan = 0;
    }

    // GETTERS
    public int getDiasBan() {
        return diasBan;
    }

    public String getNombre(){
        return this.nombreUsuario;
    }

    public String getGrupo(){
        return this.grupo;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "User{id=" + id +
                ", nombre='" + nombreUsuario + '\'' +
                ", baneado=" + banned +
                ", Grupo=" + grupo +
                ", diasBan=" + diasBan + "}";
    }
}
