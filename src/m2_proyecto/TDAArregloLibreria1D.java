package m2_proyecto;

import java.util.LinkedList;

/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: Libreria1D(clase Arreglo de 1 dimension)
 * Fecha: 28 oct 2024
 */

public class TDAArregloLibreria1D {
    private LinkedList<Libro> listaLibrosLinkedList; // Lista ligada de libros
    private Libro[] listaLibros;  // Arreglo que almacena los libros de la lista ligada
    private TDAListaLigada lista; // Referencia a la lista ligada original

    // Constructor
    public TDAArregloLibreria1D(TDAListaLigada listaLigadaLibros) {
        this.listaLibrosLinkedList = new LinkedList<>();
        this.lista = listaLigadaLibros; // Guarda la referencia a la lista
        cargarLibrosDesdeListaLigada();
    }

    // Método para cargar libros desde la lista ligada a la LinkedList y arreglos
    public Libro[] cargarLibrosDesdeListaLigada() {
        listaLibrosLinkedList.clear();
        Nodo aux = lista.getInicio(); // Usa la lista original
        while (aux != null) {
            Libro libro = aux.libro;
            listaLibrosLinkedList.add(libro);
            aux = aux.sig;
        }
        // Sincronizamos los arreglos con la LinkedList
        actualizarArreglos();
        return listaLibros;
    }

    // Método para actualizar los arreglos a partir de la LinkedList
    private void actualizarArreglos() {
        listaLibros = new Libro[listaLibrosLinkedList.size()];
        
        for(int i = 0; i < listaLibros.length; i++) {
            listaLibros[i] = listaLibrosLinkedList.get(i);
        }
    }
    // Mostrar un libro por su nombre (usando toString)
    public Libro obtenerLibro(String nombreLibro) {
        for (Libro libro : listaLibrosLinkedList) {
            if (libro.getTitulo().equalsIgnoreCase(nombreLibro)) {
                return libro;
            }
        }
        return null; // No se encontró el libro
    }

    // Obtener todas las obras de un autor
    public Libro[] obtenerTodasLasObrasDeCiertoAutor(String autor) {
        LinkedList<Libro> obrasDelAutor = new LinkedList<>();
        for (Libro libro : listaLibrosLinkedList) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                obrasDelAutor.add(libro);
            }
        }
        return obrasDelAutor.toArray(new Libro[0]); //devuelvo un arreglo de tipo libro en un nuevo arreglo no vacio 
    }

    public Libro[] ordenarLibrosPorNombre() {
        // Ordenar libros por nombre usando el algoritmo de intercambio
        for (int i = 0; i < listaLibros.length - 1; i++) {
            for (int j = i + 1; j < listaLibros.length; j++) {
                if (listaLibros[i].getTitulo().compareTo(listaLibros[j].getTitulo()) > 0) {
                    Libro aux = listaLibros[i];
                    listaLibros[i] = listaLibros[j];
                    listaLibros[j] = aux;
                }
            }
        }
        return listaLibros;
    }

    public Libro[] ordenarLibrosPorAutor() {
        // Ordenar libros por autor usando el algoritmo de burbuja
        for (int i = 0; i < listaLibros.length - 1; i++) {
            for (int j = 0; j < listaLibros.length - i - 1; j++) {
                if (listaLibros[j].getAutor().compareTo(listaLibros[j + 1].getAutor()) > 0) {
                    Libro aux = listaLibros[j];
                    listaLibros[j] = listaLibros[j + 1];
                    listaLibros[j + 1] = aux;
                }
            }
        }
        return listaLibros;
    }

    public Libro[] ordenarLibrosPorEditorial() {
        // Ordenar libros por editorial usando el algoritmo de selección
        for (int i = 0; i < listaLibros.length - 1; i++) {
            int posMenor = i;
            for (int j = i + 1; j < listaLibros.length; j++) {
                if (listaLibros[j].getEditorial().compareTo(listaLibros[posMenor].getEditorial()) < 0) {
                    posMenor = j;
                }
            }
            // Intercambio si el mínimo no está en la posición actual
            if (posMenor != i) {
                Libro aux = listaLibros[i];
                listaLibros[i] = listaLibros[posMenor];
                listaLibros[posMenor] = aux;
            }
        }
        return listaLibros;
    }

    public Libro[] ordenarLibrosPorISBN() {
        // Ordenar libros por editorial usando el algoritmo de SHELL
        int n = listaLibros.length;
        for (int mitad = n / 2; mitad > 0; mitad /= 2) {
            for (int i = mitad; i < n; i++) {
                Libro aux = listaLibros[i];
                int j;
                for (j = i; j >= mitad && listaLibros[j - mitad].getISBN() > aux.getISBN(); j -= mitad) {
                    listaLibros[j] = listaLibros[j - mitad];
                }
                listaLibros[j] = aux;
            }
        }
        return listaLibros;
    }

    // Contar el número de ejemplares de un libro en particular
    public int contarEjemplaresDeCiertoLibro(Libro libro) {
        int contador = 0;
        for (Libro l : listaLibrosLinkedList) {
            if (l.equals(libro)) {
                contador++;
            }
        }
        return contador;
    }

    // Buscar libros duplicados en la lista
    public boolean buscarLibrosDuplicados(Libro libro) {
        int contador = 0;
        for (Libro l : listaLibrosLinkedList) {
            if (l.equals(libro)) {
                contador++;
            }
        }
        if(contador>1)
            return true;
        else{
            return false;
        }
    }

