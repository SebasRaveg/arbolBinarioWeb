/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinarioespecial.modelo.Nodo;
import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinarioespecial.modelo.ArbolBinario;
import arbolbinarioespecial.modelo.Dato;
import arbolbinarioweb.controlador.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author seba3
 */
@Named(value = "arbolSumaControlador")
@SessionScoped
public class ArbolSumaControlador implements Serializable {
    
    private int numero;
    private ArbolBinario arbol;
    private DefaultDiagramModel model;
    private String textoHeader;
            
    public ArbolSumaControlador() {
        
    }
    
    @PostConstruct
    private void inicializar(){
        arbol = new ArbolBinario();
        textoHeader = "Arbol Sumatoria";
    }

    public String getTextoHeader() {
        return textoHeader;
    }

    public void setTextoHeader(String textoHeader) {
        this.textoHeader = textoHeader;
    }
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }
    
    public void llenarArbolsumas(arbolbinario.modelo.ArbolBinario abb) throws ArbolBinarioException
    {
        //Recorrido del arbol abb y llenar mi arbol sumas
        /*arbol = new ArbolBinario();
        abb.preOrden().forEach((l) -> {
        int i = (Integer) l;
            try {
                arbol.adicionarNodo(new Dato(i), arbol.getRaiz());
            } catch (ArbolBinarioException ex) {
                Logger.getLogger(ArbolSumaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        arbol = new ArbolBinario();
        recorrerAbbPreOrden(abb.getRaiz());
        pintarArbol();
    }
    
    private void recorrerAbbPreOrden(arbolbinario.modelo.Nodo temp){
        if(temp != null){
            try {
                arbol.adicionarNodo(new Dato(temp.getDato()), arbol.getRaiz());
                recorrerAbbPreOrden(temp.getIzquierda());
                recorrerAbbPreOrden(temp.getDerecha());
            } catch (ArbolBinarioException ex) {
                JsfUtil.addErrorMessage(ex.getMessage());
            }
        }
    }
    
    public void adicionarNodo(){
        try {
            arbol.adicionarNodo(new Dato(numero), arbol.getRaiz());
            pintarArbol();
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
     public void adicionarNodo(int parametro)
    {
        try {
            arbol.adicionarNodo(new Dato(parametro), arbol.getRaiz());
            pintarArbol();
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
     
    
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
        
        System.out.println(id.replaceAll("frmArbolSuma:diagrama-", ""));
        
        //Short.valueOf(id.replaceAll("frmMotociclista:diagrama-", ""));
    }
    
    public void pintarArbol() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:6}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbol(arbol.getRaiz(), model, null, 20, 0);

    }

    private void pintarArbol(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato().getSuma() + "       -Dato" + reco.getDato().getNumero());
            elementHijo.setId(String.valueOf(reco.getDato().getNumero()));

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbol(reco.getIzquierda(), model, elementHijo, x - 10, y + 5);
            pintarArbol(reco.getDerecha(), model, elementHijo, x + 10, y + 5);
        }
    }
    
}
    

