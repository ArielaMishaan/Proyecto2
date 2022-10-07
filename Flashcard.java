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
     * @return String
     */
    public String getLado1() {
        return this.lado1;
    }

    
    /** 
     * @param lado1
     */
    public void setLado1(String lado1) {
        this.lado1 = lado1;
    }

    
    /** 
     * @return String
     */
    public String getLado2() {
        return this.lado2;
    }

    
    /** 
     * @param lado2
     */
    public void setLado2(String lado2) {
        this.lado2 = lado2;
    }

    
    /** 
     * @return String
     */
    public String darVuelta2(){
        return lado2;
    }

    
    /** 
     * @return String
     */
    public String darVuelta1(){
        return lado1;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Lado1: " + getLado1() + "\n" +
            "Lado2: " + getLado2() + "\n";
    }
    
}

