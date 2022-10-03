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

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public ArrayList<Flashcard> getListaFlashcards() {
        return this.listaFlashcards;
    }

    public void setListaFlashcards(ArrayList<Flashcard> listaFlashcards) {
        this.listaFlashcards = listaFlashcards;
    }

    public void agregarFlashcard(String lado1, String lado2){
        Flashcard nueva = new Flashcard(lado1, lado2);
        listaFlashcards.add(nueva);
    }

    @Override
    public String toString() {
        String resultado = "";
        for (Flashcard flashcard : listaFlashcards) {
            resultado = resultado + flashcard.toString();
        }

        return resultado;
    }


    

}