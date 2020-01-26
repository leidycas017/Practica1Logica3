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
public class Polinomio {
    String polinomio;

    public Polinomio(String polinomio) {
        this.polinomio = polinomio;
    }
 

    public String getPolinomio() {
        return polinomio;
    }

    public void setPolinomio(String polinomio) {
        this.polinomio = polinomio;
    }
    /*
    public void simplificar(){
          double coeficiente;
          int exponente;
          double suma;
        String[] separado = polinomio.split(" ");
        String[] nuevo;
         for(int i=0; i<separado.length; i=i+2){
             coeficiente = Double.parseDouble(separado[i]);
             exponente = Integer.parseInt(separado[i+1]);
             
             
             for(int j=3;j<separado.length;j++){
                 if(exponente == Double.parseDouble(separado[j])){
                     suma= coeficiente+Double.parseDouble(separado[j-1]);
                 }
                 j=j+2;
             }
 
         }
          }
    */
}
