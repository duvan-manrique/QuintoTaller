/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que representa el corredor uno del equipo A
 * @author jeison gaona
 */
public class Corredor33 extends Thread{

    /**
     * Enunciado de clase para generar numeros random
    */
    Random moviRandom;

    /**
     * Variable que guarda los pasos de los corredores
     */
    int pasos = 0;

    /**
     * Dato que sincroniza la carrera
     */
    final Dato paso;

    /**
     * Mapa para pintar la carrera
     */
    Map<Integer, String> listaEquipoC = new HashMap<>();

    /**
     * Clase para terminar otro hilo en caso de ganar
     */
    Corredor31 corredorA;

    /**
     * Clase para terminar otro hilo en caso de ganar
     */
    Corredor32 corredorB;

    /**
     * Variable para terminar el hilo
     */
    boolean terminar = true;

    /**
     * Constructor de la clase
     * @param paso
     * @param lista 
     */
    public Corredor33(Dato paso,Map<Integer,String> lista) {
        this.paso = paso;
        moviRandom = new Random();
        this.listaEquipoC = lista;
    }
    
    
    /**
     * Metodo para iniciar el hilo
     */
    @Override
    public void run() {
         while(terminar==true){
            iniciar();
        }
    }
    
    
    /**
     * Metodo para simular la carrera
     */
    public void iniciar(){      
        synchronized(paso){
                try {
                    if(!terminoCarrera()){
                      paso.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Corredor21.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
       
        int posicion=paso.getPasos();
        pasos=paso.getPasos();

        while(pasos<100 || pasos==100){
            
            int numero=moviRandom.devolverNumero();
               pasos+=numero;
               posicion+=numero;
            
            
           listaEquipoC.put(posicion,"\033[34m t");
           Imprimir.imprimir(listaEquipoC, posicion);
           listaEquipoC.put(posicion, " "); 
           synchronized(paso){
                if(terminoCarrera()){
                    paso.notify();
                    paso.setPasos(pasos);
                    corredorA.stop();
                    corredorB.stop();
                    imprimirMedalla();
                    matarHilo(); 
                    break;
                }
            }
        }
        
    }
    
    /**
     * Metodo que determina si ya acabo la carrera
     * @return
     */
    public boolean terminoCarrera(){
        boolean valor=false;
       if(pasos==100 || pasos>100){
          valor=true; 
       }
      return valor;
    }
    
    
     /**
     * Metodo para acabar el hilo
     */
     public void matarHilo(){
        this.terminar=false;
    }
    
    
     /**
     * Funcion que imprime la medalla en caso de ganar
     */
    public void imprimirMedalla(){
        System.out.println("   \033[34m____________   ");
        System.out.println("  \033[34m/            \\   ");
        System.out.println(" \033[34m/              \\");
        System.out.println("\033[34m|                | ");
        System.out.println("\033[34m| EQUIPO C(azul) |");
        System.out.println("\033[34m|   GANADOR      |");
        System.out.println("\033[34m|                | ");
        System.out.println(" \033[34m\\              / ");
        System.out.println("  \033[34m\\____________/");
    }

    /**
    * Funcion que devuelve el objeto hilo corredor del equipo A
    * @return 
    */
    public Corredor31 getCorredorA() {
        return corredorA;
    }

    /**
     * Funcion que cambia el objeto hilo corredor del equipo B
     * @param corredorA 
     */
    public void setCorredorA(Corredor31 corredorA) {
        this.corredorA = corredorA;
    }

    /**
    * Funcion que devuelve el objeto hilo corredor del equipo B
    * @return 
    */
    public Corredor32 getCorredorB() {
        return corredorB;
    }

    /**
     * Funcion que cambia el objeto hilo corredor del equipo B
     * @param corredorB
     */
    public void setCorredorB(Corredor32 corredorB) {
        this.corredorB = corredorB;
    }

    
    
    
}
