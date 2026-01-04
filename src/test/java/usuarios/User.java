package test.java.usuarios;

public class User {

    private int id;
    private String nombreUsuario;
    private boolean banned;
    private int diasBan;

    public User(int id, String nombreUsuario){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.banned = false;
        this.diasBan = 0;
    }

    public void setNombre(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
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

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "User{id=" + id +
                ", nombre='" + nombreUsuario + '\'' +
                ", baneado=" + banned +
                ", diasBan=" + diasBan + "}";
    }
}
