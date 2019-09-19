
package test;

import com.aeropuerto.carrera.*;
import com.aeropuerto.carrera.Dato;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas unitarias para evaluar si acabo la carrera
  @author ivan lopez
 * @author duvan cañon
 */
public class PruebasDePasosCorredores {
   
    Dato paso;
    
    Corredor11 corredorUnoA;
    
    Corredor22 corredorDosB;
    
    Corredor33 corredorTresC;
            
    public PruebasDePasosCorredores() {
    }
     @Before
     public void iniciar(){
        paso=new Dato();
        Map<Integer,String> lista=new HashMap<>();
        corredorUnoA=new Corredor11(paso, lista);
        corredorDosB=new Corredor22(paso, lista);
        corredorTresC=new Corredor33(paso, lista);
     }
     
     @Test
     public void evaluarSiYaAcaboLaEtapaA(){
         corredorUnoA.setPasos(33);
         assertEquals(true, corredorUnoA.terminoCarrera());
     }
     
     
     @Test
     public void evaluarSiYaAcaboLaEtapaB(){
         corredorDosB.setPasos(78);
         assertEquals(true, corredorDosB.terminoCarrera());
     }
     
      @Test
     public void evaluarSiYaAcaboLaEtapaC(){
         corredorTresC.setPasos(102);
         assertEquals(true, corredorTresC.terminoCarrera());
     }
     
     @Test
     public void evaluarSiYaAcaboLaEtapaAPruebaDos(){
         corredorUnoA.setPasos(26);
         assertEquals(false, corredorUnoA.terminoCarrera());
     }
     
     
     @Test
     public void evaluarSiYaAcaboLaEtapaBPruebaDos(){
         corredorDosB.setPasos(58);
         assertEquals(false, corredorDosB.terminoCarrera());
     }
     
      @Test
     public void evaluarSiYaAcaboLaEtapaCPruebaDos(){
         corredorTresC.setPasos(99);
         assertEquals(false, corredorTresC.terminoCarrera());
     }
}
