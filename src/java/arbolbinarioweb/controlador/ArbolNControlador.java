/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.excepciones.ArbolNException;
import arbolbinarioweb.controlador.util.JsfUtil;
import arboln2.modelo.ArbolN2;
import arboln2.modelo.Ciudad;
import arboln2.modelo.Cliente;
import arboln2.modelo.NodoN2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author seba3
 */
@Named(value = "arbolNControlador")
@SessionScoped
public class ArbolNControlador implements Serializable{
    
    private ArbolN2 arbol = new ArbolN2();
    private String dato;
    private DefaultDiagramModel model;
    private Cliente cliente = new Cliente();
    private List<Ciudad> ciudad;
    
    private boolean verRegistrar = false;

    public ArbolN2 getArbol() {
        return arbol;
    }

    public void setArbol(ArbolN2 arbol) {
        this.arbol = arbol;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isVerRegistrar() {
        return verRegistrar;
    }

    public void setVerRegistrar(boolean verRegistrar) {
        this.verRegistrar = verRegistrar;
    }

    public List<Ciudad> getCiudad() {
        return ciudad;
    }

    public void setCiudad(List<Ciudad> ciudad) {
        this.ciudad = ciudad;
    }
    
    
    public ArbolNControlador() {

    }
    
    /*
    @PostConstruct
    public void iniciar() {
        llenarCiudades();
        try {
            cargarArbol();
        } catch (ArbolNException ex) {
            Logger.getLogger(ArbolNControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        pintarArbol();
    }
    
    private void llenarCiudades() {
        ciudad = new ArrayList<>();
        ciudad.add(new Ciudad((short) 1, "Maniales"));
        ciudad.add(new Ciudad((short) 2, "Pereira"));
        ciudad.add(new Ciudad((short) 3, "Bogota"));

    }
    
    public void cargarArbol() throws ArbolNException{

        //Algoritmo
        arbol.insertarNodo(new Cliente(0, "Mi Tienda", null, null, null));
        List<Cliente> padres = new ArrayList<Cliente>();
        padres.add(arbol.getRaiz().getDato());
    }
    */
    public void habilitarRegistrar() {
        verRegistrar = true;
        cliente = new Cliente();
    }
    
    public void guardarCelular() throws ArbolNException {

        arbol.insertarNodo(cliente, dato);
        cliente = new Cliente();
        verRegistrar = false;
        pintarArbol();
    }

    public void cancelarGuardado() {
        verRegistrar = false;

    }
    
public void pintarArbol() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);

        pintarArbol(arbol.getRaiz(), model, null, 30, 0);
    }

    private void pintarArbol(NodoN2 reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");
            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));
            }

            model.addElement(elementHijo);
            for (int i = 0; i < reco.getHijos().size(); i++) {
                pintarArbol(reco.getHijos().get(i), model, elementHijo,x - reco.getHijos().size() - 3, y + 5);
                x += 10;

            }

        }
    }
    
    public DiagramModel getModel() {
        return model;
    }
}
