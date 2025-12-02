/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniproyecto;

/**
 *
 * @author bere0
 */
public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
    private boolean disponible;

    public Producto(int id, String nombre, String categoria, double precio, int stock, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public boolean isDisponible() { return disponible; }

    // Setter necesario para aplicar descuento (o usa otro método)
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', categoria='%s', precio=%.2f, stock=%d, disponible=%s}",
                id, nombre, categoria, precio, stock, disponible);
    }
}

