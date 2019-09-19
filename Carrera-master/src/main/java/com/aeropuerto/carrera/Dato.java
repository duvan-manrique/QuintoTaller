
package com.aeropuerto.carrera;

/**
 *Clase que representa el objeto estafeta
 * @author ivan lopez
 * @author duvan cañon
 */
public class Dato {

    /**
     * Variable que guarda los pasos que da el corredor
     */
    Integer mover;
    
    /**
     * Constructor de la clase
     */
    public Dato() {
    }

    /**
     * Constructor de la clase
     * @param pasos 
     */
    public Dato(Integer pasos) {
        this.mover = pasos;
    }

    /**
     * Funcion que cambia los pasos
     * @param pasos 
     */
    public void setPasos(Integer pasos) {
        this.mover = pasos;
    }

    /**
     * Funcion que devuelve los pasos que lleva
     * @return 
     */
    public Integer getPasos() {
        return mover;
    }
    
    
}
