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
        String ruta1, ruta2;
        System.out.println("Ingrese la ruta del archivo 1 (sin comillas)");
        ruta1 = entrada.next();
        System.out.println("Ingrese la ruta del archivo 2 (sin comillas)");
        ruta2 = entrada.next();
        a.Crear(ruta1, 0);
        a.Crear(ruta2, 1);
        a.mostrarDif();
    }
}
      


