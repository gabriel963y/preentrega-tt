package com.ecommerce.exceptions;

public class ProductoInexistenteException extends RuntimeException{
    public ProductoInexistenteException(String mensaje) {
        super(mensaje);
    }
}
