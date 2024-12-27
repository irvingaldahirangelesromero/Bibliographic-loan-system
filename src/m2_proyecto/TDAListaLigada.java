package m2_proyecto;

/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: Lista(Lista simplemente ligada)
 * Fecha: 28 oct 2024
 */ 
 
public class TDAListaLigada {
    private Nodo inicio;
    private Nodo fin;
    private int tam;

    public TDAListaLigada() {
        this.inicio = null;
        this.fin = null;
        this.tam = 0;
    }
    
    public boolean estaVacia(){
        return (tam==0)?true:false;
    }
    
    public void insertarInicio(Libro libro){
        if(tam == 0){
            inicio = new Nodo(libro); //se usa este constructor de Nodo ya que en este 'siguiente' apunta a NULL
            fin = inicio; // Se hace referencia de que el primero Nodo tambien sera el ultimo
            tam++;
        } else {
            inicio = new Nodo(libro, inicio); //este constructor apuntara al elemento que anteriormente era el primero
            tam++;
        }
    }
    
    public void insertarFin(Libro libro){
        if(tam == 0){
            insertarInicio(libro);
        } else {
            Nodo aux = inicio; //aux lo uso para ir recorriendo nodo por nodo
            Nodo paux = inicio; 
            while(aux!=null){ //mientras el nodo en el que me encuentro sea distinto de nulo
                paux = aux; //p aux guardara a mi nodo actual
                aux = aux.sig; // cambio al nodo siguiente
            }
            //cuando aux llegue a null, paux que es el antecesor de aux valdra lo que valia el ultimo nodo recorrido por aux
            paux.sig = new Nodo(libro); // y es ahi donde creo el nuevo nodo
            fin = paux.sig; 
        }
        tam++;
    }
    
    public void insertarNodoPosN(int posicion, Libro libro){
        int contador = 1;
        Nodo aux = inicio; //Para recorrer nodo por nodo
        Nodo paux = inicio; // seguira la posicion anterior de aux
        
        if(inicio==null || posicion==1){
            insertarInicio(libro);
            fin = inicio;
        }else{
            if(posicion >0 && posicion<=tam+1){ //si la posicion no sobrepasa los limites
                while(aux!=null && contador != posicion){ //recorro nodo por nodo mientras no se trate de la posicion indicada
                    paux = aux; //guardo el nodo antes de actualizar el nodo actual
                    aux = aux.sig;  //me cambio al nodo siguiente
                    contador++; 
                }      
                //aux vendria quedando en el nodo de la posicion donde queremos insertar, entonces
               
                paux.sig = new Nodo(libro); //en el siguiente del nodo ''anterior'' creare el nodo donde siguiente sera el siguiente de aux
                paux.sig.sig = aux;
                //por asi decirlo con paux unimos a la izq y con aux a la derecha
                
                tam++;
            }        
        }
    }
    
    public void eliminarInicio(){
        if(inicio != null){
            Nodo temp;
            if(tam == 0){
                inicio = null;
            }else {
                temp = inicio;
                inicio = temp.sig;
                temp = null;
            }
            tam--;
        }
    }
    
    public void eliminarFin(){
        Nodo aux = inicio;
        Nodo paux = inicio;
        while(aux.sig != null){
            paux = aux;
            aux = aux.sig;
        }
        paux.sig = null;
        tam--;
    }
    
    public void eliminarNodoPosN(int posicion){
        if(posicion ==1)
            eliminarInicio();
        else{
            int contador = 1;
            Nodo aux = inicio;
            Nodo paux = inicio;
            while(aux!=null && contador!= posicion){
                paux = aux;
                aux = aux.sig;
                contador++;
            }
            paux.sig = aux.sig;
        }
        tam--;
    }
   
    public Nodo buscarNodo(Libro libro){
        Nodo aux = inicio;
        while(aux!=null){
            if(aux.libro.equals(libro)){
                return aux;
            }else{
                aux = aux.sig;
            }
        }
        return null;
    } 
    
    public void vaciarLista(){
        while(inicio != null){ // Mientras haya nodos en la lista
            eliminarInicio(); // Esto debería eliminar el primer nodo
        }
    }

  
public void invertirLista() {
    Nodo paux = null; // Este es el nuevo nodo que se va a convertir en el inicio
    Nodo aux = inicio; // Este es el nodo actual que estamos revisando

    while (aux != null) {
        Nodo siguiente = aux.sig; // Guardamos el siguiente nodo antes de cambiar el puntero
        aux.sig = paux; // Invertimos el enlace del nodo actual
        paux = aux; // Movemos paux al nodo actual
        aux = siguiente; // Avanzamos al siguiente nodo guardado
    }

    // Después de invertir, paux será el nuevo inicio
    fin = inicio; // El antiguo inicio se convierte en el nuevo fin
    inicio = paux; // Actualizamos el inicio de la lista
    fin.sig = null; // Aseguramos que el nuevo fin apunte a null
}
    
    
    @Override
    public String toString() {
        String cad="{";
        Nodo aux=inicio;
        while(aux!=null){ //mientras el nodo actual no sea nulo
            cad+=aux.libro + "\n"; //obtenemos el dato (objeto libro)
            aux=aux.sig; // y ahora nuestro nodo sera el que apunte siguiente
        }
        return cad +"}";
    }
    
    public Nodo getInicio() {
        return inicio;
    }
    public Libro getFin() {
        return fin.libro;
    }
    public int getTam() {
        return tam;
    }  
        


}
