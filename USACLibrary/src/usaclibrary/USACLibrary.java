package usaclibrary;

import interfaz.Login;
import java.io.IOException;
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
    public static AVLTree arbolAvl = new AVLTree(); 
    public static ListaSimple recorridosAVL = new ListaSimple(); // lista para recoridos del avl
    public static ListaSimple usuariosSistema = new ListaSimple(); // lista para recoridos del avl
    //variables que obtienen el nombre del usuario que se encuentra logeado.
    public static String UsuarioLogeado = "";
    public static String ClaveUsuarioLogeado = "";
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new Login().setVisible(true);
        
        /*
         AVLTree tree = new AVLTree();  
  
        
        tree.root = tree.insert(tree.root, "10","cat");
        tree.root = tree.insert(tree.root, "20","cat"); 
        tree.root = tree.insert(tree.root, "30","cat");
        tree.root = tree.insert(tree.root, "40","cat");
        tree.root = tree.insert(tree.root, "50","cat");
        tree.root = tree.insert(tree.root, "25","cat");
        tree.root = tree.deleteNode(tree.root, "20");
        tree.root = tree.deleteNode(tree.root, "30");
        tree.root = tree.deleteNode(tree.root, "10");
        System.out.println("imprimiendo");
        tree.inOrder(tree.root); 
        tree.graficar();
        
        */
        
    }

}
