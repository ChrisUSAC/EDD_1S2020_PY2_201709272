package logica;

/**
 *
 * @author cris
 */
public class ListaDoble {

    public int tamano;
    public NodoLista primero;
    NodoLista ultimo;
    //ListaSimple usuarios; // almacena la lista de usuarios que coinciden en ese espacio del vector (listaDoble)

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo constructor
    public ListaDoble() {

        this.tamano = 0;
        this.primero = null;
        this.ultimo = null;
        //this.usuarios = new ListaSimple();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo que avisa si la lista esta vacia
    public boolean estaVacia() {
        return this.primero == null;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo que crea n elementos en
    public void crearPosiciones(int indice) {
        int contador = 0; // variable que nos itera el while

        while (contador < indice) {

            //creacion del nodo a insertar
            NodoLista nuevo = new NodoLista();
            //validar si la lista esta vacia
            if (estaVacia()) {
                this.primero = nuevo;
                this.ultimo = nuevo;

            } else {
                nuevo.siguiente = this.primero;
                this.primero.anterior = nuevo;
                this.primero = nuevo;
            }
            this.tamano++;
            contador++; // controla el while
        }

    }

//------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo que inserta en la posicion que se le envia de parametro
    public void insertarAt(int indice,String carnet,String nombre,String apellido,String carrera,String password,String passMD5)
    {   
        int contar=0; //variable que nos dice en que indice del arreglo nos encontramos
        NodoLista aux = this.primero; // variable auxiliar que se posiciona en el inicio de la lista
        //ciclo que me posiciona al aux en el indice indicado
        while (contar != indice) {
            
            
            aux =aux.siguiente;
            contar++;
        }
        System.out.println("indice al que se insertara: "+contar);
        
        //insertar en la lista simple
        aux.usuarios.insertarFinal(carnet, nombre, apellido, carrera, password, passMD5);
        //this.usuarios.insertarFinal(carnet, nombre, apellido, carrera, password, passMD5);
    
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo que posiciona al auxiliar en el indice indicado
    public NodoLista posicionarAux(int indice)
    {
        NodoLista aux = this.primero;
        int contar=0;
        
        while (contar!=indice) {
            
            aux =aux.siguiente;
            contar++;
        }
        return aux;
        
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo para imprimir en consola lo que tiene la tabla hash
    public void imprimirConsola()
    {
        NodoLista aux = this.primero;
        int indice=0;
        int contarImpresos =1;
        System.out.println("inicia impresion");
        System.out.println("****************************************************");
        while(aux!=null)
        {
            if (aux.carnet!=null)
            {
                System.out.println("contarImpresos: "+contarImpresos);
                System.out.println("aux.carnet: "+aux.carnet+" Nombre: "+aux.nombre+" Apellido: "+aux.apellido);  
                System.out.println("indice: "+indice);  
                System.out.println("Tamano de la lista: "+this.tamano);
                contarImpresos++;   
            }
            System.out.println("indice: ==================================================== "+indice);
            System.out.println("------------f------------------------------------");
            indice++;
            aux=aux.siguiente;
        }
        
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
}
