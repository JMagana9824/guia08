/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.CategoriaFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Categoria;

/**
 *
 * @author jacque
 */
@Path("Categoria")
public class categoriaRest implements Serializable{
    @EJB
    private CategoriaFacadeLocal catFL;
    
    //findAll
    /**
     * Este método encontrará todas las categorias de la DB
     * @return Una lista con las categorias
     */
   @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findAll(){
            try {
                 if(catFL!=null){
                return catFL.findAll();
            }
            } catch (Exception e) {
        }
        return Collections.EMPTY_LIST; 
    }
    
    //findbyRange
    /**
     * Este método encontrará todas las categorias de la DB segun un rango
     * @param primero este es el limite inferior del rango de busqueda
     * @param tamanio este es el limite superior del rango de busqueda
     * @return debolverá las categorias según el rango establecido
     */
    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findRange(
            @DefaultValue("0") @QueryParam("first")int primero,
            @DefaultValue("5") @QueryParam("pagesize")int tamanio){
        List salida =null;
        try {
            if (catFL!=null) {
          salida=catFL.findRange(primero,tamanio);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }finally{
            if(salida==null){
                salida=new ArrayList();
            }
        }
        return salida;
    }
    
     /**
      * Este método encontrará las categorias de la DB de acuerdo a un ID
      * @param id recibirá un entero de parámetro
      * @return debolverá la categoria según el id establecido
      */
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
     public Categoria findID(@PathParam("id") Integer id){
            try {
                 if(catFL!=null){
                return catFL.find(id);
            }
            } catch (Exception e) {
        }
        return new Categoria(); 
     }
    }
   
   

