/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesvalidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class MatricesValidades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArchivoMatriz a = new ArchivoMatriz();
        Scanner entrada = new Scanner(System.in);
        String ruta;
        System.out.println("Ingrese la ruta del archivo (sin comillas)");
        ruta = entrada.next();
        a.Crear(ruta);
    }
}
      


