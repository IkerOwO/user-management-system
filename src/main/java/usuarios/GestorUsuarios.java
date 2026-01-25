package main.java.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorUsuarios {
    private List<User> usuarios;

    public GestorUsuarios(){
        // METER LISTA ORDENADA EN UN ARRAYLIST
        this.usuarios = new ArrayList<User>();
    }

    public int genID(){
        return (int)(Math.random() * 1000000);
    }

    public User createUser(User usuario){
        this.usuarios.add(usuario);
        return usuario;
    }

    public boolean borrarUsuario(String user){
        Iterator<User> iterator = this.usuarios.iterator();
        boolean deleted = false;
        while(iterator.hasNext()){
            User usuario = iterator.next();
            if(usuario.getNombre().equals(user)){
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

    public boolean banUsuario(String user){
        boolean banned = false;
        for(User usuario : this.usuarios){
            if(usuario.getNombre().equals(user)){
                if(usuario.isBaneado()){
                    System.out.println("El usuario ya esta baneado");
                    return false;
                }
                int dias = banDays();
                usuario.banear(dias);
                System.out.println("Usuario " + user + " baneado por " + dias + " días :3");
                banned = true;
            }
        }
        return banned;
    }
}
