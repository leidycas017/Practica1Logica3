/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package matricesvalidades;

import matriz.util.NodoDoble;
import matriz.util.Tripleta;

public class MatrizEnListaLigadaForma1 {

    NodoDoble nodoCabezaMatriz;

    /**
     * Constructor de la matriz sin elementos
     *
     * @param numeroFilas cantidad de filas de la matriz
     * @param numeroColumnas cantidad de columnas de la matriz
     */
    MatrizEnListaLigadaForma1(int numeroFilas, int numeroColumnas) {
        construyeNodosCabeza(numeroFilas, numeroColumnas);
    }
    
    /**
     * Metodo para comparar dos Matrices
     * Se recorren ambas matrices por filas
     * recorre los nodos cabezas de filas-columnas y sus respectivos nodos asociados por filas
     * con p (para la matriz 1) y q (para la matriz 2)
     * @param matrizB segunda matriz para hacer la comparación
     * @return valor del contador de diferencias
     * @author: Leidy Castaño Castaño
     * @version: 04/03/2020
     * @see <a href = "https://github.com/leidycas017/Practica1Logica3.git" /> Url
     */   
    public int Comparar(MatrizEnListaLigadaForma1 matrizB){
        NodoDoble M1 = nodoCabezaMatriz;
        NodoDoble M2 = matrizB.getNodoCabezaMatriz();
        NodoDoble nodoCabRecorM1 = getLigaNodoCabeza(M1);
        NodoDoble nodoCabRecorM2 = matrizB.getLigaNodoCabeza(M2);
        NodoDoble p = nodoCabRecorM1.getLigaF();
        NodoDoble q = nodoCabRecorM2.getLigaF();
        int cont = 0;
        
        for(int i=1;i<=M1.getT().getF();i++){
            while(nodoCabRecorM1!=p && nodoCabRecorM2!=q){
                int columP = p.getT().getC();
                int columQ = q.getT().getC();
                if(columP==columQ){
                    double v1 = (double)p.getT().getV();
                    double v2 = (double)q.getT().getV();
                    if(isDiferent(v1, v2)){
                        cont++;
                    }
                    p = p.getLigaF();
                    q = q.getLigaF();
                }else{
                   if(p.getT().getC() < q.getT().getC()){
                       double v2=0;
                       double v1=(double)p.getT().getV();
                       if(isDiferent(v1, v2)){
                           cont++;
                       }
                       p=p.getLigaF();
                   }else{
                       double v1=0;
                       double v2=(double)q.getT().getV();
                       if (isDiferent(v1, v2)) {
                           cont++;
                       }
                       q=q.getLigaF();
                   }
                }
                
            }//fin del primer while
            if(nodoCabRecorM1!=p){
                while(nodoCabRecorM1!=p){
                   double v2=0;
                   if(isDiferent(v2, (double)p.getT().getV())){
                       cont++;
                   }
                   p=p.getLigaF();
                }
            }
            if(nodoCabRecorM2!=q){
                while(nodoCabRecorM2!=q){
                   double v1=0;
                   if(isDiferent(v1, (double)q.getT().getV())){
                       cont++;
                   }
                   q=q.getLigaF();
                }
            }
            nodoCabRecorM1 = getLigaNodoCabeza(nodoCabRecorM1);
            nodoCabRecorM2 = matrizB.getLigaNodoCabeza(nodoCabRecorM2);
            p = nodoCabRecorM1.getLigaF();
            q = nodoCabRecorM2.getLigaF();
        }
        
       return cont;
    } 
    /**
     * Metodo para evaluar diferencia entre dos valores
     * 
     * @param v1 valor de la tripleta 1
     * @param v2 valor de la tripleta 2
     * @author: Leidy Castaño Castaño
     * @version: 04/03/2020
     * @see <a href = "https://github.com/leidycas017/Practica1Logica3.git" /> Url
     * @return valor booleano si cumple o no con la condición  5 ( + ó - ) de diferencia
     */ 
    public boolean isDiferent(double v1, double v2){
            double dif = v2-v1;
            if(dif <= -5.0 || dif >= 5.0){
                return true;
            }
            return false;
    }
    
