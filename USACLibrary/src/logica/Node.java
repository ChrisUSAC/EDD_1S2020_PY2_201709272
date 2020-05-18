
package logica;

/**
 *
 * @author cris
 */
public class Node {
    String categoria, key;
    int  height; //dato guardado (carnet) y altura
    Node left,right; // punteros hacia los hijos iz y derecho
    
    //metodo constructor
    Node(String d,String categoria)
    {
        this.key = d;  //valor a guardar
        this.categoria = categoria;
        this.height = 1; // altura del arbol a guardar
    }
    
}

