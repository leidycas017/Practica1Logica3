/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesvalidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author usuario
 */
public class ArchivoMatriz {
    MatrizEnListaLigadaForma1 matriz[] = new MatrizEnListaLigadaForma1[2];
    
      public void Crear(String ruta, int n){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String bfRead;
            int fila = 0;
            while((bfRead = bf.readLine()) != null){
                if(fila==0){
                    String[] separado = bfRead.split(" ");
                    matriz[n] = new MatrizEnListaLigadaForma1(Integer.parseInt(separado[0]), Integer.parseInt(separado[1]));
                }else{
                    char[] list = new char[bfRead.length()];
                    list=bfRead.toCharArray();
                    int[] num = new int[bfRead.length()];
                    for(int i=0; i<bfRead.length(); i++){
                        num[i]=Character.getNumericValue(list[i]);
                    }
                    int i=0;
                    for(int columna=1;columna<=bfRead.length();columna++){
                        if(num[i]!=0){
                            matriz[n].setCelda(fila, columna, num[i]);
                        }
                        i++;
                    }
                }
                fila++;
            }
            
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
          
    }
      
      public void mostrarDif(){
          //System.out.println(matriz[0]);
          //System.out.println(matriz[1]);
          int dif = matriz[0].Comparar(matriz[1]);
          int tamaño = matriz[0].getNodoCabezaMatriz().getT().getF();
          double porcentaje = (double)((double)dif/(tamaño*tamaño))*100;
          System.out.println("Hay: "+dif+" numeros de contraste diferentes");
          System.out.println("Resultado: "+porcentaje+"% de cambios");
      }
     

}
