package logica;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author cris
 */
public class ListaSimple {

    public int tamano;
    public NodoLista primero;
    NodoLista ultimo;

    //metodo constructor
    public ListaSimple() {

        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;
    }

    //---------------------------------------------------------------------------------------
    //metodo booleano que nos dice si la lista esta vacia
    public boolean estaVacia() {

        return this.primero == null;
    }

    //---------------------------------------------------------------------------------------
    //metodo para insertar al final de la lista
    public void insertarFinal(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {

        //creacion del nodo a insertar en la lista
        NodoLista nuevo = new NodoLista(carnet, nombre, apellido, carrera, password, passMD5);

        //evaluar si la lista esta vacia
        if (estaVacia()) {

            this.primero = nuevo;
            this.ultimo = nuevo;
            this.tamano++;
        } else {

            this.ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            this.ultimo = nuevo;
            this.tamano++;
        }
    }

    //--------------------------------------------------------------------------------------------------------------------------------------
    public void eliminar(String carnet) {

        NodoLista aux = this.primero; //referencia la primer nodo de la lista

        while (aux != null) {

            if (aux.carnet.equals(carnet)) {

                //evaluar si solo existe un nodo
                if (aux == this.primero && this.tamano == 1) {
                    this.primero = null;
                    this.ultimo = null;
                    tamano--; //se reduce el tamano en 1
                    break;

                } else if (aux == this.primero) {
                    this.primero = aux.siguiente;
                    this.primero.anterior = null;
                    tamano--; //se reduce el tamano en 1
                    break;

                } else if (aux == this.ultimo) {
                    this.ultimo = aux.anterior;
                    this.ultimo.siguiente = null;
                    tamano--; //se reduce el tamano en 1
                    break;

                } //uno del centro
                else {
                    aux.anterior.siguiente = aux.siguiente;
                    aux.siguiente.anterior = aux.anterior;
                    tamano--; //se reduce el tamano en 1
                    break;
                }

            }

            aux = aux.siguiente;
        }

    }

    //--------------------------------------------------------------------------------------------------------------------------------------
    public void modificar(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {

        NodoLista aux = this.primero; //referencia la primer nodo de la lista

        while (aux != null) {

            if (aux.carnet.equals(carnet)) {

                aux.nombre = nombre;
                aux.apellido = apellido;
                aux.carrera = carrera;
                aux.password = password;
                aux.passMD5 = passMD5;
            }

            aux = aux.siguiente;
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------------
    //metodo que retorna la informacion del usuario
        public NodoLista buscarUsuario(String carnet) {

        NodoLista aux = this.primero; //referencia la primer nodo de la lista

        while (aux != null) {

            if (aux.carnet.equals(carnet)) {

                return aux;
                
            }

            aux = aux.siguiente;
        }
        
        return aux; // si no encuentra la informacion del carnet lo envia null

    }
    //--------------------------------------------------------------------------------------------------------------------------------------
    //metodo que nos dice si un usuario ya existe
    public boolean existeUsuario(String carnet) {

        boolean validar = false;
        NodoLista aux = this.primero; //referencia la primer nodo de la lista

        while (aux != null) {

            if (aux.carnet.equals(carnet)) {
                validar = true;
            }

            aux = aux.siguiente;
        }
        return validar;

    }
    
    //------------------------------------------------------------------------------------------------------------------------------------------
    public void graficarRecorrido()
    {
        try {

            PrintWriter w = new PrintWriter("RecorridoAVL.dot");  // creacion del archivo
            w.println("digraph G{ \n");
            w.println("node[ shape = box] \n");


            //=================================================================
            //asignacion de id horizontal a las listas simples
                NodoLista aux = primero;
                while (aux != null) {
                    w.println("A" + aux.carnet + "[label = " + "\"" + aux.carnet +"\n"+"\"" + ", width=1.5];" + "\n");
                    aux = aux.siguiente;

                }
            //enlaces de la lista simple///////////////////////////////////////////////////////////////////////

                NodoLista aux2 = primero;

                while (aux2 != null) {
                    if (aux2.siguiente != null) {
                        w.println("A" + aux2.carnet + " -> " + "A" + aux2.siguiente.carnet + "\n");
                    }

                    aux2 = aux2.siguiente;

                }

            //-------------------------------------

            w.println("}");
            w.close();  //cerrar la escritura del archivo

            //construccion del archivo .dot .png
            try {
                ProcessBuilder pbuilder;
                
                pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "RecorridoAVL.png", "RecorridoAVL.dot");
                pbuilder.redirectErrorStream(true);
                //Ejecuta el proceso
                pbuilder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //abrir imagen            
            File archivo = new File("RecorridoAVL.png");
            try {
                Desktop.getDesktop().open(archivo);

            } catch (Exception e) {
                System.out.println("no se encontro imagen");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //-------------------------------------------------------------------------------------------------
    //metodo que resetea la lista
    public void resetearLista()
    {
        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;    
    }
    
    //------------------------------------------------------------------------------------------------
        //metodo para insertar al final de la lista
    public void insertarFinalCategoria(String categoria,String creador) {

        //creacion del nodo a insertar en la lista
        NodoLista nuevo = new NodoLista(categoria,creador);

        //evaluar si la lista esta vacia
        if (estaVacia()) {

            this.primero = nuevo;
            this.ultimo = nuevo;
            this.tamano++;
        } else {

            this.ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            this.ultimo = nuevo;
            this.tamano++;
        }
    }
    
    //---------------------------------------------------------------------------------------------
    //metodo para insertar al final los usuarios en una lista que esten en el sistema
    public void insertarFinalUsuarioSistema(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {

        //creacion del nodo a insertar en la lista
        NodoLista nuevo = new NodoLista(carnet, nombre, apellido, carrera, password, passMD5);

        //evaluar si la lista esta vacia
        if (estaVacia()) {

            this.primero = nuevo;
            this.ultimo = nuevo;
            this.tamano++;
        } else {

            this.ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            this.ultimo = nuevo;
            this.tamano++;
        }
    }
}
