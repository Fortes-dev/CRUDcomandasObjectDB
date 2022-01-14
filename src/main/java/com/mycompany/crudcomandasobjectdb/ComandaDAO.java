/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandasobjectdb;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.Pedido;
import models.Producto;

/**
 *
 * @author medin
 */
public class ComandaDAO {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("comandas.odb");
    }

    public List<Producto> showCarta() {
        em = emf.createEntityManager();
        TypedQuery<Producto> tq = em.createQuery(Constants.LISTCARTA, Producto.class);
        var carta = tq.getResultList();

        em.close();

        return carta;
    }

    public List<Pedido> showPedidos() {
        em = emf.createEntityManager();
        TypedQuery<Pedido> tq = em.createQuery(Constants.LISTPEDIDOS, Pedido.class);

        var pedidos = tq.getResultList();

        em.close();

        return pedidos;
    }

    public List<Pedido> showPedidosHoy() {
        var fecha = fechaHoy();

        em = emf.createEntityManager();
        TypedQuery<Pedido> tq = em.createQuery(Constants.PENDIENTEHOY, Pedido.class);
        tq.setParameter("fecha", fecha);
        var pedidosPendientes = tq.getResultList();

        em.close();

        return pedidosPendientes;
    }

    public void save(Long id) {

        try {

            em = emf.createEntityManager();

            var pedido = new Pedido();
            var producto = getProduct(id, em);

            pedido.setFecha(fechaHoy());
            pedido.setProducto(producto);
            pedido.setPrecio(producto.getPrecio());
            pedido.setPendiente("si");
            pedido.setRecogido("no");

            System.out.println("Ha pedido: " + producto);

            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
            System.out.println("\nHa realizado el pedido con el producto: " + producto);

        } catch (Exception e) {
            System.out.println("\nProducto no existente");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {

        try {
            em = emf.createEntityManager();

            var pedido = getPedido(id, em);

            em.getTransaction().begin();
            em.remove(pedido);
            em.getTransaction().commit();
            System.out.println("\nHa eliminado el pedido: " + pedido);
        } catch (Exception e) {
            System.out.println("\nPedido no existente");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void recoger(Long id) {

        try {
            em = emf.createEntityManager();

            var pedido = getPedido(id, em);

            em.getTransaction().begin();
            pedido.setPendiente("no");
            pedido.setRecogido("si");
            em.getTransaction().commit();
            System.out.println("\nHa recogido el pedido: " + pedido);

        } catch (Exception e) {
            System.out.println("\nPedido no existente");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Producto getProduct(Long id, EntityManager em) {

        TypedQuery<Producto> tq = em.createQuery(Constants.PRODUCTOID, Producto.class);

        tq.setParameter("id", id);

        var producto = tq.getSingleResult();

        return producto;

    }

    public Pedido getPedido(Long id, EntityManager em) {

        TypedQuery<Pedido> tq = em.createQuery(Constants.PEDIDOID, Pedido.class);

        tq.setParameter("id", id);

        var pedido = tq.getSingleResult();

        return pedido;

    }

    private Date fechaHoy() {

        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date date = new java.sql.Date(lnMilisegundos);

        return date;
    }

}
