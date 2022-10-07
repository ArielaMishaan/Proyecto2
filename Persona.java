/**
 * clase controladora
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.util.ArrayList;
public class Persona{
     /*
     ogomrg
      */
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
     * @return ArrayList<Libro>
     */
    public ArrayList<Libro> getListaLibros() {
        return this.listaLibros;
    }

    
    /** 
     * @param listaLibros
     */
    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    
    /** 
     * @return ArrayList<ListaFlashcards>
     */
    public ArrayList<ListaFlashcards> getListaListaFlashcards() {
        return this.listaListaFlashcards;
    }

    
    /** 
     * @param listaListaFlashcards
     */
    public void setListaListaFlashcards(ArrayList<ListaFlashcards> listaListaFlashcards) {
        this.listaListaFlashcards = listaListaFlashcards;
    }

    
    /** 
     * @return String
     */
    public String getMetas() {
        return this.metas;
    }

    
    /** 
     * @param metas
     */
    public void setMetas(String metas) {
        this.metas = metas;
    }

    
    /** 
     * @return String
     */
    public String getCarnet() {
        return this.carnet;
    }

    
    /** 
     * @param carnet
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    
    /** 
     * @return String
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

    
    /** 
     * @param contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    /** 
     * @return String
     */
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

    
    /** 
     * @param nombre
     * @param tema
     * @param paginas
     * @param idioma
     */
    public void agregarLibro(String nombre, String tema, int paginas, String idioma){
        Libro actual = new Libro(nombre, tema, paginas, idioma);
        listaLibros.add(actual);
    }

    
    /** 
     * @param tema
     */
    public void agregarNuevaListaFlashcards(String tema){
        ArrayList<Flashcard> lista = new  ArrayList<Flashcard>();
        ListaFlashcards listaNueva = new ListaFlashcards(tema, lista);
        listaListaFlashcards.add(listaNueva);
    }

    
    /** 
     * @param numLista
     * @param lado1
     * @param lado2
     */
    public void agregarFlashcard2(int numLista, String lado1, String lado2){
        ListaFlashcards actual = listaListaFlashcards.get( numLista);
        actual.agregarFlashcard(lado1, lado2);
    }

    
    /** 
     * @return String
     */
    public String desplegarListas(){
        String resultado = "";
        int i = 1;
        for (ListaFlashcards lista : listaListaFlashcards) {
            resultado = resultado + "\n" + i + ". " + lista.getTema();
            i++;
        }
        return resultado;
    }

    
    /** 
     * @param num
     * @return String
     */
    public String desplegarListaEspecifica(int num){
        ListaFlashcards actual = listaListaFlashcards.get(num-1);
        return actual.toString();
    }

    
    /** 
     * @return String
     */
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
