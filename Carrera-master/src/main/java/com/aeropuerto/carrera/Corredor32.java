
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que representa el corredor tres del equipo B
 * @author ivan lopez
 * @author duvan cañon
 */
public class Corredor32 extends Thread{

    /**
     * Enunciado de clase para generar numeros random
     */
    Random movimientos;

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
     Map<Integer,String> listaEquipoB=new HashMap<>();

     /**
      * Variable para terminar el hilo
      */
     boolean terminar = true;

     /**
      *Clase para terminar otro hilo en caso de ganar
      */
    Corredor31 corredorA;
     
    /**
      *Clase para terminar otro hilo en caso de ganar
      */
    Corredor33 corredorC;
    
    /**
     * Constructor de la clase
     * @param paso
     * @param lista 
     */
    public Corredor32(Dato paso,Map<Integer,String> lista) {
        this.paso = paso;
        movimientos=new Random();
        this.listaEquipoB=lista;
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
            
            
           listaEquipoB.put(posicion,"\033[35m t");
           Imprimir.imprimir(listaEquipoB, posicion);
           listaEquipoB.put(posicion, " ");   
           synchronized(paso){
                if(terminoCarrera()){
                    paso.notify();
                    paso.setPasos(pasos);
                    corredorA.stop();
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
     * Funcion que devuelve el objeto hilo corredor del equipo A
     * @return
     */
    public Corredor31 getCorredorA() {
        return corredorA;
    }

    /**
     * Funcion que cambia el objeto hilo corredor del equipo A
     * @param corredorA
     */
    public void setCorredorA(Corredor31 corredorA) {
        this.corredorA = corredorA;
    }

    /**
     * Funcion que devuelve el objeto hilo corredor del equipo A
     * @return
     */
    public Corredor33 getCorredorC() {
        return corredorC;
    }

    /**
     * Funcion que cambia el objeto hilo corredor del equipo A
     * @param corredorC
     */
    public void setCorredorC(Corredor33 corredorC) {
        this.corredorC = corredorC;
    }
    
    /**
     * Funcion que imprime la medalla en caso de ganar
     */
    public void imprimirMedalla(){
       
        System.out.println("\033[35m EQUIPO 2(violeta)");
        System.out.println("\033[35m    GANADOR      ");
     
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }
    
}
