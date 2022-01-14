/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandasobjectdb;

/**
 *
 * @author medin
 */
public class Constants {

    public static final String LISTCARTA = "SELECT pr FROM Producto pr";
    public static final String LISTPEDIDOS = "SELECT p FROM Pedido p";
    public static final String PRODUCTOID = "SELECT pr FROM Producto pr where pr.id=:id";
    public static final String PEDIDOID = "SELECT p FROM Pedido p where p.id=:id";
    public static final String PENDIENTEHOY = "SELECT p FROM Pedido p where p.pendiente='si' and p.fecha=:fecha";
    
}
