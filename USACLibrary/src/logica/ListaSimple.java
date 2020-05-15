package logica;

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
}
