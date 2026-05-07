package com.ecommerce.utils;

public class MenuUtils {
    public static void imprimirConBorde(String mensaje){
        int largoTexto = mensaje.length();
        String bordeHorizontal = "+" + "-".repeat(largoTexto + 2) + "+";
        System.out.println(bordeHorizontal);
        System.out.println("| " + mensaje + " |");
        System.out.println(bordeHorizontal);
    }
}
