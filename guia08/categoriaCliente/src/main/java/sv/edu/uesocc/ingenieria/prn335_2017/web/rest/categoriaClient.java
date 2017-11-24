/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Categoria;

/**
 * Jersey REST client generated for REST resource:categoriaRest [Categoria]<br>
 * USAGE:
 * <pre>
 *        categoriaClient client = new categoriaClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author usuario
 */
@Named
@ViewScoped
public class categoriaClient implements Serializable {

    private WebTarget target;
    private Client client;
    List<Categoria> salida1;
    
    /**
     * Este es el constructor donde se instancia el cliente
     */

    public categoriaClient() {
        try {
            client = ClientBuilder.newClient();
            target = client.target("http://localhost:8080/guia07-1.0-SNAPSHOT/webresources/Categoria");
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /**
     * Este metodo busca todas las categorias registradas
     * @return 
     */

    public List findAll() {
        List<Categoria> salida = null;
        try {
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Categoria>>() {});
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (salida == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }
    
   

    @PostConstruct
    /**
     * este metodo llena a la lista con los registros encontrados
     */
    public void init() {
        salida1= findAll();
    }
    
    //getters y setters de la lista

    public List<Categoria> getSalida() {
        return salida1;
    }

    public void setSalida(List<Categoria> salida) {
        this.salida1 = salida1;
    }

    public void close() {
        client.close();
    }

}
