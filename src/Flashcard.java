/**
 * clase que crea flashcards
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
public class Flashcard {
    private String lado1;
    private String lado2;


    public Flashcard() {
        lado1 = "";
        lado2 = "";
    }


    public Flashcard(String lado1, String lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }


    
    /** 
     * metodo para obtener el lado
     * @return String
     */
    public String getLado1() {
        return this.lado1;
    }

    
    /** 
     * metodo para cambiar el lado
     * @param lado1
     */
    public void setLado1(String lado1) {
        this.lado1 = lado1;
    }

    
    /** 
     * metodo para obtener el lado 
     * @return String
     */
    public String getLado2() {
        return this.lado2;
    }

    
    /** 
     * metodo para cambiar el lado
     * @param lado2
     */
    public void setLado2(String lado2) {
        this.lado2 = lado2;
    }

    
    /** 
     * metodo para mostrar el lado 2 de la flashcard
     * @return String
     */
    public String darVuelta2(){
        return lado2;
    }

    
    /** 
     * metodo para mostrar el lado 1 de la flashcard
     * @return String
     */
    public String darVuelta1(){
        return lado1;
    }

    
    /** 
     * metodo para pasar todo a String
     * @return String
     */
    @Override
    public String toString() {
        return "Lado1: " + getLado1() + "\nLado2: " + getLado2() + "\n";
    }
    
}

