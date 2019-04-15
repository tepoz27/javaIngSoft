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
class Client {
    private int IDCliente;
    private String Nombres, Apellidos, Direccion, Telefono, Email;
    
    public Client(int id, String name, String ape, String dir, String tel, String mail){
        this.IDCliente = id;
        this.Nombres = name;
        this.Apellidos = ape;
        this.Direccion = dir;
        this.Telefono = tel;
        this.Email = mail;
    }
    
    public int getID(){
        return IDCliente;
    }
    
    public String getNombre(){
        return Nombres;
    }
    
    public String getApellidos(){
        return Apellidos;
    }
    
    public String getDir(){
        return Direccion;
    }
    
    public String getTel(){
        return Telefono;
    }
    
    public String getEmail(){
        return Email;
    }
}