    /**
     *
     * @param numeroFilas
     * @param numeroColumnas
     */
    private void construyeNodosCabeza(int numeroFilas, int numeroColumnas) {
        Tripleta tripletaConfiguracion = new Tripleta(numeroFilas, numeroColumnas, null);
        nodoCabezaMatriz = new NodoDoble(tripletaConfiguracion);

        // Depende de las f y c
        int max = (numeroFilas > numeroColumnas) ? numeroFilas : numeroColumnas;

        // Creo los nodos Cabeza de las listas de filas y columas
        // Estas a su vez hacen parte de la lista circular de nodos cabeza
        NodoDoble ultimo = nodoCabezaMatriz;
        for (int i = 1; i <= max; i++) {
            NodoDoble nuevoNodoRegistroCabeza = new NodoDoble(new Tripleta(i, i, null));
            // Estoy creando la referencia circular inicial para la lista de columnas(la oreja)
            nuevoNodoRegistroCabeza.setLigaC(nuevoNodoRegistroCabeza);
            // Estoy creando la referencia circular inicial para la lista de filas(la oreja)
            nuevoNodoRegistroCabeza.setLigaF(nuevoNodoRegistroCabeza);
            // Liga del ultimo con el nuevo
            setLigaNodoCabeza(ultimo, nuevoNodoRegistroCabeza);
            // Este es el nuevo ultimo
            ultimo = nuevoNodoRegistroCabeza;
        }
        // Establezco la referencia de la lista circular
        setLigaNodoCabeza(ultimo, nodoCabezaMatriz);
    }

    /**
     * Crea la liga en los nodos cabeza, se reutiliza el Object de la tripleta
     * del Nodo.
     *
     * @param a
     * @param b
     */
    private static void setLigaNodoCabeza(NodoDoble a, NodoDoble b) {
        a.getT().setV(b);
    }

    private NodoDoble getLigaNodoCabeza(NodoDoble a) {
        return (NodoDoble) a.getT().getV();
    }

    /**
     * Retorna el nodo cabeza de la matriz
     *
     * @return
     */
    public NodoDoble getNodoCabezaMatriz() {
        return nodoCabezaMatriz;
    }

    private MatrizEnListaLigadaForma1(NodoDoble nc) {
        this.nodoCabezaMatriz = nc;
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz
     *
     * @param fila fila donde se encuentra el dato
     * @param columna columnas donde se encuentra el dato
     * @param valor valor
     */
    public void setCelda(int fila, int columna, double valor) {
        Tripleta nuevoTripletaRegistro = new Tripleta(fila, columna, valor);
        setCelda(nuevoTripletaRegistro);
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz
     *
     * @param t
     */
    public void setCelda(Tripleta t) {

        // Creo el NodoDoble con los valores a ingresar
        NodoDoble nuevoNodoRegistro = new NodoDoble(t);

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        NodoDoble nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);

        // Buscar el nodo cabeza correspondiente a la Fila del registro que 
        // estamos insertando y cuando lo encuentra inserta el registro en la lista
        // de esa fila
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getF() == t.getF()) {
                // Eureka, encontre el Nodo cabeza de la fila
                conectaPorFilas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaMatriz);
        // Buscar el nodo cabeza correspondiente a la Columna del registro que
        // estamos insertando y cuando lo encuentra inserta el registro en la lista
        // por columna
        while (nodoCabezaDeRecorridoLocalizado != nodoCabezaMatriz && nodoCabezaDeRecorridoLocalizado != null) {
            if (nodoCabezaDeRecorridoLocalizado.getT().getC() == t.getC()) {
                conectaPorColumnas(nodoCabezaDeRecorridoLocalizado, nuevoNodoRegistro);
                break;
            }
            nodoCabezaDeRecorridoLocalizado = getLigaNodoCabeza(nodoCabezaDeRecorridoLocalizado);
        }
    }

