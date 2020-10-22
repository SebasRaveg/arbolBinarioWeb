/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.ArbolBinario;
import arbolbinario.modelo.Nodo;
import arbolbinario.modelo.excepciones.ArbolBinarioException;
import arbolbinarioweb.controlador.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.Connector;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author carloaiza
 */
@Named(value = "arbolBinarioControlador")
@SessionScoped
public class ArbolBinarioControlador implements Serializable {

    private DefaultDiagramModel model;
    private DefaultDiagramModel modelArbol2;

    private ArbolBinario arbol = new ArbolBinario();
    private int dato;
    private boolean verInOrden = false;
    private boolean verPreOrden = false;
    private boolean verPosOrden = false;
    private boolean verPorNivel = false;
    private boolean verNivelesOrdenados = false;
    private boolean verHojas = false;
    private boolean verRamaMayor = false;
   
    private String datoscsv = "18,15,13,17,8,14,-8,10,59,28,80,78,90";
    private int terminado;
    private ArbolBinario arbolTerminados = new ArbolBinario();
    
    private int datoSumar;
    private String textoEnviar;
    private int datoPromediar;
    
    public String getTextoEnviar() {
        return textoEnviar;
    }

    public void setTextoEnviar(String textoEnviar) {
        this.textoEnviar = textoEnviar;
    }

    public int getDatoPromediar() {
        return datoPromediar;
    }

    public void setDatoPromediar(int datoPromediar) {
        this.datoPromediar = datoPromediar;
    }

    public int getDatoSumar() {
        return datoSumar;
    }

    public void setDatoSumar(int datoSumar) {
        this.datoSumar = datoSumar;
    }

    public ArbolBinario getArbolTerminados() {
        return arbolTerminados;
    }

    public void setArbolTerminados(ArbolBinario arbolTerminados) {
        this.arbolTerminados = arbolTerminados;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }
    
    public DefaultDiagramModel getModelArbol2() {
        return modelArbol2;
    }

    public void setModelArbol2(DefaultDiagramModel modelArbol2) {
        this.modelArbol2 = modelArbol2;
    }

    public String getDatoscsv() {
        return datoscsv;
    }

    public void setDatoscsv(String datoscsv) {
        this.datoscsv = datoscsv;
    }

    public boolean isVerInOrden() {
        return verInOrden;
    }

    public void setVerInOrden(boolean verInOrden) {
        this.verInOrden = verInOrden;
    }
    
    public boolean isVerPreOrden() {
        return verPreOrden;
    }

    public void setVerPreOrden(boolean verPreOrden) {
        this.verPreOrden = verPreOrden;
    }
    
    public boolean isVerPosOrden() {
        return verPosOrden;
    }

    public void setVerPosOrden(boolean verPosOrden) {
        this.verPosOrden = verPosOrden;
    }
    
    public boolean isVerPorNivel() {
        return verPorNivel;
    }

    public void setVerPorNivel(boolean verPorNivel) {
        this.verPorNivel = verPorNivel;
    }
    
    public boolean isVerNivelesOrdenados() {
        return verNivelesOrdenados;
    }

    public void setVerNivelesOrdenados(boolean verNivelesOrdenados) {
        this.verNivelesOrdenados = verNivelesOrdenados;
    }
    
    public boolean isVerHojas() {
        return verHojas;
    }

    public void setVerHojas(boolean verHojas) {
        this.verHojas = verHojas;
    }
    
    public boolean isVerRamaMayor() {
        return verRamaMayor;
    }

