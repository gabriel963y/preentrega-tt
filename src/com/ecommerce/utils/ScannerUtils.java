package com.ecommerce.utils;

import java.util.Locale;
import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in).useLocale(Locale.US);

    public static String capturarTexto(String mensaje) {
        System.out.printf("%s", mensaje);
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje) {
        System.out.printf("%s", mensaje);
        while (!SCANNER.hasNextInt()){
            System.out.println(Colores.RED + "El dato ingresado es inválido.\n"  + Colores.RESET + mensaje);
            SCANNER.next();
        }
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

}
