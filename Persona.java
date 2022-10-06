import java.util.ArrayList;

public class Persona{
    
    private String nombre;
    private ArrayList<Libro> listaLibros;
    private ArrayList<ListaFlashcards> listaListaFlashcards;
    private String metas;
    private String carnet;
    private String contrasenia;


    public Persona() {
        nombre = "";
        listaLibros = new ArrayList<Libro>();
        listaListaFlashcards = new ArrayList<ListaFlashcards>();
        metas = "";
        carnet = "";
        contrasenia = "";
    }

    public Persona(String nombre, ArrayList<Libro> listaLibros, ArrayList<ListaFlashcards> listaListaFlashcards, String metas, String carnet, String contrasenia) {
        this.nombre = nombre;
        this.listaLibros = listaLibros;
        this.listaListaFlashcards = listaListaFlashcards;
        this.metas = metas;
        this.carnet = carnet;
        this.contrasenia = contrasenia;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Libro> getListaLibros() {
        return this.listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public ArrayList<ListaFlashcards> getListaListaFlashcards() {
        return this.listaListaFlashcards;
    }

    public void setListaListaFlashcards(ArrayList<ListaFlashcards> listaListaFlashcards) {
        this.listaListaFlashcards = listaListaFlashcards;
    }

    public String getMetas() {
        return this.metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public String getCarnet() {
        return this.carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String mostrarLibros(){
        String resultado = "";
        int contador = 1;
        resultado = resultado + "\nLIBROS LEÍDOS: \n";
        
        for (Libro libro : listaLibros) {
            resultado = resultado + "\nLibro " + contador + " :";
            resultado = resultado + libro.toString();
            contador ++;
        }
        return resultado;
    }

    public void agregarLibro(String nombre, String tema, int paginas, String idioma){
        Libro actual = new Libro(nombre, tema, paginas, idioma);
        listaLibros.add(actual);
    }

    public void agregarNuevaListaFlashcards(String tema){
        ArrayList<Flashcard> lista = new  ArrayList<Flashcard>();
        ListaFlashcards listaNueva = new ListaFlashcards(tema, lista);
        listaListaFlashcards.add(listaNueva);
    }

    public void agregarFlashcard2(int numLista, String lado1, String lado2){
        ListaFlashcards actual = listaListaFlashcards.get( numLista);
        actual.agregarFlashcard(lado1, lado2);
    }

    public String desplegarListas(){
        String resultado = "";
        int i = 1;
        for (ListaFlashcards lista : listaListaFlashcards) {
            resultado = resultado + "\n" + i + ". " + lista.getTema();
            i++;
        }
        return resultado;
    }

    public String desplegarListaEspecifica(int num){
        ListaFlashcards actual = listaListaFlashcards.get(num-1);
        return actual.toString();
    }

    @Override
    public String toString() {
        return "" +
            "Nombre: " + getNombre() + "\n" +
            "Lista de Libros: " + getListaLibros() + "\n" +
            "Listas de Flashcards: " + getListaListaFlashcards().toString() + "\n" +
            "Metas: " + getMetas() + "\n" +
            "Carnet: " + getCarnet() + "\n";
    }

    
}