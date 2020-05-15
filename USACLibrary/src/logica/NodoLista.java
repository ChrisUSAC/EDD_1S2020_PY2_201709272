package logica;

/**
 *
 * @author cris
 */
public class NodoLista {

    public NodoLista siguiente; // puntero al siguiente nodo
    NodoLista anterior; // puntero al nodo anterior

    //atributos del estudiante
    public String carnet;
    public String nombre;
    public String apellido;
    public String carrera;
    public String password;
    public String passMD5;

    public ListaSimple usuarios; // lista de usuarios en el sistema

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo constructor
    public NodoLista(String carnet, String nombre, String apellido, String carrera, String password, String passMD5) {
        this.siguiente = null;
        this.anterior = null;
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        this.passMD5 = passMD5;

        this.usuarios = new ListaSimple();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //constructor para nulos
    public NodoLista() {
        this.siguiente = null;
        this.anterior = null;
        this.carnet = null;
        this.nombre = null;
        this.apellido = null;
        this.carrera = null;
        this.password = null;
        this.passMD5 = null;

        this.usuarios = new ListaSimple();

    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

}
