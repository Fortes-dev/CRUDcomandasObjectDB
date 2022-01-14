/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandasobjectdb;

import java.util.Scanner;

/**
 *
 * @author medin
 */
public class Main {

    private static ComandaDAO dao = new ComandaDAO();

    public static void main(String[] args) {

        System.out.println("\n----------------------------------------\nBienvenido a su app de Comandas");
        menu();

    }

    /**
     * Método void para mostrar un menu de manera recursiva
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("\n\n----------------------------------------\nSeleccione la acción que desea realizar: \n----------------------------------------");
        System.out.println("1. Crear un nuevo pedido.\n"
                + "2. Eliminar un pedido existente.\n"
                + "3. Marcar pedido como recogido.\n"
                + "4. Mostrar pedidos pendientes de hoy.\n"
                + "5. Mostrar carta.\n"
                + "6. Mostrar historial pedidos.\n"
                + "7. Salir.\n----------------------------------------");

        opcion = sc.nextInt();

        switch (opcion) {

            case 1 -> {

                dao.showCarta().forEach(c -> System.out.println(c));
                System.out.println("\nSeleccione el id del producto que desea pedir: ");
                Long id = sc.nextLong();
                dao.save(id);
                menu();

            }

            case 2 -> {

                dao.showPedidos().forEach(p -> System.out.println(p));
                System.out.println("\nSeleccione el id del pedido a eliminar: ");
                Long id = sc.nextLong();
                dao.delete(id);
                menu();
            }

            case 3 -> {

                dao.showPedidosHoy().forEach(p -> System.out.println(p));
                System.out.println("\nSeleccione el id del pedido a recoger: ");
                Long id = sc.nextLong();
                dao.recoger(id);
                menu();
            }

            case 4 -> {

                System.out.println("\nLas comandas pendientes de hoy son: \n");
                dao.showPedidosHoy().forEach(p -> System.out.println(p));
                menu();
            }

            case 5 -> {

                System.out.println("\nLa carta: \n");
                dao.showCarta().forEach(c -> System.out.println(c));
                menu();

            }

            case 6 -> {

                System.out.println("\nHistórico de pedidos: \n");
                dao.showPedidos().forEach(c -> System.out.println(c));
                menu();

            }
            
            case 7 -> {
                                                
                System.out.println("https://github.com/Fortes-dev/CRUDcomandasHibernate.git");
                System.exit(0);
                
            }

            default -> {
                menu();
            }
        }
    }

}
