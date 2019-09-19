
package com.aeropuerto.carrera;


/**
 * Clase principal donde inicia el programa
 * @author jeison gaona
 */
public class Main {
    
    /**
     * Funcion que inicia el hilo principal del programa
     */
     public static void main(String[] args) {
         System.out.println("\u001B[34m Equipo 1 azul");
         System.out.println("\u001B[35m Equipo 2 violeta");
         System.out.println("\u001B[33m Equipo 3 amarillo");

         Principal princi=new Principal();
         princi.start();
    }
}
