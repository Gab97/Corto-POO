/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_corto;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String codigo;
    private String nombre;
    private int cantidad;
    private String tipo;
    private boolean precio;
    private boolean disponibilidad;
    
    
    public Filtro (int id, String codigo, String nombre, 
            int cantidad, String tipo, boolean precio){
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }
    
    public Filtro (String codigo, String nombre, 
            int cantidad, String tipo, boolean precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }
    
    
    public Filtro (String nombre, int cantidad, String tipo, boolean precio){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }

    Object getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setDisponibilidad(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object setDisponibilidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int getStock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean getDisponilididad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
