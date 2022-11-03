/**
 * clase Main
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.util.Scanner;

public class Principal{
    
    /** 
     * metodo para interactuar con el usuario
     * @param args
     */
    public static void main (String[] args){
        Scanner teclado = new Scanner (System.in);
        Persona persona = new Persona();
        int opcion = 0;
        boolean continuar = true;
        
        String menu = "\nLERNEN\n1. Crear Usuario. \n2. Iniciar sesión  \n3. Salir. ";
        
        while (continuar){
            try{
                
                System.out.println(menu);
                System.out.println("\nSeleccione una opción: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                
                switch (opcion){
                    case 1:{ //Crear usuario
                        
                        break;
                    }
                    case 2:{ //Iniciar sesión
                        
                        int segundaOpcion = 0;
                        boolean continuarSesion = true;
                        String menuDos = "\n1. Crear Lista \n2. Estudiar Lista. \n3. Mostrar lista de estudios. \n4. Sección libros. \n5. Metas.\n6. Salir.";

                        while(continuarSesion){
                            System.out.println(menuDos);
                            System.out.println("Seleccione una opción: ");
                            segundaOpcion = teclado.nextInt();
                            teclado.nextLine();

                            switch(segundaOpcion){
                                case 1:{ //Crear lista
                                    
                                    System.out.println("Introduzca el tema de su flashcard: ");
                                    String tema = teclado.nextLine();
                                    persona.agregarNuevaListaFlashcards(tema);
                                    System.out.println("¿Cuántas Flashcards desea crear?");
                                    int cantidad = teclado.nextInt();
                                    teclado.nextLine();
                                    
                                    for (int i = 0; i<cantidad; i++){
                                        System.out.println("Escriba el lado 1 de su flashcard " + (i+1));
                                        String lado1 = teclado.nextLine();
                                        System.out.println("Escriba el lado 2 de su flashcard " + (i+1));
                                        String lado2 = teclado.nextLine();
                                        persona.agregarFlashcard2((persona.getListaListaFlashcards().size()-1), lado1, lado2);
                                    }
                                    break;
                                }
                                case 2: //Estudiar lista
                                case 3:{ //Mostrar lista
                                    
                                    if(persona.getListaListaFlashcards().size() != 0){
                                        System.out.print(persona.desplegarListas());

                                        System.out.println("\n¿Qué lista desea visualizar (número)?");
                                        int num = teclado.nextInt();
                                        teclado.nextLine();
                                        
                                        System.out.println(persona.desplegarListaEspecifica(num));
                                    }

                                    else{
                                        System.out.println("No hay listas para mostrar.");
                                    }

                                    break;

                                }
                                case 4:{ //Sección de libros
                                    
                                    String menuLibros = "\nLIBROS\n1. Ingresar libro\n2. Mostrar libros\n3. Salir de la sección libros";
                                    int opcionLibros = 0;
                                    boolean continuarLibros = true;

                                    while(continuarLibros){
                                        System.out.println(menuLibros);
                                        System.out.println("\nSeleccione una opción: ");
                                        opcionLibros = teclado.nextInt();
                                        teclado.nextLine();

                                        switch (opcionLibros) {

                                            case 1: //Ingresar libro

                                                String nombre = "";
                                                String tema = "";
                                                int paginas = 0;
                                                String idioma = "";
                                                
                                                System.out.println("Ingrese el título del libro que terminó de leer: ");
                                                nombre = teclado.nextLine();
                                                
                                                System.out.println("Ingrese el tema del libro que terminó de leer: ");  
                                                tema = teclado.nextLine();  
                                                
                                                System.out.println("Ingrese el número de páginas del libro que terminó de leer: ");  
                                                paginas = teclado.nextInt();
                                                teclado.nextLine();
                                                
                                                System.out.println("Ingrese el idioma del libro que terminó de leer: ");  
                                                idioma = teclado.nextLine();                                 
                                                
                                                persona.agregarLibro(nombre, tema, paginas, idioma, persona.getNombre());
                                                
                                                break;
                                            
                                            case 2: //Mostrar libros
                                            
                                                if (persona.getListaLibros().size() != 0){
                                                    System.out.println(persona.mostrarLibros());
                                                }
                                                else{
                                                    System.out.println("No hay libros para mostrar.");
                                                }

                                                break;
                                            
                                            case 3: //Salir de la sección de libros
                                                continuarLibros = false;
                                                break;
                                            
                                            default:
                                            break;
                                        }
                                    }

                                    break;
                                }
                                case 5: //metas
                                case 6:
                            }
                        break;
                        }
                    }
                    case 4:{ //Estudiar lista
                        break;
                    }

                    case 7:{ //Metas
                        break;
                    }

                    case 8:{ //Salir
                        continuar = false;
                        System.out.println("Ojalá ganes el parcial pai ;)");
                        break;
                    }
                    default:{
                        System.out.println("Las opciones permitidas son entre 1 a 8.");
                        break;
                    }

                }
            }catch (Exception e){
                teclado.nextLine();
                System.out.println("Entrada incorrecta. Por favor verifique que no ingresó un número incorrecto.");
            }   
        }    
    }
}