// Generar un reporte de la librería con el número de ejemplares
public String generarReporteLibreria() {
    String reporte = ""; // Inicializa la cadena vacía para el reporte
    boolean[] librosAgregados = new boolean[listaLibros.length]; // Arreglo para marcar libros agregados

    for (int i = 0; i < listaLibros.length; i++) {
        // Solo procesar libros que no han sido agregados al reporte
        if (!librosAgregados[i]) {
            // Contar cuántos ejemplares hay del libro
            int contadorEjemplares = 0;
            for (int j = 0; j < listaLibros.length; j++) {
                if (listaLibros[i].getTitulo().equals(listaLibros[j].getTitulo())) {
                    contadorEjemplares++; // Sumar la cantidad de ejemplares
                    librosAgregados[j] = true; // Marcar este libro como agregado
                }
            }
            // Agregar la información del libro al reporte
            reporte += "Libro: " + listaLibros[i].getTitulo() + 
                       ", Autor: " + listaLibros[i].getAutor() + 
                       ", Ejemplares: " + contadorEjemplares + 
                       "\n"; 
        }
    }
    return reporte;
}


    // Obtener libros agregados recientemente (últimos n libros)
    public Libro[] obtenerLibrosAgregadosRecientemente() {
        // obtener los últimos 5 libros agregados, por ejemplo
        int n = Math.min(5, listaLibrosLinkedList.size());
        LinkedList<Libro> librosRecientes = new LinkedList<>(listaLibrosLinkedList.subList(listaLibrosLinkedList.size() - n, listaLibrosLinkedList.size()));
        return librosRecientes.toArray(new Libro[0]);
    }

    // Vaciar la lista de libros
    public void vaciarLista() {
        listaLibrosLinkedList.clear();
            lista.vaciarLista();    
        // Actualizamos los arreglos vaciándolos también
        actualizarArreglos();
    }


    // Contar los ejemplares totales en la librería
    public int contarEjemplaresTotales() {
        return listaLibrosLinkedList.size();
    }

    // Eliminar libros duplicados
public void eliminarLibrosDuplicados() {
    // Crear una lista temporal para almacenar libros únicos
    LinkedList<Libro> librosSinDuplicados = new LinkedList<>();
    
    // Eliminar duplicados de la LinkedList
    for (int i = 0; i < listaLibrosLinkedList.size(); i++) {
        Libro libro = listaLibrosLinkedList.get(i);
        //.contains(libro) verifica si el libro actual ya está presente en librosSinDuplicados.
        if (!librosSinDuplicados.contains(libro)) {
            librosSinDuplicados.add(libro);
        }
        //Si librosSinDuplicados.contains(libro) devuelve false, significa que libro no se encuentra en la lista temporal, por lo que se agrega a esta lista usando librosSinDuplicados.add(libro).
    }
    listaLibrosLinkedList = librosSinDuplicados;

    // Eliminar duplicados en la lista ligada (nodos)
    Nodo actual = lista.getInicio();
    int posicion = 1;
    
    while (actual != null) {
        Libro libroActual = actual.libro;
        Nodo siguiente = actual.sig;
        int siguientePosicion = posicion + 1;
        
        while (siguiente != null) {
            if (libroActual.equals(siguiente.libro)) {
                lista.eliminarNodoPosN(siguientePosicion);
                siguiente = siguiente.sig; // Mover al siguiente nodo después de eliminar
            } else {
                siguiente = siguiente.sig;
                siguientePosicion++;
            }
        }
        actual = actual.sig;
        posicion++;
    }

    // Sincronizamos los arreglos
    actualizarArreglos();
    }

}
