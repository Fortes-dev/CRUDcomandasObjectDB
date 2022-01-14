/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author medin Modelo de la tabla pedidos
 */
@Entity
public class Pedido implements Serializable {

    /**
     * Atributos que coinciden con las columnas de la tabla pedidos
     */
    @Id
    @GeneratedValue
    private Long id;


    private Date fecha;
    private double precio;
    private String pendiente;
    private String recogido;
    
    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    /**
     * Constructor con parámetros
     *
     * @param id
     * @param fecha
     * @param precio
     * @param pendiente
     * @param recogido
     */
    public Pedido(Long id, Date fecha, double precio, String pendiente, String recogido) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.pendiente = pendiente;
        this.recogido = recogido;
    }

    /**
     * Constructor vacio
     */
    public Pedido() {
    }

    /**
     * Getters y setters de los campos/atributos
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String isPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String isRecogido() {
        return recogido;
    }

    public void setRecogido(String recogido) {
        this.recogido = recogido;
    }

    /**
     * Método toString para mostar un pedido por consola.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", fecha=" + fecha + ", precio=" + precio + ", pendiente=" + pendiente + ", recogido=" + recogido + '}';
    }

}
