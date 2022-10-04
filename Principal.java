import java.util.Scanner;

public class Principal{
    public static void main (String[] args){
        Scanner teclado = new Scanner (System.in);
        Persona persona = new Persona();
        int opcion = 0;
        boolean continuar = true;
        
        String menu = "1. Crear Usuario. \n2. Iniciar sesión \n3. Crear Lista \n4. Estudiar Lista. \n5. Mostrar lista de estudios. \n6. Sección libros. \n7. Metas. \n8. Salir. ";

        while (continuar){
            try{
                System.out.println(menu);
                System.out.println("Seleccione una opción: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                
                switch (opcion){
                    case 1:{
                        
                        break;
                    }
                    case 2:{
                        break;
                    }
                    case 3:{
                        System.out.println("Introduzca el tema de su flashcard: ");
                        String tema = teclado.nextLine();
                        persona.agregarNuevaListaFlashcards(tema);
                        System.out.println("¿Cuántas Flashcards desea crear?");
                        int cantidad = teclado.nextInt();
                        teclado.nextLine();
                        for (int i = 0; i<cantidad; i++){
                            System.out.println("Escriba el lado 1 de su flashcard");
                            String lado1 = teclado.nextLine();
                            System.out.println("Escriba el lado 2 de su flashcard");
                            String lado2 = teclado.nextLine();
                            persona.agregarFlashcard2(persona.getListaListaFlashcards().size()-1, lado1, lado2);
                        }
                        break;
                    }
                    case 4:{
                        break;
                    }
                    case 5:{
                        break;
                    }
                    case 6:{
                        break;
                    }
                    case 7:{
                        break;
                    }
                    case 8:{
                        break;
                    }
                    default:{
                        System.out.println("Las opciones permitidas son entre 1 a 8.");
                        break;
                    }

                }
            }catch (Exception e){
                teclado.nextLine();
                System.out.println("Entreda incorrecta. Por favor verifique que no ingresó un número incorrecto.");
            }   
        }    
    }
}