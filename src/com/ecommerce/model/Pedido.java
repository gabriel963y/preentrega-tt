package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorIdPedido = 0;
    private int idPedido = 1;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.lineas = new ArrayList<>();
        this.idPedido = contadorIdPedido++;
    }

    public void agregarLinea(LineaPedido linea) {
        this.lineas.add(linea);
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::calcularSubTotal).sum();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }
}
