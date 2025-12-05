package com.olvealmacen.tienda.modelo;

public class Compra {

    private int id;
    private String fecha;
    private int idProveedor;
    private double total;

    public Compra() {}

    public Compra(int id, String fecha, int idProveedor, double total) {
        this.id = id;
        this.fecha = fecha;
        this.idProveedor = idProveedor;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
