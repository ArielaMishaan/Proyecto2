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
     * metodo para obtener el nombre
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * metodo para modificar el nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * metodo para obtener el tema
     * @return String
     */
    public String getTema() {
        return this.tema;
    }

    
    /** 
     * metodo para modificar el tema
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    
    /** 
     * metodo para obtener paginas
     * @return int
     */
    public int getPaginas() {
        return this.paginas;
    }

    
    /** 
     * metodo para modificar paginas
     * @param paginas
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    
    /** 
     * metodo para obtener el idioma
     * @return String
     */
    public String getIdioma() {
        return this.idioma;
    }

    
    /** 
     * metodo para modificar el idioma
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    
    /** 
     * metodo para transformar todo en String
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

