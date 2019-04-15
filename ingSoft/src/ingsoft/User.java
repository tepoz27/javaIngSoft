/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsoft;

/**
 *
 * @author mx06200a
 */
class User {
    private int IDUsuario;
    private String Usuario, Contra, Rol;
    
    public User(int id, String user, String cont, String rol){
        this.IDUsuario = id;
        this.Usuario = user;
        this.Contra = cont;
        this.Rol = rol;
    }
    
    public int getID(){
        return IDUsuario;
    }
    
    public String getUser(){
        return Usuario;
    }
    
    public String getContra(){
        return Contra;
    }
    
    public String getRol(){
        return Rol;
    }
}
