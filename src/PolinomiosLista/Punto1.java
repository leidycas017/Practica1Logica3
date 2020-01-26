/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PolinomiosLista;



/**
 *
 * @author usuario
 */
public class Punto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ArchivoPolinomios poli = new ArchivoPolinomios();
       int cant = poli.ingresar();
       
       for(int i=1;i<cant;i++){
           System.out.println(poli.Mostrar(i));
       }
       
        System.out.println("Multiplicacion:");
       System.out.println(poli.multiplicar(1, 2));
       System.out.println("Derivada:");
       System.out.println(poli.derivar(1));
       System.out.println("Polinomio Evaluado:");
       System.out.println(poli.Evaluar(1, 3));
 
    }
    
}
