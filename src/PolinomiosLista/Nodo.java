/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PolinomiosLista;

import polinomios.util.Termino;

/**
 * Clase que representa un nodo simple en una lista ligada
 * @author alejandroescobar
 */
class Nodo {
    private Termino termino;
    private Nodo liga;

    Nodo(Termino t) {
        termino = t;
    }

    public Termino getTermino() {
        return termino;
    }

    public void setTermino(Termino termino) {
        this.termino = termino;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
    
    
}