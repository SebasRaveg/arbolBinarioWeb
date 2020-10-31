/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinarioweb.controlador.util.JsfUtil;
import arboln2.modelo.ArbolN2;
import arboln2.modelo.Cliente;
import arboln2.modelo.NodoN2;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;

/**
 *
 * @author seba3
 */
@Named(value = "arbolNControlador")
@SessionScoped
public class ArbolNControlador implements Serializable{
    
    private ArbolN2 arbol = new ArbolN2();
    private int dato;

    public ArbolN2 getArbol() {
        return arbol;
    }

    public void setArbol(ArbolN2 arbol) {
        this.arbol = arbol;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
    
    public ArbolNControlador() {

    }
    
    public void insertarNodo() {
        arbol.insertarNodo(dato, identificacionPadre, arbol.getRaiz());
        JsfUtil.addSuccessMessage("El Cliente ha sido adicionado");
        pintarArbol();
    }
    
    public void pintarArbol() {

    }
}
