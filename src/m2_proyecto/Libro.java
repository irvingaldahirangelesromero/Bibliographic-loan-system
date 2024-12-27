package m2_proyecto;

import java.util.Objects;

/**
 * Universidad Tecnologica de la Huasteca Hidalguiense
 * TSU En Tecnologias de la Informacion Area Desarrollo de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Clase: Libro
 * Fecha: 28 oct 2024
 */

public class Libro implements Comparable {
    private String titulo;
    private String autor;
    private String editorial;
    private final long ISBN;

    public Libro() {
        this.titulo = "";
        this.autor = "";
        this.editorial = "";
        this.ISBN = 0;
    }
    public Libro(String titulo, String autor, String editorial, long ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.ISBN = ISBN;
    }
    
    @Override
    public String toString() {
        return "Libro [ISBN: " + ISBN + ", titulo: " + titulo + ", autor: " + autor + ", editorial: " + editorial + ']';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (this.ISBN != other.ISBN) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return Objects.equals(this.editorial, other.editorial);
    } 

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public long getISBN() {
        return ISBN;
    }    

    @Override
    public int compareTo(Object obj) {
        final Libro other = (Libro) obj;
        if (this.editorial != other.editorial) {
            return 1;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return 1;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return 1;
        }
        return (Objects.equals(this.ISBN, other.ISBN)==true)?0:1;
    }
}
