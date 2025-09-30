package org.example.Entity;

public class Product {
    private int id;
    private String nombre;
    private Double precio;
    private int stock;

    public Product() {}

    public Product(String nombre, int id, Double precio, int stock) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters


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
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        if (precio == null && precio < 0){
            throw new IllegalArgumentException("El precio no puede ser nulo ni negativo.");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    @Override
    public String toString(){
        return "Id: "+id+" Producto: "+ nombre + " Precio: " + precio + " Stock: " + stock;
    }
}
