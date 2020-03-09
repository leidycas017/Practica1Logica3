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
 * Se ingresa una cadena del polinomio, coeficiente espacio exponente,
 * se ingresa en un arraylist y se crean los terminos
 * del polinomio en lista ligada con cabeza
 * se desarrollan los siguientes metodos:
 * Simplificar, ordenar, mostrar, dividir,multiplicar,
 * cambiar signo,derivar, evaluar.
 * @author Leidy Castaño
 * @version: 03/03/2020/A
 * @see <a href = "https://github.com/leidycas017/Practica1Logica3.git" /> Url
 * 
 * 
 */
public class ArchivoPolinomios {
    private ArrayList<Polinomio> ListaPolinomios;
    Scanner entrada= new Scanner(System.in);
    File Fichero = new File("DatosPolinomios.txt");
    PolinomioListaSimpleConCabeza polN[];

     public int ingresar(){
         borrar();
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
     
    public PolinomioListaSimpleConCabeza ordenar(PolinomioListaSimpleConCabeza ord){
        Nodo p = ord.getCabeza().getLiga();
        Nodo pp;
        int tempE;
        double tempC;
        while(p.getLiga()!= null){
            pp = p.getLiga();
            while(pp!=null){
                if(pp.getTermino().getE() > p.getTermino().getE()){
                    tempC = p.getTermino().getC();
                    tempE = p.getTermino().getE();
                    p.getTermino().setC(pp.getTermino().getC());
                    p.getTermino().setE(pp.getTermino().getE());
                    pp.getTermino().setC(tempC);
                    pp.getTermino().setE(tempE);
                }
                pp=pp.getLiga();
            }
            p=p.getLiga();
        }
        return ord;
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
                int numTerm = separado.length;
                if(numTerm % 2 == 0){
                    for(int i=0; i<separado.length; i=i+2){
                    double c = Double.parseDouble(separado[i]);
                    int e = Integer.parseInt(separado[i+1]);
                    Termino t = new Termino(e,c);
                    Nodo n = new Nodo(t);
                    cA.setLiga(n);
                    cA = n;
                } 
                }else{
                    System.err.println("Faltó ingresar exponentes");
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
     
     
     public PolinomioListaSimpleConCabeza Mostrar(int p){
         return ordenar(polN[p]);
     }
     
     public PolinomioListaSimpleConCabeza dividir(int p1, int p2) throws Exception{
       PolinomioListaSimpleConCabeza polCociente= new PolinomioListaSimpleConCabeza();
       Nodo CA = polCociente.getCabeza();
       polN[p1] = ordenar(simplificar(polN[p1]));
       polN[p2] = ordenar(simplificar(polN[p2]));
       if(polN[1].getGrado()>polN[2].getGrado()){
         PolinomioListaSimpleConCabeza dividendo = new PolinomioListaSimpleConCabeza();
         dividendo =polN[1];
         
         int gradoCociente = dividendo.getGrado() - polN[p2].getGrado();
         Nodo p = polN[1].getCabeza().getLiga();
         Nodo pp = polN[2].getCabeza().getLiga();
         while(dividendo!=null && dividendo.getGrado()>=polN[2].getGrado()){
            int exp = dividendo.getGrado() - polN[2].getGrado();
            double coe = p.getTermino().getC()/pp.getTermino().getC();
            Termino t = new Termino(exp,coe);
            Nodo n = new Nodo(t);
            CA.setLiga(n);
            CA=n;
            PolinomioListaSimpleConCabeza ult = new  PolinomioListaSimpleConCabeza();
            Nodo Cu = ult.getCabeza();
            Nodo m=CA;
            Cu.setLiga(m);
            PolinomioListaSimpleConCabeza multiplicacion = multiplicar(ult,polN[2]);
            PolinomioListaSimpleConCabeza cambioSigno = CambiarSigno(multiplicacion);
            dividendo = dividendo.sumar(cambioSigno);
            //dividendo = dividendo.sumar(CambiarSigno(multiplicar(polCociente,polN[2])));
            if(dividendo.getCabeza().getLiga()!=null){
              ordenar(dividendo);
              p=dividendo.getCabeza().getLiga();
            }
          }
         }else{
           System.out.println("El dividendo debe ser de mayor grado");
       }
          return polCociente;
     }
         
     public PolinomioListaSimpleConCabeza multiplicar(int p1, int p2 ){
         PolinomioListaSimpleConCabeza polC= new PolinomioListaSimpleConCabeza();
         polN[p1]= simplificar(polN[p1]);
         polN[p2]=simplificar(polN[p2]);
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
         polC = simplificar(polC);
         polC = ordenar(polC);
         return polC;
     }
     
       public PolinomioListaSimpleConCabeza CambiarSigno(PolinomioListaSimpleConCabeza p1){
         PolinomioListaSimpleConCabeza polC= new PolinomioListaSimpleConCabeza();
         Nodo pol1=p1.getCabeza().getLiga();
         Nodo pol3 = polC.getCabeza();

         double coeficiente1,coeficiente2;
         int exponente1,exponente2;
         
         while(!(p1.finRecorrido(pol1))){
             
            coeficiente1=pol1.getTermino().getC();
            exponente1=pol1.getTermino().getE();
            coeficiente2=-1;
            exponente2=0;
            
             Termino t = new Termino(exponente1+exponente2, coeficiente1*coeficiente2);
             Nodo n = new Nodo(t);
             pol3.setLiga(n);
             pol3 = n;
             pol1=pol1.getLiga();
         }
         return polC;
     }
     
    public PolinomioListaSimpleConCabeza multiplicar(PolinomioListaSimpleConCabeza p1, PolinomioListaSimpleConCabeza p2 ){
         PolinomioListaSimpleConCabeza polC= new PolinomioListaSimpleConCabeza();
         p2=simplificar(p2);
         Nodo pol1=p1.getCabeza().getLiga();
         Nodo pol2=p2.getCabeza().getLiga();
         Nodo pol3 = polC.getCabeza();

         double coeficiente1,coeficiente2;
         int exponente1,exponente2;
         
         while(!(p1.finRecorrido(pol1))){
             while(!(p2.finRecorrido(pol2)) ){
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
         pol2=p2.getCabeza().getLiga();

         }
         polC = simplificar(polC);
         polC = ordenar(polC);
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
     
     public boolean borrar(){
         if(Fichero.exists()){
          return Fichero.delete();
         }else
        return false;
     }
     
}
     

