package com.ecommerce.model;

import com.ecommerce.utils.Colores;

public class Producto {
    private int id = 0;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto(String nombre, double precio, int stock, String categoria) {
        validarTexto(nombre, "Nombre");
        validarNoNegativo(precio, "Precio");
        validarNoNegativo(stock, "Stock");
        validarTexto(categoria, "Categoría");
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarTexto(nombre, "Nombre");
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
       validarNoNegativo(precio, "Precio");
       this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        validarNoNegativo(stock, "Stock");
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        validarTexto(categoria, "Categoría");
        this.categoria = categoria;
    }

    public void validarNoNegativo(double valor, String campo){
        if (valor < 0) throw new IllegalArgumentException(Colores.RED + campo + " no puede ser negativo.");

    }
    public void validarTexto(String texto, String campo){
        if (texto == null || texto.trim().isEmpty()) throw new IllegalArgumentException(Colores.RED + campo + " no puede estar vacío.");
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "|" + nombre +
                "|$" + precio +
                "|Stock: " + stock +
                "|Categoría: " + categoria;
    }
}
