
package com.aeropuerto.carrera;

import java.util.HashMap;
import java.util.Map;


/**
 * Clase que representa el corredor uno del equipo C
 * @author ivan lopez
 * @author duvan cañon
 */
public class Corredor13 extends Thread{

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
    Map<Integer,String> listaEquipoC=new HashMap<>();

    /**
     * Constructor de la clase
     * @param paso
     * @param lista 
     */
    public Corredor13(Dato paso,Map<Integer,String> lista) {
        this.paso = paso;
        this.listaEquipoC=lista;
        movimientos=new Random();
    }

    /**
     * Metodo que inicia el hilo
     */
    @Override
    public void run() {
        
        iniciar();
    }
    
    /**
     * Metodo para simular la carrera
     */
    public void  iniciar(){
         
        int posicion=0;
        while(pasos<32 || pasos==32){
            int numero=movimientos.devolverNumero();
            pasos+=numero;
            posicion+=numero;
            listaEquipoC.put(posicion,"\033[33m t");
            Imprimir.imprimir(listaEquipoC,posicion);
            listaEquipoC.put(posicion, " ");
            synchronized(paso){
                if(terminoCarrera()){
                    paso.notify();
                    paso.setPasos(pasos);
                    break;
                }
            }
        }
        listaEquipoC.put(32, " ");  
        listaEquipoC.put(33, " ");  
        listaEquipoC.put(34, " "); 
        listaEquipoC.put(35, " "); 
        listaEquipoC.put(36, " "); 
    } 
    
      /**
     * Metodo que determina si acabo la carrera
     * @return 
     */
    public boolean terminoCarrera(){
        boolean valor=false;
       if(pasos==32 || pasos>32){
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