    /**
     * Método para conectar un nuevo nodo por las filas
     *
     * @param nodoCabezaDeRecorridoLocalizado
     * @param nnuevo
     */
    private void conectaPorFilas(NodoDoble nodoCabezaDeRecorridoLocalizado, NodoDoble nuevoNodoRegistro) {
        NodoDoble nodoRecorridoEnLaFila = nodoCabezaDeRecorridoLocalizado.getLigaF();
        NodoDoble ultimoNodoDeFila = nodoCabezaDeRecorridoLocalizado;
        boolean siDebeInsertar = true;
        while (nodoRecorridoEnLaFila != null && nodoRecorridoEnLaFila != nodoCabezaDeRecorridoLocalizado) {
            int columnaNuevoRegistro = nuevoNodoRegistro.getT().getC();
            int columnaNodoRestroRecorrido = nodoRecorridoEnLaFila.getT().getC();
            if (columnaNuevoRegistro > columnaNodoRestroRecorrido) {
                ultimoNodoDeFila = nodoRecorridoEnLaFila;
                nodoRecorridoEnLaFila = nodoRecorridoEnLaFila.getLigaF();
            } else if (columnaNuevoRegistro == columnaNodoRestroRecorrido) {
                siDebeInsertar = false;
                nodoRecorridoEnLaFila.getT().setV(nuevoNodoRegistro.getT().getV());
                break;
            } else {
                break;
            }
        }
        if (siDebeInsertar) {
            nuevoNodoRegistro.setLigaF(nodoRecorridoEnLaFila);
            ultimoNodoDeFila.setLigaF(nuevoNodoRegistro);
        }
    }

    /**
     * Método para conectar un nuevo nodo por las columnas
     *
     * @param nodoCDeRecorrido
     * @param nnuevo
     */
    private void conectaPorColumnas(NodoDoble nodoCabezaDeRecorridoLocalizado, NodoDoble nuevoNodoRegistro) {
        NodoDoble nodoRecorridoEnLaColumna = nodoCabezaDeRecorridoLocalizado.getLigaC();
        NodoDoble ultimoNodoDeColumna = nodoCabezaDeRecorridoLocalizado;
        boolean siDebeInsertar = true;

        while (nodoRecorridoEnLaColumna != null && nodoRecorridoEnLaColumna != nodoCabezaDeRecorridoLocalizado) {
            int filaNuevoRegistro = nuevoNodoRegistro.getT().getF();
            int filaNodoRecorrido = nodoRecorridoEnLaColumna.getT().getF();

            if (filaNuevoRegistro > filaNodoRecorrido) {
                ultimoNodoDeColumna = nodoRecorridoEnLaColumna;
                nodoRecorridoEnLaColumna = nodoRecorridoEnLaColumna.getLigaC();
            } else if (filaNuevoRegistro == filaNodoRecorrido) {
                siDebeInsertar = false;
                nodoRecorridoEnLaColumna.getT().setV(nuevoNodoRegistro.getT().getV());
                break;
            } else {
                break;
            }
        }
        if (siDebeInsertar) {
            nuevoNodoRegistro.setLigaC(nodoRecorridoEnLaColumna);
            ultimoNodoDeColumna.setLigaC(nuevoNodoRegistro);
        }
    }
    


    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        // Obtengo la configuración de la matriz, fr y cr
        Tripleta configuracion = nodoCabezaMatriz.getT();
        int cantidadFilasMatriz = configuracion.getF();
        int cantidadColumnasMatriz = configuracion.getC();
        // Imprimir una línea con encabezado de las columnas
        cadena.append("\t");
        for (int i = 1; i <= cantidadColumnasMatriz; i++) {
            cadena.append(i + "\t");
        }
        cadena.append("\n");

        NodoDoble nodoRecorridoCabeza = getLigaNodoCabeza(nodoCabezaMatriz);

