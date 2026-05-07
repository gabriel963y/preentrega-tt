package com.ecommerce.exceptions;

public class ProductoExistenteException extends RuntimeException {
    public ProductoExistenteException(String mensaje) {
        super(mensaje);
    }
}
