/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PolinomiosLista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import polinomios.util.Termino;

/**
 *
 * @author usuario
 */
public class ArchivoPolinomios {
    private ArrayList<Polinomio> ListaPolinomios;
    Scanner entrada= new Scanner(System.in);
    File Fichero = new File("DatosPolinomios.txt");
    PolinomioListaSimpleConCabeza polN[];

     public int ingresar(){
         System.out.println("Ingreso de Polinomios");
          boolean sw = true;
         String polinomio;
         ListaPolinomios = new ArrayList();
         int i=1;
        do {  
             System.out.println("Ingrese el polinomio "+i+":");
             polinomio = entrada.nextLine();
 
             Polinomio nuevoPol = new Polinomio(polinomio);
             ListaPolinomios.add(nuevoPol);
             System.out.println("Continuar ingresando polinomios. True/False");
             sw = entrada.nextBoolean();
             entrada.nextLine();
             if(!sw){
             ArchivoPolinomios a = new ArchivoPolinomios();
             a.escribir(ListaPolinomios);
             }
             i++;
        } while (sw);   
        int cant = i;
        crearTerminos(cant);
        return  cant;
     }
     
     public PolinomioListaSimpleConCabeza simplificar(PolinomioListaSimpleConCabeza sim){
         Nodo ant = sim.getCabeza().getLiga();
         Nodo pp =ant;
         Nodo p = ant.getLiga();
         
         while(ant!=null){
             while(p!=null){
                 if(ant.getTermino().getE()==p.getTermino().getE()){
                     ant.getTermino().setC(p.getTermino().getC()+ant.getTermino().getC());
                     pp.setLiga(p.getLiga());
                     p=pp.getLiga();
                 }else{
                     pp=p;
                     p=p.getLiga();
                 }
             }
             ant=ant.getLiga();
             pp=ant;
             if(ant!=null){
                 p=ant.getLiga(); 
             }
         }
       return  sim;
     }
     
     public void crearTerminos(int cant){
           int p=1;
           polN= new PolinomioListaSimpleConCabeza[cant+1];
          polN[p] = new PolinomioListaSimpleConCabeza();
          Nodo cA = polN[p].getCabeza();
        try{
            BufferedReader bf = new BufferedReader(new FileReader(Fichero));
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                if(p!=1){
                    polN[p]= new PolinomioListaSimpleConCabeza();
                    cA= polN[p].getCabeza();
                }
                String[] separado = bfRead.split(" ");
                for(int i=0; i<separado.length; i=i+2){
                    double c = Double.parseDouble(separado[i]);
                    int e = Integer.parseInt(separado[i+1]);
                    Termino t = new Termino(e,c);
                     Nodo n = new Nodo(t);
                     cA.setLiga(n);
                     cA = n;
                }
                p++;
            }
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
     }
     
     public void escribir(ArrayList<Polinomio> ListaPolinomios) {

        PrintWriter salida = null;
        try {
            // escritura de datos
            salida = new PrintWriter(new BufferedWriter(new FileWriter(Fichero,true)));
            for (int i = 0; i < ListaPolinomios.size(); i++) {
                salida.println(ListaPolinomios.get(i).getPolinomio());
            }
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Datos no Compatibles");
        } finally {
            salida.close();
        }
    }
     
     public void leerTxt(){ 
        String texto = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(Fichero));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){ 
                temp += bfRead +"\n"; //guardado el texto del archivo
            }
            texto = temp;
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
        System.out.println(texto);
    }
     
     public PolinomioListaSimpleConCabeza Mostrar(int p){
         return polN[p];
     }
     
     
     public PolinomioListaSimpleConCabeza multiplicar(int p1, int p2 ){
         PolinomioListaSimpleConCabeza polC= new PolinomioListaSimpleConCabeza();
         polN[1]= simplificar(polN[p1]);
         polN[2]=simplificar(polN[p2]);
         Nodo pol1=polN[p1].getCabeza().getLiga();
         Nodo pol2=polN[p2].getCabeza().getLiga();
         Nodo pol3 = polC.getCabeza();

         double coeficiente1,coeficiente2;
         int exponente1,exponente2;
         
         while(!(polN[p1].finRecorrido(pol1))){
             while(!(polN[p2].finRecorrido(pol2)) ){
            coeficiente1=pol1.getTermino().getC();
            exponente1=pol1.getTermino().getE();
            coeficiente2=pol2.getTermino().getC();
            exponente2=pol2.getTermino().getE();
            
             Termino t = new Termino(exponente1+exponente2, coeficiente1*coeficiente2);
             Nodo n = new Nodo(t);
             pol3.setLiga(n);
             pol3 = n;
             pol2=pol2.getLiga();
         }
         pol1=pol1.getLiga();
         pol2=polN[p2].getCabeza().getLiga();

         }
 
         return polC;
     }
     
     public PolinomioListaSimpleConCabeza derivar(int p ){
       PolinomioListaSimpleConCabeza polC= new PolinomioListaSimpleConCabeza();
       polN[p]=simplificar(polN[p]);
       Nodo d = polN[p].getCabeza().getLiga();
       Nodo nuevo=polC.getCabeza();
       double coeficiente;
       int exponente;
       while(d!=null){
           exponente = d.getTermino().getE();
           coeficiente = d.getTermino().getC();
           Termino t = new Termino(exponente-1, exponente*coeficiente);
           Nodo n = new Nodo(t);
           nuevo.setLiga(n);
           nuevo = n;
           d=d.getLiga();
       }
       return polC;
}
     
     public double Evaluar(int p, double x){
         polN[p]=simplificar(polN[p]);
         Nodo e = polN[p].getCabeza().getLiga();
         double acum=0;
         double coeficiente;
         int exponente;
         while(e!=null){
            coeficiente = e.getTermino().getC();
            exponente = e.getTermino().getE();
            acum += coeficiente*(Math.pow(x, exponente));
            e=e.getLiga();
         }
       return  acum;
     }
     
     public void borrar(){
       Fichero.delete();
     }
     
}
     

