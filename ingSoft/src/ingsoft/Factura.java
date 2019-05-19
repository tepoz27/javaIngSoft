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
class Factura {
    private int IDFactura;
    private String Nombre, ModoPago, Fecha;
    
    public Factura(int id, String nombre, String pago, String fecha){
        this.IDFactura = id;
        this.Nombre = nombre;
        this.ModoPago = pago;
        this.Fecha = fecha;
    }
    
    public int getID(){
        return IDFactura;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public String getModoPago(){
        return ModoPago;
    }
    
    public String getFecha(){
        return Fecha;
    }
}