    public void setVerRamaMayor(boolean verRamaMayor) {
        this.verRamaMayor = verRamaMayor;
    }
    
    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public ArbolBinario getArbol() {
        return arbol;
    }

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
    }

    /**
     * Creates a new instance of ArbolBinarioControlador
     */
    public ArbolBinarioControlador() {

    }

    public void adicionarNodo() {
        try {
            arbol.adicionarNodo(dato, arbol.getRaiz());
            JsfUtil.addSuccessMessage("El dato ha sido adicionado");
            dato = 0;
            pintarArbol();

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarMultipicarValor() {
        try {
            arbol.isLleno();
            arbol.Multiplicar(dato);
            pintarArbol();
            JsfUtil.addSuccessMessage("Los valores del arbol han sido multiplicados por " + dato);
            dato=0;
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarborrarPorNivel() {
        try {
            arbol.isLleno();
            arbol.borrarNivel(dato);
            pintarArbol();
            JsfUtil.addSuccessMessage("Se han eliminado los niveles: "+ dato);
            dato=0;
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    
    public void buscarNodo(){
        try {
            Nodo nodoEncontrado = arbol.buscarNodo(dato, arbol.getRaiz());
                arbol.isLleno();
                if (nodoEncontrado!= null)
                {
                    JsfUtil.addSuccessMessage("El dato "+dato+" si se encuentra en el arbol");
                }
                dato = 0;
        } catch (ArbolBinarioException ex) {
             JsfUtil.addErrorMessage("El dato "+dato+" no se encuentra en el arbol") ;
        }
    }
    
    public void darPadre(Integer hijo) {
        hijo = dato;
        if (this.arbol.getRaiz().getDato() == (hijo)) {
            JsfUtil.addSuccessMessage("La raiz no tiene padre");
            dato = 0;
        }
        Integer padre = this.arbol.padre(hijo);
        if (padre == null) {
            JsfUtil.addSuccessMessage("No existe el Dato: " + hijo.toString());
            dato = 0;
        }
        JsfUtil.addSuccessMessage("El padre de: " + hijo + "\n es : " + padre.toString());
        dato = 0;
    }
    
    public void borrarNodo() {
            Nodo NodoaBorrar = arbol.borrarNodo(arbol.getRaiz(), dato);
        try {
            arbol.isLleno();
            
            if (NodoaBorrar== null) 
             {
              JsfUtil.addSuccessMessage("El dato "+dato+" no existe");
             }
             else 
             {
              JsfUtil.addErrorMessage("El dato "+dato+" fue borrado");
              pintarArbol();
             };
            dato = 0;
            
            

        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarInOrden() {
        try {
            arbol.isLleno();
            verInOrden = true;
            JsfUtil.addSuccessMessage("Recorrido InOrden");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPreOrden() {
        try {
            arbol.isLleno();
            verPreOrden = true;
            JsfUtil.addSuccessMessage("Recorrido PreOrden");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPosOrden() {
        try {
            arbol.isLleno();
            verPosOrden = true;
            JsfUtil.addSuccessMessage("Recorrido PosOrden");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPorNivel() {
        try {
            arbol.isLleno();
            verPorNivel = true;
            JsfUtil.addSuccessMessage("Imprimir Por Niveles");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarNivelesOrdenados() {
        try {
            arbol.isLleno();
            arbol.alturaArbol();
            verNivelesOrdenados = true;
            JsfUtil.addSuccessMessage("Imprimir por Niveles en Orden");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarHojas() {
        try {
            arbol.isLleno();
            verHojas = true;
            JsfUtil.addSuccessMessage("Hojas del Arbol");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarBalance() {
        try {
            arbol.isLleno();
            String balance = arbol.imprimirBalance();
            JsfUtil.addSuccessMessage(balance);          
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarObtenerRamaMayor() {
        try {
            arbol.isLleno();
            verRamaMayor = true;
            JsfUtil.addSuccessMessage("Rama(s) con mas valores");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarCambiarValor() {
        try {
            arbol.isLleno();
            arbol.cambiar();
            pintarArbol();
            JsfUtil.addSuccessMessage("Se cambiaron los valores del arbol");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
       
    public void habilitarBorrarMenor() {
        try {
            arbol.isLleno();
            String borrado = arbol.borrarMenor();
            pintarArbol();
            JsfUtil.addSuccessMessage("Se ha eliminado el dato: " + borrado);
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarBorrarMayor() {
        try {
            arbol.isLleno();
            String borrado = arbol.borrarMayor();
            pintarArbol();
            JsfUtil.addSuccessMessage("Se ha eliminado el dato: " + borrado);
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPodar() {
        try {
            arbol.isLleno();
            arbol.podar();
            pintarArbol();
            JsfUtil.addSuccessMessage("Las Hojas del Arbol, Fueron eliminadas");
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void habilitarPromediar()
    {
        if(arbol.getRaiz()!=null)
        {
            try {
                Nodo nodoencontrado = arbol.buscarNodo(datoPromediar, arbol.getRaiz());
                if(nodoencontrado != null)
                {
                    //Encontró un arbol
                    float cont= arbol.contarNodos(nodoencontrado);
                    float suma = arbol.sumarNodos(nodoencontrado);
                    JsfUtil.addSuccessMessage("El árbol tiene "+ cont+" elementos,\n"
                            + " suman "+suma+"\n, Promedian "+ (suma/cont));
                }
                
            } catch (ArbolBinarioException ex) {
                JsfUtil.addErrorMessage(ex.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("No puede promediar un árbol vacío");
        }    
    }
    
    public void sumarNodo(){
        try {
            Nodo nodoEncontrado= arbol.buscarNodo(datoSumar, arbol.getRaiz());
            JsfUtil.addSuccessMessage("El nodo suma:" + arbol.sumarNodos(nodoEncontrado));
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
        
    }
    
    public String irBeanSuma() throws ArbolBinarioException
    {
        //Cálculos, bds, enviar correos
        ArbolSumaControlador beanSuma= (ArbolSumaControlador) 
                JsfUtil.getManagedBean("arbolSumaControlador");
        
        beanSuma.setTextoHeader(textoEnviar);
        
        //beanSuma.adicionarNodo(Integer.parseInt(textoEnviar));
        beanSuma.llenarArbolsumas(arbol);
        
        return "abbsuma";
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public void pintarArbol() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:6}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbol(arbol.getRaiz(), model, null, 50, 0);

    }

    private void pintarArbol(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

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

            pintarArbol(reco.getIzquierda(), model, elementHijo, x - 10, y + 5);
            pintarArbol(reco.getDerecha(), model, elementHijo, x + 10, y + 5);
        }
    }

    public void extraerDatos() {
        try {
            arbol.setRaiz(null);
            arbol.llenarArbol(datoscsv);
            pintarArbol();
            datoscsv = "";
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage("Los datos ingresados no tienen el formato separado por comas");
        }
    }
    
    public void buscarTerminadosEn() {
        for (Element ele : model.getElements()) {
            ele.setStyleClass("ui-diagram-element");
            int numTerm = Integer.parseInt(ele.getData().toString());
            if (numTerm < 0) {
                numTerm *= -1;
            }
            if (numTerm % 10 == terminado) {
                ele.setStyleClass("ui-diagram-element-busc");
            }
        }
    }

    public void encontrarTerminadosEn() {
        try {
            arbolTerminados = new ArbolBinario();
            encontrarTerminadosEn(arbol.getRaiz());
            pintarArbolTerminados();
        } catch (ArbolBinarioException ex) {
            JsfUtil.addErrorMessage("Ocurrio un error generando el árbol de terminados" + ex);
        }
    }

    private void encontrarTerminadosEn(Nodo reco) throws ArbolBinarioException {
        if (reco != null) {
            int numTerm= reco.getDato();
            if(numTerm<0)
            {
                numTerm *=-1;
            }
            if(numTerm%10==terminado)
            {
                arbolTerminados.adicionarNodo(reco.getDato(), arbolTerminados.getRaiz());
            }
            encontrarTerminadosEn(reco.getIzquierda());
            encontrarTerminadosEn(reco.getDerecha());
        }
    }

    public void pintarArbolTerminados() {

        modelArbol2 = new DefaultDiagramModel();
        modelArbol2.setMaxConnections(-1);
        modelArbol2.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        modelArbol2.setDefaultConnector(connector);
        pintarArbolTerminados(arbolTerminados.getRaiz(), modelArbol2, null, 30, 0);

    }

    private void pintarArbolTerminados(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

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

            pintarArbolTerminados(reco.getIzquierda(), model, elementHijo, x - 5, y + 5);
            pintarArbolTerminados(reco.getDerecha(), model, elementHijo, x + 5, y + 5);
        }
    }

}
