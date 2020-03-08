/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PolinomiosLista;

import java.util.Scanner;



/**
 *
 * @author usuario
 */
public class Punto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       Scanner entrada = new Scanner(System.in);
       ArchivoPolinomios poli = new ArchivoPolinomios();
       
       int cant = poli.ingresar();
       int opc;
       do{
           System.out.println("Elige una opción:");
           System.out.println("1. Mostrar todos los polinomios");
           System.out.println("2. Mostrar uno de los polinomios");
           System.out.println("3. Multiplicar dos polinomios");
           System.out.println("4. Dividir dos polinomios");
           System.out.println("5. Derivar Polinomio");
           System.out.println("6. Evaluar polinomio");
           System.out.println("0. Salir\n");
           
           opc = entrada.nextInt();
           
           switch(opc){
               case 1:
                for(int i=1;i<cant;i++){
                System.out.println(i+". "+poli.simplificar(poli.Mostrar(i)));
                }
                break;
               case 2:
                System.out.println("Ingrese número del polinomio:");
                int num = entrada.nextInt();
                System.out.println(poli.simplificar(poli.Mostrar(num)));
                break;
               case 3:
                System.out.println("Ingrese número del primer polinomio");
                int pol1=entrada.nextInt();
                System.out.println("Ingrese número del segundo polinomio");
                int pol2=entrada.nextInt();
                System.out.println("Resultado de la multiplicacion:");
                System.out.println(poli.multiplicar(pol1,pol2));
                System.out.println("\n");
                break;
               case 4:
                   System.out.println("Ingrese el número del dividendo");
                   int dividendo = entrada.nextInt();
                   System.out.println("Ingrese el número del divisor");
                   int divisor = entrada.nextInt();
                   System.out.println("El resultado de la división es:");
                   poli.dividir(dividendo, divisor);
                   System.out.println(poli.dividir(dividendo, divisor));
                   break;
               case 5:
                System.out.println("Ingrese número del polinomio a derivar");
                int d=entrada.nextInt();
                System.out.println("Resultado de la derivada:");
                System.out.println(poli.derivar(d));
                break;
              case 6:
                System.out.println("Ingrese el número del polinomio a evaluar");
                int e=entrada.nextInt();
                System.out.println("Ingrese el valor de la variable");
                double v = entrada.nextDouble();
                System.out.println("Polinomio Evaluado:");
                System.out.println(poli.Evaluar(e,v)); 
                break;
          }
       }while(opc!=0);
 
    }
    
}
