
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;


/**
 *Clase que inicia el hilo principal del programa
 * @author ivan lopez
 * @author duvan cañon
 */
public class Principal extends Thread{

    /**
     * Objeto que representa la estafeta del equipo A
     */
    Dato datoEquipo1=new Dato();
    
    /**
     * Objeto que representa la estafeta del equipo B
     */
    Dato datoEquipo2=new Dato();
     
    /**
     * Objeto que representa la estafeta del equipo C
     */
    Dato datoEquipo3=new Dato();

    /**
     * Mapa donde se guarda el recorrido del equipo A
     */
    Map<Integer,String> listaEquipoA=new HashMap<>();
    
    /**
     * Mapa donde se guarda el recorrido del equipo B
     */
    Map<Integer,String> listaEquipoB=new HashMap<>();
    
    /**
     * Mapa donde se guarda el recorrido del equipo C
     */
    Map<Integer,String> listaEquipoC=new HashMap<>();

    /**
     * Constructor de la clase
     */
    public Principal() {
        System.out.println("\u001B[34m Equipo 1 azul");
         System.out.println("\u001B[35m Equipo 2 violeta");
         System.out.println("\u001B[33m Equipo 3 amarillo");
        llenarEquipo1();
        llenarEquipo2();
        llenarEquipo3();
    }

    /**
     * Funcion donde inicia el hilo
     */
    @Override
    public void run() {
        inicioEquipos();
    }
    
    /**
     * Funcion que da el inicio a los equipos
     */
    public void inicioEquipos(){
        Corredor11 corredorUno1=new Corredor11(datoEquipo1,listaEquipoA);
        Corredor21 corredorDosA=new Corredor21(datoEquipo1, listaEquipoA);
        Corredor31 corredorTresA=new Corredor31(datoEquipo1, listaEquipoA);
        
        Corredor12 corredorUnoB=new Corredor12(datoEquipo2,listaEquipoB);
        Corredor22 corredorDosB=new Corredor22(datoEquipo2, listaEquipoB);
        Corredor32 corredorTresB=new Corredor32(datoEquipo2, listaEquipoB);
        
        Corredor13 corredorUnoC=new Corredor13(datoEquipo3,listaEquipoC);
        Corredor23 corredorDosC=new Corredor23(datoEquipo3, listaEquipoC);
        Corredor33 corredorTresC=new Corredor33(datoEquipo3, listaEquipoC);

        corredorTresA.setCorredorB(corredorTresB);
        corredorTresA.setCorredorC(corredorTresC);
        
        corredorTresB.setCorredorA(corredorTresA);
        corredorTresB.setCorredorC(corredorTresC);
        
        corredorTresC.setCorredorA(corredorTresA);
        corredorTresC.setCorredorB(corredorTresB);
        
        corredorUno1.start();
        corredorDosA.start();
        corredorTresA.start();

        corredorUnoB.start();
        corredorDosB.start();
        corredorTresB.start();
        
        corredorUnoC.start();
        corredorDosC.start();
        corredorTresC.start();   
    }
    
    /**
     * Funcion que llena los espacios del mapa con relevos del equipo A
     */
    public void llenarEquipo1(){
        for (int i=0;i<100;i++) {
            listaEquipoA.put(i, " ");
        }
         listaEquipoA.put(33, "\033[34m t");
         listaEquipoA.put(66,"\033[34m t");
    }
    
    /**
     * Funcion que llena los espacios del mapa con relevos del equipo B
     */
    public void llenarEquipo2(){
        for (int i=0;i<100;i++) {
           listaEquipoB.put(i, " ");
        }
        listaEquipoB.put(33, "\033[35m t");
        listaEquipoB.put(66,"\033[35m t");
    }
    
    /**
     * Funcion que llena los espacios del mapa con relevos del equipo C
     */
    public void llenarEquipo3(){
        for (int i=0;i<100;i++) {
            listaEquipoC.put(i, " ");
        }
         listaEquipoC.put(33, "\033[33m t");
         listaEquipoC.put(66,"\033[33m t");
    }
   
}
