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


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getPaginas() {
        return this.paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Nombre:" + getNombre() + "\n" +
            "Tema:" + getTema() + "\n" +
            "Paginas:" + getPaginas() + "\n" +
            "Idioma:" + getIdioma() + "\n";
    }


}