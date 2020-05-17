package logica;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author cris
 */
public class tablaHash {

    public ListaDoble arreglo; //almacena el arreglo de usuarios que pertenecen al sistema
    public int tamanio, contador;
//------------------------------------------------------------------------------------------------------------------------------------------------
//metodo constructor    

    public tablaHash(int tam) {
        this.tamanio = tam; //se le pasa el tamano de la tabla hash 
        arreglo = new ListaDoble(); // se crea la lista 
        arreglo.crearPosiciones(tam); // se crean las posiciones en la lista vacia

    }
//------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo que asigna un indice en la tabla, e inserta en la lista de usuarios en el indice

    public void funcionHash(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {
        //calculo del indice en el que se inserta en la tabla 
        int indiceArreglo = Integer.parseInt(carnet) % this.tamanio;

        System.out.println("El indice es: " + indiceArreglo + " Para el carnet: " + carnet);
        System.out.println("----------------------------------------------------------");

        //buscar en la lista si la posicion esta vacia, sino proceder a manejar colision
        NodoLista aux = this.arreglo.primero;
        int contar = 0; //variable que sirve para posicionarse en el indice obtenido en indiceArreglo
        while (contar != indiceArreglo) {

            aux = aux.siguiente;
            contar++;
        }

        //luego de calcular y tratar las colisiones esta listo para insertar el nuevo dato
        aux.usuarios.insertarFinal(carnet, nombre, apellido, carrera, password, passMD5);
        //arreglo.usuarios.insertarFinal(carnet, nombre, apellido, carrera, password, passMD5);
    }

//------------------------------------------------------------------------------------------------------------------------------------------------
//metodo para borrar un elemento
    public void eliminar(String carnet) {

        //calculo del indice en el que se elimina en la tabla 
        int indiceArreglo = Integer.parseInt(carnet) % this.tamanio;

        System.out.println("El indice es: " + indiceArreglo + " Para el carnet a eliminar: " + carnet);
        System.out.println("----------------------------------------------------------");

        //buscar en la lista si la posicion esta vacia, la lista simple donde estan los usuarios
        NodoLista aux = this.arreglo.primero;
        int contar = 0; //variable que sirve para posicionarse en el indice obtenido en indiceArreglo
        while (contar != indiceArreglo) {

            aux = aux.siguiente;
            contar++;
        }
        //eliminar al usuario de la lista simple de usuarios de esa posicion
        aux.usuarios.eliminar(carnet);
        //arreglo.usuarios.eliminar(carnet);

    }
//------------------------------------------------------------------------------------------------------------------------------------------------
//metodo para modificar un elemento

    public void modificar(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {

        //calculo del indice en el que se modifica en la tabla 
        int indiceArreglo = Integer.parseInt(carnet) % this.tamanio;

        System.out.println("El indice es: " + indiceArreglo + " Para el carnet a modificar: " + carnet);
        System.out.println("----------------------------------------------------------");

        //buscar en la lista si la posicion
        NodoLista aux = this.arreglo.primero;
        int contar = 0; //variable que sirve para posicionarse en el indice obtenido en indiceArreglo
        while (contar != indiceArreglo) {

            aux = aux.siguiente;
            contar++;
        }

        //enviar a modificar en la lista simple del arreglo
        aux.usuarios.modificar(carnet, nombre, apellido, carrera, password, passMD5);
        //arreglo.usuarios.modificar(carnet, nombre, apellido, carrera, password, passMD5);

    }
//------------------------------------------------------------------------------------------------------------------------------------------------
    //llena la informacion del frame para la modificacion de datos del usuario

    public NodoLista busquedaModificar(String carnet) {

        //calculo del indice en el que se modifica en la tabla 
        int indiceArreglo = Integer.parseInt(carnet) % this.tamanio;

        System.out.println("El indice es: " + indiceArreglo + " Para el carnet info: " + carnet);
        System.out.println("----------------------------------------------------------");

        //buscar en la lista si la posicion
        NodoLista aux = this.arreglo.primero;
        int contar = 0; //variable que sirve para posicionarse en el indice obtenido en indiceArreglo
        while (contar != indiceArreglo) {

            aux = aux.siguiente;
            contar++;
        }

        //enviar a modificar en la lista simple del arreglo
        return aux.usuarios.buscarUsuario(carnet);

    }
//------------------------------------------------------------------------------------------------------------------------------------------------
//metodo que sirve para validar si se puede guardar un usuario

    public boolean existeUsuario(String carnet) {
        boolean validar = false; // en caso de encontrar a ese usuario validar con true

        //calculo del indice en el que se modifica en la tabla 
        int indiceArreglo = Integer.parseInt(carnet) % this.tamanio;

        System.out.println("El indice es: " + indiceArreglo + " Para el carnet a buscar si existe : " + carnet);
        System.out.println("----------------------------------------------------------");

        //buscar en la lista si la posicion
        NodoLista aux = this.arreglo.primero;
        int contar = 0; //variable que sirve para posicionarse en el indice obtenido en indiceArreglo
        while (contar != indiceArreglo) {

            aux = aux.siguiente;
            contar++;
        }

        //enviar a ver si existe ya el usuario
        validar = aux.usuarios.existeUsuario(carnet);

        return validar;

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //graficar en consola
    public void graficaConsola() {

        NodoLista arreg = this.arreglo.primero; // referencia al arreglo de 45 espacios
        int espacio = 0; //nos dice en que espacio nos encontramos
        while (arreg != null) {

            System.out.print("[" + espacio + "]->");
            NodoLista aux = arreg.usuarios.primero;

            while (aux != null) {
                System.out.print("[ Carnet: " + aux.carnet + "]->>");
                aux = aux.siguiente;
            }
            System.out.print("\n");

            arreg = arreg.siguiente;
            espacio++;
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public void graficar() {
        try {

            PrintWriter w = new PrintWriter("tablaHash.dot");  // creacion del archivo
            w.println("digraph G{ \n");
            w.println("node[ shape = box] \n");

            // asignacion de id para el vector de 45 -----------------------------------------------
            NodoLista arreg = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio = 0; //nos dice en que espacio nos encontramos
            while (arreg != null) {

                w.println(espacio + "[label = " + "\"" + espacio + "\"" + ", width=1.5, group = 1];" + "\n");

                arreg = arreg.siguiente;
                espacio++;
            }

            //enlaces verticales del vector -----------------------------------------------------------
            NodoLista arreg2 = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio2 = 0; //nos dice en que espacio nos encontramos
            while (arreg2.siguiente != null) {

                w.println(espacio2 + " -> " + (espacio2 + 1) + " ; " + "\n");
                arreg2 = arreg2.siguiente;
                espacio2++;
            }

            //=================================================================
            //asignacion de id horizontal a las listas simples
            NodoLista arreg3 = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio3 = 0; //nos dice en que espacio nos encontramos
            while (arreg3 != null) {

                NodoLista aux = arreg3.usuarios.primero;
                int col = 2;
                while (aux != null) {
                    w.println("A" + aux.carnet + "[label = " + "\"" + aux.carnet +"\n"
                                                                    +" Nombre: "+aux.nombre+"\n"
                                                                    +"Apellido: "+aux.apellido+"\n"
                                                                    +"Carrera: "+aux.carrera+"\n"
                                                                    +"Password: "+aux.password+"\n"
                                                                    +"\"" + ", width=1.5, group = " + col + "];" + "\n");
                    aux = aux.siguiente;
                    col++;
                }

                arreg3 = arreg3.siguiente;
                espacio3++;
            }

            //enlaces de la lista simple///////////////////////////////////////////////////////////////////////
            NodoLista arreg4 = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio4 = 0; //nos dice en que espacio nos encontramos
            while (arreg4 != null) {

                NodoLista aux = arreg4.usuarios.primero;

                while (aux != null) {
                    if (aux.siguiente != null) {
                        w.println("A" + aux.carnet + " -> " + "A" + aux.siguiente.carnet + "\n");
                    }

                    aux = aux.siguiente;

                }

                arreg4 = arreg4.siguiente;
                espacio4++;
            }

            //enlaces de vector con listas que existan==========================================================
            NodoLista arreg5 = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio5 = 0; //nos dice en que espacio nos encontramos
            while (arreg5 != null) {

                NodoLista aux = arreg5.usuarios.primero;

                while (aux != null) {

                    w.println(espacio5 + " -> " + "A" + aux.carnet + "\n");
                    break;
                }

                arreg5 = arreg5.siguiente;
                espacio5++;
            }
            //--------------------------------------------------------------------------------------------
            //posicionamiento en el mismo rango
            NodoLista arreg6 = this.arreglo.primero; // referencia al arreglo de 45 espacios
            int espacio6 = 0; //nos dice en que espacio nos encontramos
            while (arreg6 != null) {
                String same ="";
                same = "{rank =  same;"+espacio6+";";
                NodoLista aux = arreg6.usuarios.primero;

                while (aux != null) {

                    same += "A"+aux.carnet+";";
                    aux = aux.siguiente;
                }
                same += "}";
                w.println(same);
                arreg6 = arreg6.siguiente;
                espacio6++;
            }
            //-------------------------------------

            w.println("}");
            w.close();  //cerrar la escritura del archivo

            //construccion del archivo .dot .png
            try {
                ProcessBuilder pbuilder;

                pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "tablaHash.png", "tablaHash.dot");
                pbuilder.redirectErrorStream(true);
                //Ejecuta el proceso
                pbuilder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //abrir imagen            
            File archivo = new File("tablaHash.png");
            try {
                Desktop.getDesktop().open(archivo);

            } catch (Exception e) {
                System.out.println("no se encontro imagen");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
