
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que representa el corredor tres del equipo A
 * @author jeison gaona
 */
public class Corredor31 extends Thread{

     /**
     * Enunciado de clase para generar numeros random
     */
    Random movimientos;
 
    /**
     * Variable que guarda los pasos de los corredores
     */
    int pasos=0;
    
    /**
     * Dato que sincroniza la carrera
     */
    final Dato paso;
   
    /**
     * Mapa para pintar la carrera
     */
    Map<Integer,String> listaEquipoA=new HashMap<>();

    /**
     * Clase para terminar otro hilo en caso de ganar
     */
    Corredor32 corredorB;
    
     /**
     * Clase para terminar otro hilo en caso de ganar
     */
    Corredor33 corredorC;
    
    /**
     * Variable para terminar el hilo
     */
    boolean terminar=true;
    
     /**
      * Constructor de la clase
      * @param paso
      * @param lista 
      */
    public Corredor31(Dato paso,Map<Integer,String> lista) {
       this.paso = paso;
        movimientos=new Random();
        this.listaEquipoA=lista;
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
            
            int numero=movimientos.devolverNumero();
               pasos+=numero;
               posicion+=numero;
            
            
           listaEquipoA.put(posicion,"\033[31m t");
           Imprimir.imprimir(listaEquipoA, posicion);
           listaEquipoA.put(posicion, " ");
           synchronized(paso){
                if(terminoCarrera()==true){
                    paso.notify();
                    paso.setPasos(pasos);
                    corredorB.stop();
                    corredorC.stop();
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

    

    /**
    * Funcion que devuelve el objeto hilo corredor del equipo B
    * @return 
    */
    public Corredor33 getCorredorC() {
        return corredorC;
    }

    /**
     * Funcion que cambia el objeto hilo corredor del equipo B
     * @param corredorC 
     */
    public void setCorredorC(Corredor33 corredorC) {
        this.corredorC = corredorC;
    }
     
    /**
     * Funcion que imprime la medalla en caso de ganar
     */
    public void imprimirMedalla(){
        System.out.println("   \033[31m____________   ");
        System.out.println("  \033[31m/            \\   ");
        System.out.println(" \033[31m/              \\");
        System.out.println("\033[31m|                | ");
        System.out.println("\033[31m| EQUIPO A(Rojo) |");
        System.out.println("\033[31m|   GANADOR      |");
        System.out.println("\033[31m|                | ");
        System.out.println(" \033[31m\\              / ");
        System.out.println("  \033[31m\\____________/");
    }
    
}
