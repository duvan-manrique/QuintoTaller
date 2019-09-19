
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;


/**
 *Clase que inicia el hilo principal del programa
 * @author jeison gaona
 */
public class Principal extends Thread{

    /**
     * Objeto que representa la estafeta del equipo A
     */
    Dato estafetaEquipoA=new Dato();
    
    /**
     * Objeto que representa la estafeta del equipo B
     */
    Dato estafetaEquipoB=new Dato();
     
    /**
     * Objeto que representa la estafeta del equipo C
     */
    Dato estafetaEquipoC=new Dato();

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
        llenarEquipoA();
        llenarEquipoB();
        llenarEquipoC();
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
        Corredor11 corredorUnoA=new Corredor11(estafetaEquipoA,listaEquipoA);
        Corredor21 corredorDosA=new Corredor21(estafetaEquipoA, listaEquipoA);
        Corredor31 corredorTresA=new Corredor31(estafetaEquipoA, listaEquipoA);
        
        Corredor12 corredorUnoB=new Corredor12(estafetaEquipoB,listaEquipoB);
        Corredor22 corredorDosB=new Corredor22(estafetaEquipoB, listaEquipoB);
        Corredor32 corredorTresB=new Corredor32(estafetaEquipoB, listaEquipoB);
        
        Corredor13 corredorUnoC=new Corredor13(estafetaEquipoC,listaEquipoC);
        Corredor23 corredorDosC=new Corredor23(estafetaEquipoC, listaEquipoC);
        Corredor33 corredorTresC=new Corredor33(estafetaEquipoC, listaEquipoC);

        corredorTresA.setCorredorB(corredorTresB);
        corredorTresA.setCorredorC(corredorTresC);
        
        corredorTresB.setCorredorA(corredorTresA);
        corredorTresB.setCorredorC(corredorTresC);
        
        corredorTresC.setCorredorA(corredorTresA);
        corredorTresC.setCorredorB(corredorTresB);
        
        corredorUnoA.start();
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
    public void llenarEquipoA(){
        for (int i=0;i<100;i++) {
            listaEquipoA.put(i, " ");
        }
         listaEquipoA.put(33, "\033[31m t");
         listaEquipoA.put(66,"\033[31m t");
    }
    
    /**
     * Funcion que llena los espacios del mapa con relevos del equipo B
     */
    public void llenarEquipoB(){
        for (int i=0;i<100;i++) {
           listaEquipoB.put(i, " ");
        }
        listaEquipoB.put(33, "\033[32m t");
        listaEquipoB.put(66,"\033[32m t");
    }
    
    /**
     * Funcion que llena los espacios del mapa con relevos del equipo C
     */
    public void llenarEquipoC(){
        for (int i=0;i<100;i++) {
            listaEquipoC.put(i, " ");
        }
         listaEquipoC.put(33, "\033[34m t");
         listaEquipoC.put(66,"\033[34m t");
    }
   
}
