package m2_proyecto;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.HashMap;
/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: Prestamo2D(clase Arreglo de 2 dimensiones)
 * Fecha: 28 oct 2024
 */

public class TDAArregloPrestamos2D {
    private Prestamo[][] prestamos; // Matriz de préstamos
    private int prestamo; // Fila actual para registrar nuevos préstamos
    private static final int numPrestamos = 0; // Máximo número de préstamos

    // Constructor
    public TDAArregloPrestamos2D() {
        prestamos = new Prestamo[numPrestamos][10]; // Suponemos un máximo de 10 préstamos
        prestamo = 0; // Inicializamos la fila actual
    }

    // Método para registrar un préstamo
    public void registrarPrestamo(Libro libro, String usuario, String fechaPrestamo, String idUsuario) {
        if (prestamo < numPrestamos) {
            prestamos[prestamo][0] = new Prestamo(libro, usuario, fechaPrestamo, idUsuario);
            prestamo++;
        } else {
            System.out.println("Límite de préstamos alcanzado.");
        }
    }

    // Método para registrar la devolución de un préstamo
    public void registrarDevolucion(Libro libro, String usuario) {
        for (int i = 0; i < prestamo; i++) {
            if (prestamos[i][0] != null && prestamos[i][0].getLibro().equals(libro) && prestamos[i][0].getUsuario().equals(usuario)) {
                prestamos[i][0] = null; // Eliminar el préstamo
                break;
            }
        }
    }

    // Método para listar libros activos por usuario
    public Libro[] prestamosActivosPorUsuario(String usuario) {
        Libro[] librosPrestados = new Libro[numPrestamos];
        int contador = 0;

        for (int i = 0; i < prestamo; i++) {
            //si existe el usuario en el arreglo prestamos almacenara el libro 
            if (prestamos[i][0] != null && prestamos[i][0].getUsuario().equals(usuario)) {
                librosPrestados[contador++] = prestamos[i][0].getLibro();
            }
        }

        // Devolver solo los libros prestados
        Libro[] resultado = new Libro[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = librosPrestados[i]; //devuelvo en un arreglo de tipo libro los libros prestados
        }
        return resultado;
    }

    // Método para listar libros prestados en rangos de fechas
    public Libro[] librosPrestadosEnRangosDeFechas(String fechaInicio, String fechaFin) {
    Libro[] librosPrestados = new Libro[numPrestamos];
    int contador = 0;
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate inicio;
    LocalDate fin;

    try {
        inicio = LocalDate.parse(fechaInicio, formatoFecha); //paso al formato correcto la fecha ingresada
        fin = LocalDate.parse(fechaFin, formatoFecha);
    } catch (Exception e) {
        System.out.println("Formato de fecha inválido. Asegúrate de usar el formato YYYY-MM-DD.");
        return new Libro[0]; // Retorna un arreglo vacío si las fechas son inválidas
    }

    for (int i = 0; i < prestamo; i++) {
        if (prestamos[i][0] != null) {
            LocalDate fechaPrestamo = LocalDate.parse(prestamos[i][0].getFechaPrestamo(), formatoFecha); //obtengo las fechas de cada prestamo
            if ((fechaPrestamo.isEqual(inicio) || fechaPrestamo.isAfter(inicio)) && //defino desde que fecha se recuperearan los libros
                (fechaPrestamo.isEqual(fin) || fechaPrestamo.isBefore(fin))) { //defino la fecha limite hasta donde se recuperaran los libros
                librosPrestados[contador++] = prestamos[i][0].getLibro(); //obtengo los libros de esas fechas
            }
        }
    }

    // Devolver solo los libros prestados
    Libro[] resultado = new Libro[contador];
    for (int i = 0; i < contador; i++) {
        resultado[i] = librosPrestados[i];
    }
    return resultado;
    }

public Libro calcularLibroMasPrestado() {
    if (prestamo == 0) return null; // No hay préstamos registrados

    // Mapa para almacenar la cantidad de préstamos de cada libro
    HashMap<Libro, Integer> contadorPrestamos = new HashMap<>(); //por cada libro incrementara el numero  

    // Recorremos el arreglo de préstamos
    for (int i = 0; i < prestamo; i++) {
        if (prestamos[i][0] != null) {
            Libro libroActual = prestamos[i][0].getLibro();

            // Actualizamos el contador en el mapa
            contadorPrestamos.put(libroActual, contadorPrestamos.getOrDefault(libroActual, 0) + 1);
        }
    }

    // Variables para almacenar el libro más prestado y su contador máximo
    Libro libroMasPrestado = null;
    int maxPrestamos = 0;

    // Recorremos el mapa para encontrar el libro con más préstamos
    for (HashMap.Entry<Libro, Integer> entry : contadorPrestamos.entrySet()) {
        if (entry.getValue() > maxPrestamos) {
            maxPrestamos = entry.getValue();
            libroMasPrestado = entry.getKey();
        }
    }

    return libroMasPrestado;
}

    // Método para restringir préstamo de un libro
    public void restringirPrestamo(Libro libro) {
        for (int i = 0; i < prestamo; i++) {
            if (prestamos[i][0] != null && prestamos[i][0].getLibro().equals(libro)) {
                prestamos[i][0] = null; // Restringir el préstamo
                break;
            }
        }
    }

public boolean existePrestamo(String tituloLibro, String fecha, String idUsuario, String nombreUsuario) {
    for (int i = 0; i < prestamos.length; i++) {
        if (prestamos[i][0] != null && 
            prestamos[i][0].getLibro().getTitulo().equals(tituloLibro) && 
            prestamos[i][0].getFechaPrestamo().equals(fecha) && 
            prestamos[i][0].getIdUsuario().equals(idUsuario) && // Comparación correcta
            prestamos[i][0].getUsuario().equals(nombreUsuario)) {
            return true; // Se encontró el préstamo
        }
    }
    return false; // No se encontró el préstamo
}

    // Método para obtener disponibilidad de un libro
    public boolean obtenerDisponibilidadLibro(Libro libro) {
        for (int i = 0; i < prestamo; i++) {
            //si eiste el libro en el arreglo prestamo no esta disponible el libro
            if (prestamos[i][0] != null && prestamos[i][0].getLibro().compareTo(libro)==0) {
                return false; // El libro no está disponible
            }
        }
        return true; // El libro está disponible
    }


    // Método para reiniciar todos los préstamos
    public void reiniciarPrestamos() {
        for (int i = 0; i < prestamo; i++) {
            prestamos[i][0] = null; // Vaciar todos los préstamos
        }
        prestamo = 0; // Reiniciar el índice de fila
    }
}
