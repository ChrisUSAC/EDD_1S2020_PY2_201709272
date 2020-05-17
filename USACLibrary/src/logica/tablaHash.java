package logica;

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
    public void graficaConsola(){
    
        NodoLista arreg = this.arreglo.primero; // referencia al arreglo de 45 espacios
        int espacio = 0; //nos dice en que espacio nos encontramos
        while (arreg!=null) {
            
            System.out.print("["+espacio+"]->");
            NodoLista aux = arreg.usuarios.primero;
            
            while (aux!=null) {
                System.out.print("[ Carnet: "+aux.carnet+"]->>");
                aux = aux.siguiente;
            }
            System.out.print("\n");
            
            arreg = arreg.siguiente;
            espacio++;
        }
    }
    
}
