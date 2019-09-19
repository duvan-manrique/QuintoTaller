
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el corredor dos del equipo A
 * @author ivan lopez
 * @author  duvan cañon
 */
public class Corredor21 extends Thread{

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
     * Constructor de la clase
     * @param paso
     * @param lista 
     */
    public Corredor21(Dato paso,Map<Integer,String> lista) {
        this.paso = paso;
        movimientos=new Random();
        this.listaEquipoA=lista;
    }
    
    /**
     * Metodo para iniciar el hilo
     */
    @Override
    public void run() {
       iniciar();
       
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
        while(pasos<66 || pasos==66){
            int numero=movimientos.devolverNumero();
                pasos+=numero;
               posicion+=numero;
            
            
          listaEquipoA.put(posicion,"\033[34m t");
          Imprimir.imprimir(listaEquipoA, posicion);
          listaEquipoA.put(posicion, " ");  
          synchronized(paso){
                if(terminoCarrera()==true){
                    paso.notify();
                    paso.setPasos(pasos);
                    break;
                }
            }
        }
         listaEquipoA.put(62, " ");  
         listaEquipoA.put(63, " ");  
         listaEquipoA.put(64, " "); 
         listaEquipoA.put(65, " "); 
         listaEquipoA.put(66, " "); 
         listaEquipoA.put(67, " ");
         listaEquipoA.put(68, " "); 
    }
    
    /**
     * Metodo que evalua si ya se termino la carrea para el corredor
     * @return 
     */
    public boolean terminoCarrera(){
        boolean valor=false;
       if(pasos==66 || pasos>66){
          valor=true; 
       }
      return valor;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }
    
    
}
