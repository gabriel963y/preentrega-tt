package com.ecommerce.service;

import com.ecommerce.exceptions.StockInsuficienteException;
import com.ecommerce.model.LineaPedido;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.Producto;
import com.ecommerce.utils.Colores;
import com.ecommerce.utils.MenuUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final InventarioService inventario;
    private final List<Pedido> historialPedidos;

    public PedidoService(InventarioService inventario) {
        this.inventario = inventario;
        this.historialPedidos = new ArrayList<>();
    }

    public void crearPedido(Producto producto, int cantidad){
        if (cantidad > producto.getStock()) throw new StockInsuficienteException(Colores.RED + "Error: Stock insuficiente." + Colores.RED);

        Pedido nuevoPedido = new Pedido();
        LineaPedido nuevaLinea = new LineaPedido(producto, cantidad);
        nuevoPedido.agregarLinea(nuevaLinea);
        producto.setStock(producto.getStock() - cantidad);
        historialPedidos.add(nuevoPedido);
        System.out.println(Colores.GREEN);
        MenuUtils.imprimirConBorde("✔ Pedido creado con éxito.");
        System.out.println(Colores.RESET);
    }

    public List<Pedido> listarPedidos() {
        return this.historialPedidos;
    }



}
