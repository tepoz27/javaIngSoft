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
class Product {
    private int IDProducto, Stock;
    private String Nombre, Categoria, Loca;
    private float Precio;
    
    public Product(int id, int stock, String name, String cate, String loca, float price){
        this.IDProducto = id;
        this.Nombre = name;
        this.Precio = price;
        this.Categoria = cate;
        this.Loca = loca;
        this.Stock = stock;
    }
    
    public int getID(){
        return IDProducto;
    }
    
    public int getStock(){
        return Stock;
    }
    
    public String getName(){
        return Nombre;
    }
    
    public String getCate(){
        return Categoria;
    }
    
    public String getLoca(){
        return Loca;
    }
    
    public float getPrecio(){
        return Precio;
    }
}