        // Recorrido por una matriz virtual m x n
        for (int fv = 1; fv <= cantidadFilasMatriz; fv++) {
            cadena.append(fv + "\t");
            if (nodoRecorridoCabeza != null && nodoRecorridoCabeza != nodoCabezaMatriz) {
                NodoDoble nodoRecorridoCeldas = nodoRecorridoCabeza.getLigaF();
                for (int cv = 1; cv <= cantidadColumnasMatriz; cv++) {
                    if (nodoRecorridoCeldas != null && nodoRecorridoCeldas != nodoRecorridoCabeza) {
                        Tripleta triMo = nodoRecorridoCeldas.getT();
                        int ft = triMo.getF();
                        int ct = triMo.getC();
                        if (fv == ft) {
                            if (cv < ct) {
                                cadena.append("0\t");
                            } else if (cv == ct) {
                                Object vt = triMo.getV();
                                if (vt != null) {
                                    cadena.append(vt + "\t");
                                } else {
                                    cadena.append("ERROR x COLUMNAS!!!!");
                                }
                                nodoRecorridoCeldas = nodoRecorridoCeldas.getLigaF();
                            }
                        } else {
                            cadena.append("ERROR x FILAS !!!!");
                        }
                    } else {
                        cadena.append("0\t");
                    }
                }
            }
            nodoRecorridoCabeza = getLigaNodoCabeza(nodoRecorridoCabeza);
            cadena.append("\n");
        }
        return cadena.toString();
    }

    public int getFilas() {
        return nodoCabezaMatriz.getT().getF();
    }

    /**
     * Busca el valor de una celda i,j de la matriz o 0 en caso contrario
     *
     * @param i
     * @param j
     * @return
     */
    public int getCelda(int i, int j) {
        int valor = 0;

        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza
        NodoDoble nCDeRecorrido = getLigaNodoCabeza(nodoCabezaMatriz);

        /**
         * Buscar el nodo cabeza correspondiente a la Fila del registro que
         * estamos buscando y cuando lo encuentra buscar el registro en la lista
         * de columnas de esa fila
         */
        while (nCDeRecorrido != nodoCabezaMatriz && nCDeRecorrido != null) {
            // Cuando localice la fila busco la columna
            if (nCDeRecorrido.getT().getF() == i) {
                NodoDoble nodoRecorrido = nCDeRecorrido.getLigaF();
                NodoDoble cabezaRecorrido = (NodoDoble) nCDeRecorrido;
                while (nodoRecorrido != null && nodoRecorrido != cabezaRecorrido) {
                    if (j > nodoRecorrido.getT().getC()) {
                        nodoRecorrido = nodoRecorrido.getLigaF();
                    } else {
                        // Cuando no es mayor valido si estoy en la columna
                        if (j == nodoRecorrido.getT().getC()) {
                            valor = (int) nodoRecorrido.getT().getV();
                        }
                        break;
                    }
                }
            }

            nCDeRecorrido = getLigaNodoCabeza(nCDeRecorrido);
        }

        return valor;

    }

    public int getColumnas() {
        return nodoCabezaMatriz.getT().getC();
    }

    public static NodoDoble getCopiaListaFila(NodoDoble nCDeRecorrido) {

        NodoDoble copiaFila = new NodoDoble(nCDeRecorrido.getT().clonar());
        copiaFila.setLigaF(copiaFila);
        NodoDoble ultimoNodoDeFilaCopia = (NodoDoble) copiaFila;

        NodoDoble nodoRecorrido = nCDeRecorrido.getLigaF();
        NodoDoble cabezaRecorrido = (NodoDoble) nCDeRecorrido;
        while (nodoRecorrido != null && nodoRecorrido != cabezaRecorrido) {
            Tripleta tripletaCopia = nodoRecorrido.getT().clonar();
            NodoDoble nuevoNodocopia = new NodoDoble(tripletaCopia);
            ultimoNodoDeFilaCopia.setLigaF(nuevoNodocopia);
            ultimoNodoDeFilaCopia = nuevoNodocopia;
            nodoRecorrido = nodoRecorrido.getLigaF();
        }
        return copiaFila;
    }


    public NodoDoble getFila(int i) {
        // Obtengo un nodo cabeza para recorrer la lista de nodos cabeza

        NodoDoble nCDeRecorrido = getLigaNodoCabeza(nodoCabezaMatriz);

        /**
         * Buscar el nodo cabeza correspondiente a la Fila del registro que
         * estamos buscando y cuando lo encuentra clonar
         */
        while (nCDeRecorrido != nodoCabezaMatriz && nCDeRecorrido != null) {
            // Cuando localice la fila 
            if (nCDeRecorrido.getT().getF() == i) {
                return nCDeRecorrido;
            }
        }
        return nCDeRecorrido;
    }

}
