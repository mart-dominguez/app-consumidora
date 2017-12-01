/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cap.java.aplicacion.consumidora;

import com.mavha.cap.java.aplicacion.consumidora.modelo.Pedido;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

/**
 *
 * @author martdominguez
 */

@ApplicationScoped
@Named("registroController")
public class Observador {
    
    private Date fechaBean;
    
    @PostConstruct
    public void init(){
        this.fechaBean = new Date();
    }
    
    /*public void onAnyDocumentEvent(@Observes Pedido pedido) {
        System.out.print("OBSERVADOR RECIBE PEDIDO");
        System.out.print(pedido.toString());
    }*/

    public Date getFechaBean() {
        return fechaBean;
    }

    public void setFechaBean(Date fechaBean) {
        this.fechaBean = fechaBean;
    }
    
    
}
