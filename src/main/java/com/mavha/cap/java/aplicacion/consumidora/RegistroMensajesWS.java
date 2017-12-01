/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cap.java.aplicacion.consumidora;

import com.mavha.cap.java.aplicacion.consumidora.modelo.Pedido;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author martdominguez
 */

@Singleton
@ServerEndpoint("/mensajes")
public class RegistroMensajesWS {

    private static Integer usuario = 1;
    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());

    @PostConstruct
    public void init(){
                System.out.print("WS POST");

    }
    
    public void onAnyDocumentEvent(@Observes Pedido pedido) {
                System.out.print("OBSERVADOR RECIBE PEDIDO");
        System.out.print(pedido.toString());

        for (Session s : sessions) {
            try {
                if (s.isOpen()) {
                    s.getBasicRemote().sendText("Pedido recibido: " + pedido.toString());
                }
            } catch (IOException ex) {
                Logger.getLogger(RegistroMensajesWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.print("OBSERVADOR RECIBE PEDIDO");
        System.out.print(pedido.toString());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig c) {
        sessions.add(session);
    }

    @OnMessage
    public String onMessage(String message, Session sesion) {
                        System.out.print("cliente manda mensaje");
        System.out.print(message);
        for (Session sAbierta : sesion.getOpenSessions()) {
            if (sAbierta.isOpen())  try {
                sAbierta.getBasicRemote().sendText("Ping..."+message+ "__" + sesion.getId());
            } catch (IOException ex) {
                Logger.getLogger(RegistroMensajesWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
