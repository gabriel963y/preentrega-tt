import com.ecommerce.model.Producto;
import com.ecommerce.service.InventarioService;
import com.ecommerce.ui.MenuPrincipal;

void main() {
    InventarioService inventario = new InventarioService();
    cargarDatosDePrueba(inventario);
    MenuPrincipal.iniciar(inventario);
}

private static void cargarDatosDePrueba(InventarioService inventario) {
    inventario.agregarProducto(new Producto("Café molido", 4500, 30, "Bebidas"));
    inventario.agregarProducto(new Producto("Yerba mate 1kg", 3200, 50, "Bebidas"));
    inventario.agregarProducto(new Producto("Galletitas saladas", 1850, 100, "Almacén"));
    inventario.agregarProducto(new Producto("Agua 500ml", 6700, 20, "Almacén"));
    inventario.agregarProducto(new Producto("Alfajor de chocolate", 2900, 15, "Golosinas"));
    inventario.agregarProducto(new Producto("Alfajor de chocolate blanco", 2200, 45, "Golosinas"));
}
