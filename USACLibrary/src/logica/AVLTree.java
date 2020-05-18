package logica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author cris
 */
public class AVLTree {

    public Node root; // raiz del arbol

    //funcion get de la altura del arbol --------------------------------------------------------------
    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    //funcion que devuelve el mayor de dos numeros enteros ---------------------------------------------
    int max(int a, int b) {
        return (a > b) ? a : b; //equivalente a un if de una sola linea
    }

    //rotacion a la derecha ----------------------------------------------------------------------------- 
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right; //posible nodo existente
        //rotaciones
        x.right = y;
        y.left = T2;
        //actualizar alturas
        y.height = max(height(y.left), height(y.right)) + 1; // nivel adicional por eso el +1 por ser y un nivel arriba 
        x.height = max(height(x.left), height(x.right)) + 1;

        //enviar la nueva raiz
        return x; // se retorna el nodo que quedo por raiz
    }

    //rotacion a la izquierda ---------------------------------------------------------------------------
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        //rotaciones
        y.left = x;
        x.right = T2;
        //actualizar alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        //enviar la nueva raiz
        return y;
    }

    //obtiene el balance de un nodo dado ----------------------------------------------------------------
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);  //factor de equilibrio izq - derecha
    }

    //metodo para insertar un nodo y balancea el arbol ////////////////////////////////////////////////////////////////////////////////////
    public Node insert(Node node, String key, String categoria) {

        //insercion normal como en el arbol binario
        if (node == null) {
            return (new Node(key, categoria)); //reserva de memoria cuando se llega a un nodo hoja para insertarlo
        }
        //if (key < node.key) {
        if (key.compareToIgnoreCase(node.key) < 0) { //insertar izquierda
            node.left = insert(node.left, key, categoria);
            //System.out.println(node.key +" <- "+"nodo.left: "+node.left.key);
            //} else if (key > node.key) {
        } else if (key.compareToIgnoreCase(node.key) > 0) { // insertar derecha
            node.right = insert(node.right, key, categoria);
            //System.out.println(node.key +" -> "+" nodo.right: "+node.right.key);
        } else // si esta repetido solo se retorna el nodo, mostrar mensaje de categoria ya existente
        {
            return node;
        }

        //parte de AVL implementada
        //calcular alturas del nodo
        node.height = 1 + max(height(node.left), height(node.right));

        //obtener el factor de equilibro del nodo aqui se ve si es un 2, -2, 1,-1 o 0
        int balance = getBalance(node);

        //evaluar los 4 casos de balanceo
        //rotacion izq izq
        //if (balance > 1 && key < node.left.key) {
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) < 0) {
            return rightRotate(node);
        }

        // derecha derecha 
        //if (balance < -1 && key > node.right.key) {
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) > 0) {
            return leftRotate(node);
        }

        // izq derecha doble rotacion
        //if (balance > 1 && key > node.left.key) {
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // derecha izq doble rotacion
        //if (balance < -1 && key < node.right.key) {
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        //luego solo regresar el nodo
        return node;
    }

    //funcion que busca el valor minimo de un arbol dado y lo retorna
    Node minValueNode(Node node) {
        Node current = node;

        //ciclo para buscar por lado izquierdo, de no tener parte izquierda el valor minimo es el mismo nodo
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    //metodo para eliminar una clave dada --------------------------------------------------------------------------------
    public Node deleteNode(Node root, String key) {

        //forma estandar
        //si el arbol esta vacio solo regresar la raiz nula
        if (root == null) {
            return root;
        }

        //si la clave a eliminar es menor, enviar por subarbol izquierd llamada recursica
        //if (key < root.key) {
        if (key.compareToIgnoreCase(root.key) < 0) {
            root.left = deleteNode(root.left, key);
        } //si es mayor la clave llamar por subarbol derecho, llamada recursiva
        //else if (key > root.key) {
        else if (key.compareToIgnoreCase(root.key) > 0) {
            root.right = deleteNode(root.right, key);
        } // evaluar si el nodo a eliminar es hoja, si solo tiene 1 hijo, o si tiene 2 hijos, de este punto en adelante si existe el nodo a eliminar
        else {

            // si tiene 1 hijo sustituirlo por el hijo o si no tiene hijos
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // si no tiene hijos  
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // copia el hijo restante 
                {
                    root = temp; //contenido asignado if con el ||, el que no esta vacio
                }
            } else {

                //tiene ambos hijos, buscar el menor de los mayore, por eso se envia en minValueNode el sub arbol derecho 
                Node temp = minValueNode(root.right);

                // sustitucion de valores con el nodo a eliminar, es decir remplazar informacion --------------********
                root.key = temp.key;

                // llamada recursiva a eliminar para que borre del subarbol derecho y elimine la copia que queda en ese subarbol el nodo hoja 
                root.right = deleteNode(root.right, temp.key);
            }
        }

        //parte del AVL es balanceo se evalua lo siguiente
        //si se tenia solo un nodo retornarlo con null
        if (root == null) {
            return root;
        }

        // actualizar las alturas de los nodos
        root.height = max(height(root.left), height(root.right)) + 1;

        //obtener el factor de equilibrio para evaluar
        int balance = getBalance(root);

        //verificar que quede balanceado
        //casos de rotaciones
        // Left Left Case  
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case  
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case  
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case  
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        //si no se aplica ningun balance solo retornar el nodo
        return root;
    }

    //recorrido preorden ---------------------------------------------------------------------
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //recorrido inOrder -----------------------------------------------------------------------
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    //postOrden -------------------------------------------------------------------------------
    public void postOrden(Node node) {
        if (node != null) {

            postOrden(node.left);
            postOrden(node.right);
            System.out.print(node.key + " ");
        }
    }
    
        //recorrido preOrderReporte ---------------------------------------------------------------------
    public void preOrderReporte(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            usaclibrary.USACLibrary.recorridosAVL.insertarFinalCategoria(node.key);
            preOrderReporte(node.left);
            preOrderReporte(node.right);
        }
    }

    //recorrido inOrder -----------------------------------------------------------------------
    public void inOrderReporte(Node node) {
        if (node != null) {
            inOrderReporte(node.left);
            System.out.print(node.key + " ");
            usaclibrary.USACLibrary.recorridosAVL.insertarFinalCategoria(node.key);
            inOrderReporte(node.right);
        }
    }

    //postOrden -------------------------------------------------------------------------------
    public void postOrdenReporte(Node node) {
        if (node != null) {

            postOrdenReporte(node.left);
            postOrdenReporte(node.right);
            System.out.print(node.key + " ");
            usaclibrary.USACLibrary.recorridosAVL.insertarFinalCategoria(node.key);
        }
    }

    //-----------------------------------------------------------------------------------------
    //retorna el nodo que se envia a buscar
    public Node search(String categoria, Node r) {
        if (this.root == null) {
            return null;
            //} else if (r.key == carnet) {    
        } else if (r.key.compareToIgnoreCase(categoria) == 0) {
            return r;
            //} else if (r.key < carnet) {    
        } else if (r.key.compareToIgnoreCase(categoria) < 0) {
            return search(categoria, r.right);
        } else {
            return search(categoria, r.left);
        }
    }

    //------------------------------------------------------------------------------------------
    public void graficar() throws IOException {
        try {
            FileWriter Aavl = new FileWriter("ArbolAVL.dot");
            PrintWriter escritura = new PrintWriter(Aavl);
            escritura.println("digraph G\r\n"
                    + "{\r\n"
                    + "        node [shape = record];");
            escritura.println(dibujo(root));
            escritura.println(enlaces(root));
            escritura.println("}");
            Aavl.close();
        } catch (Exception rep) {
            rep.printStackTrace();
        }

        ProcessBuilder pbuilder;
        pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "ArbolAVL.png", "ArbolAVL.dot");
        pbuilder.redirectErrorStream(true);
        pbuilder.start();

        //abrir imagen            
        File archivo = new File("ArbolAVL.png");
        try {
            Desktop.getDesktop().open(archivo);

        } catch (Exception e) {
            System.out.println("no se encontro imagen");
        }

    }

    String dibujo(Node n) {

        String texto = "";
        if (n != null) {
            texto = "node" + n.key + " [ label =\"<f0> | <f1>" + n.key + "\n " + "Altura: " + n.height + "\n " + "| <f2> \"];\r\n";
            texto = texto + dibujo(n.left);
            texto = texto + dibujo(n.right);
        }

        return texto;
    }

//-----------------------------------
    String enlaces(Node n) {
        String texto = "";
        if (n != null) {
            if (n.left != null) {
                texto = "\"node" + n.key + "\":f0 -> \"node" + n.left.key + "\":f1;\r\n";
            }

            if (n.right != null) {
                texto = texto + "\"node" + n.key + "\":f2 -> \"node" + n.right.key + "\":f1;\r\n";
            }
            texto = texto + enlaces(n.left);
            texto = texto + enlaces(n.right);
        }

        return texto;

    }

}
