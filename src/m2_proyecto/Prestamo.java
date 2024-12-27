package m2_proyecto;

/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: clase sencilla 'Prestamo'
 * Fecha: 28 oct 2024
 */
public class Prestamo {
    private Libro libro;
    private String idUsuario;
    private String usuario;
    private String fechaPrestamo;
    private boolean activo; // true si el préstamo está activo

    public Prestamo(Libro libro, String usuario, String fechaPrestamo,String idUsuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.idUsuario=idUsuario;
        this.activo = true; // Activo al momento de crear el préstamo
    }

    
    public Libro getLibro() {
        return libro;
    }

    public String getUsuario() {
        return usuario;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void devolver(String fechaDevolucion) {
        this.activo = false; // Desactiva el préstamo cuando se devuelve
    }
}
