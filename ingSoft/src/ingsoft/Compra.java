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
public class Compra {
    
    private String Nombre;
    private int Cantidad, ID;
    private float Precio;
    
    public Compra(String name, int cant, int id, float price){
        this.Nombre = name;
        this.Cantidad = cant;
        this.Precio = price;
        this.ID = id;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public int getCantidad(){
        return Cantidad;
    }
    
    public float getPrecio(){
        return Precio;
    }
    
    public int getID(){
        return ID;
    }
    
}
