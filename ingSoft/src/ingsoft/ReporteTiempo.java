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
class ReporteTiempo {
    private int IDFactura, Cantidad;
    private float Precio;
    private String Nombre, Fecha, Producto;
    
    public ReporteTiempo(int id, String name, String prod, String fecha, int cant, float price){
        this.IDFactura = id;
        this.Cantidad = cant;
        this.Precio = price;
        this.Nombre = name;
        this.Fecha = fecha;
        this.Producto = prod;
    }
    
    public int getID (){
        return IDFactura;
    }
    
    public int getCant (){
        return Cantidad;
    }
    
    public String getNombre (){
        return Nombre;
    }
    
    public String getFecha (){
        return Fecha;
    }
    
    public String getProd (){
        return Producto;
    }
    
    public float getPrecio (){
        return Precio;
    }
}
