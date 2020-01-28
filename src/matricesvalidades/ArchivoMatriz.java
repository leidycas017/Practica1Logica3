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
    MatrizEnListaLigadaForma1 matriz =new MatrizEnListaLigadaForma1();
    
      public void Crear(String ruta){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String bfRead;
            int fila = 0;
            while((bfRead = bf.readLine()) != null){
                if(fila==0){
                    String[] separado = bfRead.split(" ");
                    matriz = new MatrizEnListaLigadaForma1(Integer.parseInt(separado[0]), Integer.parseInt(separado[1]));
                }else{
                    char[] list = new char[bfRead.length()];
                    list=bfRead.toCharArray();
                    int[] num = new int[bfRead.length()];
                    for(int i=0; i<bfRead.length(); i++){
                        num[i]=Character.getNumericValue(list[i]);
                    }
                    int i=0;
                    for(int columna=1;columna<=bfRead.length();columna++){
                        matriz.setCelda(fila, columna, num[i]);
                        i++;
                    }
                }
                fila++;
            }
            
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
          System.out.println(matriz);
    }
}
