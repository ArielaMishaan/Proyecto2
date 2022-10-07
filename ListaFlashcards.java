/**
 * clase la cual crea una lista de falshcards. Llama al lado uno y dos
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.util.ArrayList;

public class ListaFlashcards{
    private String tema;
    private ArrayList<Flashcard> listaFlashcards;

    public ListaFlashcards() {
        tema = "";
        listaFlashcards = new ArrayList<Flashcard>();
    }

    public ListaFlashcards(String tema, ArrayList<Flashcard> listaFlashcards) {
        this.tema = tema;
        this.listaFlashcards = listaFlashcards;
    }

    
    /** 
     * metodo para obtener el tema 
     * @return String
     */
    public String getTema() {
        return this.tema;
    }

    
    /** 
     * metodo para cambiar el tema
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    
    /** 
     * metodo para obtener la lista
     * @return ArrayList<Flashcard>
     */
    public ArrayList<Flashcard> getListaFlashcards() {
        return this.listaFlashcards;
    }

    
    /** 
     * metodo para cambiar la lista
     * @param listaFlashcards
     */
    public void setListaFlashcards(ArrayList<Flashcard> listaFlashcards) {
        this.listaFlashcards = listaFlashcards;
    }

    
    /** 
     * metodo para agregar la flashcard
     * @param lado1
     * @param lado2
     */
    public void agregarFlashcard(String lado1, String lado2){
        Flashcard nueva = new Flashcard(lado1, lado2);
        listaFlashcards.add(nueva);
    }

    
    /** 
     * metodo para transformar todo en String
     * @return String
     */
    @Override
    public String toString() {
        String resultado = "";
        int i = 1;

        for (Flashcard flashcard : listaFlashcards) {
            resultado = resultado + "\n\nFlashcard " + i + "\n " + flashcard.toString();
        }

        return resultado;
    }


    

}