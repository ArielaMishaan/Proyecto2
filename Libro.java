/**
 * clase que crea los libros
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
public class Libro{
    private String nombre;
    private String tema;
    private int paginas;
    private String idioma;


    public Libro() {
        nombre = "";
        tema = "";
        paginas = 0;
        idioma = "";
    }


    public Libro(String nombre, String tema, int paginas, String idioma) {
        this.nombre = nombre;
        this.tema = tema;
        this.paginas = paginas;
        this.idioma = idioma;
    }


    
    /** 
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return String
     */
    public String getTema() {
        return this.tema;
    }

    
    /** 
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    
    /** 
     * @return int
     */
    public int getPaginas() {
        return this.paginas;
    }

    
    /** 
     * @param paginas
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    
    /** 
     * @return String
     */
    public String getIdioma() {
        return this.idioma;
    }

    
    /** 
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "\n - Nombre: " + getNombre() + "\n" +
            " - Tema: " + getTema() + "\n" +
            " - Paginas: " + getPaginas() + "\n" +
            " - Idioma: " + getIdioma() + "\n";
    }


}

