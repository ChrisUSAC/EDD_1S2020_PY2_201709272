package usaclibrary;

import interfaz.Login;
import logica.*;

/**
 *
 * @author cris
 */
public class USACLibrary {

    /**
     * @param args the command line arguments
     */
    
    //Estructuras del sistema
    public static tablaHash hash = new tablaHash(45);
    public static void main(String[] args) {
        // TODO code application logic here
        new Login().setVisible(true);
    }

}
