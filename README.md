# 🛒 Sistema de Gestión de Inventario y Pedidos (E-commerce)

Este es un proyecto de consola hecho en Java para simular la gestión de un E-commerce. Nos permite manejar el stock de productos, registrar pedidos, calcular los totales y ver toda la información en la terminal.

## 🛠 Tecnologías utilizadas

* **Lenguaje:** Java (JDK 25)
* **Entorno recomendado:** IntelliJ IDEA o VS Code
* **Interfaz:** Consola / Terminal

## ⚙️ Requisitos previos

Para poder correr este proyecto en tu compu, vas a necesitar:
* [Java Development Kit (JDK)](https://adoptium.net/) (Versión 17 o superior).
* Git (para bajarte el código).

---

## 🚀 Cómo ejecutarlo

Seguí estos pasos para hacer andar la aplicación:

### 1. Clonar el repositorio
Abrí tu terminal y ejecutá este comando:
```bash
git clone [https://github.com/gabriel963y/preentrega-tt.git](https://github.com/gabriel963y/preentrega-tt.git)
cd preentrega-tt
```
### 2. Opción A: Desde IntelliJ IDEA
Si usás IntelliJ, la configuración se hace en un minuto:
- Abrí el programa, dale a Open y seleccioná la carpeta que acabas de clonar.

- Andá a File > Project Structure > Modules. Hacé clic derecho en la carpeta src y marcala como Sources Root (se tiene que poner de color azul).

- En esa misma ventana, aseguráte de que el Compiler output apunte a la carpeta out.

- Abrí el archivo src/Main.java y dale al botón de Play verde (Run).
### 2. Opción B: Desde visual studio code
- Aseguráte de tener instalada la extensión "Extension Pack for Java" (de Microsoft).

- Abrí la carpeta del proyecto en VS Code (File > Open Folder...).

- Andá a la carpeta src y abrí el archivo Main.java.

- Esperá unos segundos a que la extensión reconozca el proyecto. Justo arriba de la línea public static void main(String[] args), te va a aparecer un botón chiquito que dice Run. Hacé clic ahí para iniciar la consola.
---
## ✨ Qué incluye este proyecto
#### CRUD de productos: 
- Se puede agregar, buscar, ver la lista, modificar y eliminar productos del inventario.
#### Gestión de pedidos:
- Arma tickets de compra, agrupa los productos seleccionados y calcula el total a pagar automáticamente.
#### Validaciones a prueba de errores:
- Tiene control de stock y manejo de excepciones. Por ejemplo, si querés agregar un producto que ya existe o comprar más unidades de las que hay, el programa no explota, sino que te avisa con un mensaje claro.
#### Código ordenado por capas:
- La arquitectura está dividida (Models, Services, UI, Exceptions) para separar bien la interfaz matemática de la lógica del negocio.
