package com.ecommerce.ui;

import com.ecommerce.exceptions.ProductoExistenteException;
import com.ecommerce.exceptions.StockInsuficienteException;
import com.ecommerce.model.LineaPedido;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.Producto;
import com.ecommerce.service.InventarioService;
import com.ecommerce.service.PedidoService;
import com.ecommerce.utils.MenuUtils;
import com.ecommerce.utils.ScannerUtils;

import static com.ecommerce.utils.Colores.*;

import java.util.List;
import java.util.Optional;


public class MenuPrincipal {
    public static final int AGREGAR_PRODUCTO = 1;
    public static final int LISTAR_PRODUCTOS = 2;
    public static final int BUSCAR_PRODUCTO = 3;
    public static final int ACTUALIZAR_PRODUCTO = 4;
    public static final int ELIMINAR_PRODUCTO = 5;
    public static final int CREAR_PEDIDO = 6;
    public static final int LISTAR_PEDIDOS = 7;
    public static final int SALIR = 8;

    public static void iniciar(InventarioService inventario) {
        PedidoService pedido = new PedidoService(inventario);
        List<Producto> productos = inventario.listarProductos();

        while (true) {
            System.out.println(GREEN + """
              ╔══════════════════════════════════════╗
              ║         GESTIÓN DE PRODUCTOS         ║
              ╠══════════════════════════════════════╣
              ║ Elegir una opción:                   ║
              ║                                      ║
              ║ 1. Agregar producto                  ║
              ║ 2. Listar productos                  ║
              ║ 3. Buscar producto                   ║
              ║ 4. Actualizar producto               ║
              ║ 5. Eliminar producto                 ║
              ║ 6. Crear pedido                      ║
              ║ 7. Listar pedidos                    ║
              ║ 8. Salir                             ║
              ╚══════════════════════════════════════╝
              """);
            int opcion = ScannerUtils.capturarNumero(YELLOW + "Opción: " + RESET);


            switch (opcion) {
                case AGREGAR_PRODUCTO -> {
                    try {
                    String nombre = ScannerUtils.capturarTexto("Ingresa el nombre del producto: ");
                    double precio = ScannerUtils.capturarNumero("Ingresa el precio del producto: ");
                    int stock = ScannerUtils.capturarNumero("Ingresa el stock del producto: ");
                    String categoria = ScannerUtils.capturarTexto("Ingresa la categoría del producto: ");

                    Producto nuevoProducto = new Producto(nombre, precio, stock, categoria);
                    inventario.agregarProducto(nuevoProducto);
                    System.out.println(GREEN);
                    MenuUtils.imprimirConBorde("✔ Producto agregado con éxito con id: " + nuevoProducto.getId());
                    System.out.println(RESET);
                    } catch (ProductoExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case  LISTAR_PRODUCTOS -> {
                    if (productos.isEmpty()) {
                        System.out.println(RED + "No hay productos en el inventario.");
                    } else {
                    System.out.println(CYAN + "╔════╦════════════════════════════════╦══════════╦═══════╦═════════════════╗");
                    System.out.println("║ ID ║ Nombre                         ║ Precio   ║ Stock ║ Categoría       ║");
                    System.out.println("╠════╬════════════════════════════════╬══════════╬═══════╬═════════════════╣" + RESET);
                        productos.forEach(p -> System.out.printf(CYAN + "║" + RESET + " %-2d " + CYAN + "║" + RESET + " %-30s " + CYAN + "║" + RESET + " $%-7.2f " + CYAN + "║" + RESET + " %-5d " + CYAN + "║" + RESET + " %-15s " + CYAN + "║\n",
                                p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria()));
                    System.out.println(CYAN + "╚════╩════════════════════════════════╩══════════╩═══════╩═════════════════╝" + RESET);
                    }
                }

                case BUSCAR_PRODUCTO -> {
                    String nombre = ScannerUtils.capturarTexto("Ingresa el nombre del producto: ");
                    Optional<Producto> productoEncontrado = inventario.buscarPorNombre(nombre);

                    if (productoEncontrado.isPresent()) {
                        Producto p = productoEncontrado.get();

                        System.out.println(CYAN + "╔════════════════════════════════════════╗");
                        System.out.println("║          FICHA DEL PRODUCTO            ║");
                        System.out.println("╠════════════════════════════════════════╣" + RESET);
                        System.out.printf(CYAN + "║ " + RESET + "%-12s %-25s" + CYAN + " ║\n" + RESET, "ID:", p.getId());
                        System.out.printf(CYAN + "║ " + RESET + "%-12s %-25s" + CYAN + " ║\n" + RESET, "Nombre:", p.getNombre());
                        String precioFormateado = String.format("$%.2f", p.getPrecio());
                        System.out.printf(CYAN + "║ " + RESET + "%-12s %-25s" + CYAN + " ║\n" + RESET, "Precio:", precioFormateado);
                        System.out.printf(CYAN + "║ " + RESET + "%-12s %-25s" + CYAN + " ║\n" + RESET, "Stock:", p.getStock() + " u.");
                        System.out.printf(CYAN + "║ " + RESET + "%-12s %-25s" + CYAN + " ║\n" + RESET, "Categoría:", p.getCategoria());
                        System.out.println(CYAN + "╚════════════════════════════════════════╝" + RESET);

                    } else {
                        System.out.println(RED + "⚠ Error: No se encontró ningún producto con el nombre '" + nombre + "'." + RESET);
                    }
                }

                case ACTUALIZAR_PRODUCTO ->  {
                    int idProducto = ScannerUtils.capturarNumero("Ingresa el ID del producto a actualizar: ");
                    inventario.actualizarProducto(idProducto);
                }

                case ELIMINAR_PRODUCTO -> {
                    int idProducto = ScannerUtils.capturarNumero("Ingresa el ID del producto a eliminar: ");
                    inventario.eliminarProducto(idProducto);
                }

                case CREAR_PEDIDO -> {
                    String nombreProducto = ScannerUtils.capturarTexto("Ingresar el nombre del producto que desea pedir: ");
                    int cantidad = ScannerUtils.capturarNumero("Ingresar la cantidad que desea pedir: ");
                    Optional<Producto> producto = inventario.buscarPorNombre(nombreProducto);
                    if (producto.isPresent()){
                        try {
                            pedido.crearPedido(producto.get(), cantidad);
                        } catch (StockInsuficienteException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(RED + "⚠ El producto no existe." + RESET);
                    }
                }

                case LISTAR_PEDIDOS -> {
                    List<Pedido> historial = pedido.listarPedidos();

                    if (historial.isEmpty()) {
                        System.out.println(RED + "No hay pedidos registrados en el historial." + RESET);
                    } else {
                        System.out.println(CYAN + "╔════════════════════════════════════════════════════╗");
                        System.out.println("║                HISTORIAL DE PEDIDOS                ║");
                        System.out.println("╠════════════════════════════════════════════════════╣" + RESET);

                        int contador = 0;
                        for (Pedido p : historial) {
                            contador++;

                            String tituloPedido = "PEDIDO #" + p.getIdPedido();
                            String totalPedido = String.format("Total: $%.2f", p.calcularTotal());
                            System.out.printf(CYAN + "║ " + RESET + "%-25s %24s" + CYAN + " ║\n" + RESET, tituloPedido, totalPedido);
                            System.out.println(CYAN + "╟────────────────────────────────────────────────────╢" + RESET);

                            for (LineaPedido linea : p.getLineas()) {
                                String detalleProducto = linea.getCantidad() + "x " + linea.getProducto().getNombre();
                                String detalleSubtotal = String.format("$%.2f", linea.calcularSubTotal());

                                if (detalleProducto.length() > 32) detalleProducto = detalleProducto.substring(0, 29) + "...";

                                System.out.printf(CYAN + "║ " + RESET + "  %-32s %15s" + CYAN + " ║\n" + RESET, detalleProducto, detalleSubtotal);
                            }

                            if (contador == historial.size()) {
                                System.out.println(CYAN + "╚════════════════════════════════════════════════════╝" + RESET);
                            } else {
                                System.out.println(CYAN + "╠════════════════════════════════════════════════════╣" + RESET);
                            }
                        }
                    }
                }

                case SALIR -> {
                    System.out.println(GREEN + "Hasta pronto!!");
                    System.exit(0);
                }

                default -> throw new IllegalArgumentException(RED + "Opción incorrecta.");
            }

        }

    }

}
