/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesvalidades;

import java.util.Scanner;

/**
 * Recibe dos archivos txt, que representan la información de una imagen
 * convierte la información en una matriz de nxn,(primera fila del archivo n n)
 * cada posición contiene un número de contraste, 
 * Este número de contraste es un número del 0 al 9
 * donde 0 es que no hay contraste y 9 el mayor contraste
 * Instancia variable de tipo ArchivoMatriz para acceder a los metodos
 * @author: G1_T1
 * @version: 04/03/2020
 * @see <a href = "https://github.com/YulyAR/logica3-polinomios.git" /> Url de Repositorio en Github - Polinomio representado en Vector Forma1 </a>
 */
public class MatricesValidades {

    
    public static void main(String[] args) {
        ArchivoMatriz a = new ArchivoMatriz();
        Scanner entrada = new Scanner(System.in);
        String ruta1, ruta2;
        
        System.out.println("Ingrese la ruta del archivo 1 (sin comillas)");
        ruta1 = entrada.next();
        System.out.println("Ingrese la ruta del archivo 2 (sin comillas)");
        ruta2 = entrada.next();
        //Crear recibe la ruta y el índice de la matriz
        a.Crear(ruta1, 0);
        a.Crear(ruta2, 1);
        //mostrarDif imprime los datos
        a.mostrarDif();
    }
}
      


