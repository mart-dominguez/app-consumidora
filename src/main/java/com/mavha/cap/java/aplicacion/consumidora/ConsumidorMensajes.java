/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cap.java.aplicacion.consumidora;

import com.mavha.cap.java.aplicacion.consumidora.modelo.Pedido;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author martdominguez
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "cola1")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumidorMensajes implements MessageListener {
    
    public ConsumidorMensajes() {
        System.out.println("CREADO !!!!");        
    }
    
    @Inject
    Event<Pedido> eventoPedidoRecibido;
    
    @PostConstruct
    public void init2(){
            System.out.println("CREADO2 !!!!");        
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("==== LLEGO MENSAJE ==== ");
        try {                        
            if(message.getBooleanProperty("ES_PEDIDO")) this.leerMensajeComplejo(((TextMessage)message).getText());
            System.out.println("====>"+message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void leerMensajeComplejo(String texto){
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Pedido.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Pedido pedido = (Pedido) jaxbUnmarshaller.unmarshal(new StringReader(texto));
            System.out.println(pedido);
            eventoPedidoRecibido.fire(pedido);
            System.out.println("EVENTO LANZADO!!!");
        } catch (JAXBException ex) {
            Logger.getLogger(ConsumidorMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
