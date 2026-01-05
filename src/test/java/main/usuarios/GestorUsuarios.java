package test.java.main.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorUsuarios {
    private List<User> usuarios;

    public GestorUsuarios(){
        // METER LISTA ORDENADA EN UN ARRAYLIST
        this.usuarios = new ArrayList<User>();
    }

    public User createUser(User usuario){
        this.usuarios.add(usuario);
        return usuario;
    }

    public boolean borrarUsuario(int usuarioId){
        Iterator<User> iterator = this.usuarios.iterator();
        boolean deleted = false;
        while (iterator.hasNext()) {
            User usuario = iterator.next();
            if (usuario.getId() == usuarioId) {
                iterator.remove();
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean updateUsuario(int usuarioId, String nombreUsuario){
        boolean updated = false;
        for(User usuario : this.usuarios){
            if(usuario.getId() == usuarioId){
                // AGREGAR MAS CAMPOS SI SE PONEN MAS
                this.usuarios.get(this.usuarios.indexOf(usuario)).setNombre(nombreUsuario);
                updated = true;
            }
        }
        return updated;
    }

    public int banDays(){
        return (int)(Math.random() * 1000000) + 1;
    }

    public boolean banUsuario(int usuarioId){
        boolean banned = false;
        for (User usuario : this.usuarios) {
            if (usuario.getId() == usuarioId) {
                // SI EL USUARIO YA ESTA BANEADO
                if(usuario.isBaneado()){
                    System.out.println("El usuario ya esta baneado");
                    return false;
                }
                int dias = banDays();
                usuario.banear(dias);
                System.out.println("Usuario " + usuarioId + " baneado por " + dias + " días :3");
                banned = true;
            }
        }
        return banned;
    }

    public void seeUsuarios(){
        for(User usuario : this.usuarios){
            System.out.println(usuario);
        }
    }

}
