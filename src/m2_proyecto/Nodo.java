package m2_proyecto;

/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: clase sencilla 'Prestamo'
 * Fecha: 28 oct 2024
 */
public class Nodo {
        Libro libro;
        Nodo sig;

        public Nodo(Libro libro){ //Para insertar y que despues apunte a NULL
            this.libro = libro;
            this.sig = null;
        }
    
        public Nodo(Libro libro, Nodo sig){
            this.libro = libro;
            this.sig = sig;
        } 
}
