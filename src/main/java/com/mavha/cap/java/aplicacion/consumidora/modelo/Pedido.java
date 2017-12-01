/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cap.java.aplicacion.consumidora.modelo;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author martdominguez
 */

@XmlRootElement
public class Pedido {
    private Integer codigo;
    private Date fecha;
    private List<DetallePedido> detalle;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetallePedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetallePedido> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Pedido{" + "codigo=" + codigo + ", fecha=" + fecha + ", detalle=" + detalle + '}';
    }
    
    
}
