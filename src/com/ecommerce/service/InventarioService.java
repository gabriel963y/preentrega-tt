package com.ecommerce.service;

import com.ecommerce.exceptions.ProductoExistenteException;
import com.ecommerce.exceptions.ProductoInexistenteException;
import com.ecommerce.model.Producto;
import com.ecommerce.utils.Colores;
import com.ecommerce.utils.MenuUtils;
import com.ecommerce.utils.ScannerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventarioService {
    private final List<Producto> productos;
    private static int contadorId = 1;

    public InventarioService() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto){

        if (buscarPorNombre(producto.getNombre()).isPresent()) {
            throw new ProductoExistenteException(Colores.RED + "El producto '" + producto.getNombre() + "' ya existe.");
        }

        producto.setId(contadorId);
        contadorId++;
        productos.add(producto);
    }

    public List<Producto> listarProductos(){
        return this.productos;
    }

    public Optional<Producto> buscarPorNombre(String nombreProducto) {
        return productos.stream().filter(
                producto -> producto.getNombre().equalsIgnoreCase(nombreProducto)
        ).findFirst();
    }

    public Optional<Producto> buscarPorId(int idProducto) {
        return productos.stream().filter(
                producto -> producto.getId() == idProducto
        ).findFirst();
    }

    public void actualizarProducto(int idProducto) {
        if (productos.isEmpty()) {
            System.out.println(Colores.RED + "No hay productos disponibles.");
            return;
        }

        Optional<Producto> productoOpt = buscarPorId(idProducto);

        if (productoOpt.isEmpty()) {
            System.out.println(Colores.RED + "⚠ No se encontró ningún producto con ese ID." + Colores.RESET);
            return;
        }

        Producto productoNuevo = productoOpt.get();

        while (true) {
            System.out.println(Colores.GREEN + """
                    ╔══════════════════════════════════════╗
                    ║          ACTUALIZAR PRODUCTO         ║
                    ╠══════════════════════════════════════╣
                    ║  Qué te gustaría actulizar?          ║
                    ║ 1. Nombre                            ║
                    ║ 2. Precio                            ║
                    ║ 3. Stock                             ║
                    ║ 4. Salir                             ║
                    ╚══════════════════════════════════════╝
                    """);
            int opcion = ScannerUtils.capturarNumero(Colores.YELLOW + "Opcion: " + Colores.RESET);

            switch (opcion) {
                case 1 -> {
                    String nuevoNombre = ScannerUtils.capturarTexto(Colores.YELLOW + "Ingresa el nuevo nombre: ");
                    productoNuevo.setNombre(nuevoNombre);
                }

                case 2 -> {
                    double nuevoPrecio = ScannerUtils.capturarNumero(Colores.YELLOW + "Ingresa el nuevo precio: ");
                    productoNuevo.setPrecio(nuevoPrecio);
                }

                case 3 -> {
                    int nuevoStock = ScannerUtils.capturarNumero(Colores.YELLOW + "Ingresa el nuevo stock: ");
                    productoNuevo.setStock(nuevoStock);
                }

                case 4 -> {
                    return;
                }

                default -> {
                    System.out.println(Colores.RED + "Opción inválida.");
                    return;
                }

            }

        }

    }

    public void eliminarProducto(int idProducto) {
        buscarPorId(idProducto).ifPresentOrElse(
                producto -> {
                    System.out.println(Colores.GREEN + """
                           ╔══════════════════════════════════════╗
                           ║     DESEA ELIMINAR EL PRODUCTO?      ║
                           ╠══════════════════════════════════════╣
                           ║   1. Sí                              ║
                           ║   2. No                              ║
                           ╚══════════════════════════════════════╝
                           """);
                    int opcion = ScannerUtils.capturarNumero(Colores.RED + "Opcion: ");

                    if (opcion == 1) {
                        productos.remove(producto);
                        System.out.println(Colores.GREEN);
                        MenuUtils.imprimirConBorde("✔ Producto eliminado con éxito.");
                        System.out.println(Colores.RESET);
                    }
                },
                () -> {
                    throw new ProductoInexistenteException(Colores.RED + "No se encontró el producto.");
                }
        );

    }

}
