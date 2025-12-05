package com.olvealmacen.tienda.modelo;

public class Venta {
    private int id;
    private String fecha;
    private int idCliente;
    private String nombreCliente;
    private String metodoPago;
    private double total;

    public Venta() { }

    public Venta(int id, String fecha, int idCliente, String nombreCliente, String metodoPago, double total) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.metodoPago = metodoPago;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
