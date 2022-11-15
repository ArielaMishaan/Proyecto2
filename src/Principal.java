package src;

/**
 * clase Main
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.util.Scanner;
import java.util.Random;

public class Principal{
    
    /** 
     * metodo para interactuar con el usuario
     * @param args
     */
    public static void main (String[] args){
        
        Scanner teclado = new Scanner (System.in);
        Aplicacion app = new Aplicacion();
        Random rand = new Random();
        int opcion = 0;
        boolean continuar = true;

        app.llenarListasUsuarios();
        app.insertarInfoBaseDeDatos();
        
        String menu = "\n===== LERNEN =====\n1. Crear Usuario. \n2. Iniciar sesión  \n3. Salir. ";
        
        while (continuar){
            try{
                
                System.out.println(menu);
                System.out.println("\nSeleccione una opción: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                
                switch (opcion){
                    case 1:{ //Crear usuario
                        boolean entradaInco = true;
                        System.out.println("===== BIENVENIDO =====");
                        while(entradaInco){
                            System.out.println("Ingrese su nombre de usuario: ");
                            String nombre = teclado.nextLine();
                            System.out.println("Ingrese su numero de carnet: ");
                            String carnet = teclado.nextLine();
                            System.out.println("Ingrese la contraseña a utilizar: ");
                            String contrasenia = teclado.nextLine();
                            if(app.crear(nombre, contrasenia, carnet)){
                                System.out.println("\nEl usuario ha sido creado exitosamente.");
                                entradaInco = false;
                            }
                            else if(app.crear(nombre, contrasenia, carnet) == false){
                                System.out.println("Lo sentimos, ya existe un usuario con el mismo nombre/carnet.");
                            }
                        }
                        break;
                    }
                    case 2:{ //Iniciar sesión
                        boolean salir2 = true;
                        int opcion2 = 0;
                        String menuDos = "\n===== LERNEN =====\n1. Crear Lista \n2. Estudiar Lista. \n3. Mostrar lista de estudios. \n4. Sección libros. \n5. Metas.\n6. Salir.";
                        System.out.println("\n=====BIENVENIDO=====");
                        System.out.println("Ingrese su nombre de usuario: ");
                        String usuario = teclado.nextLine();
                        System.out.println("Ingrese su contraseña: ");
                        String contrasena = teclado.nextLine();
                        int index = app.confirmar(usuario, contrasena);
                        if(index >= 0){
                            while(salir2){

                                System.out.println(menuDos);
                                System.out.println("\nIngrese una opción: ");
                                opcion2 = teclado.nextInt();
                                teclado.nextLine();
    
                                switch(opcion2){
    
                                    case 1: {
                                        System.out.println("Introduzca el tema de su flashcard: ");
                                        String tema = teclado.nextLine();
                                        app.agregarNuevaListaFlashcards(index, tema);
                                        System.out.println("¿Cuántas Flashcards desea crear?");
                                        int cantidad = teclado.nextInt();
                                        teclado.nextLine();
    
                                        for(int i = 0; i<cantidad; i++){
                                            System.out.println("Escriba el lado 1 de su flashcard " + (i+1));
                                            String lado1 = teclado.nextLine();
                                            System.out.println("Escriba el lado 2 de su flashcard " + (i+1));
                                            String lado2 = teclado.nextLine();
                                            app.agregarFlashcard2(index, (app.getUsuarios().get(index).getListaListaFlashcards().size()-1), lado1, lado2);
                                        }
                                        break;
                                    }
                                    
                                    case 2: {
                                        int contador = 0;
                                        int correctas = 0;
                                        System.out.println(app.desplegarListas(index));
                                        System.out.println("Elija la lista que desea estudiar: (Ingrese el número)");
                                        int n = teclado.nextInt();
                                        teclado.nextLine();
                                        int cantidad = app.cantidadFlashcards(index, n);
                                        while (contador < cantidad){
                                            int flashcard = rand.nextInt(cantidad);
                                            String lado1 = app.mostrarLado1(index, n, flashcard);
                                            System.out.println("Flashcard " + (contador + 1) + ": " + lado1 + "\nIngrese la respuesta: ");
                                            String respuesta = teclado.nextLine();
                                            
                                            if (app.verificarLado2(index, n, flashcard, respuesta)){
                                                System.out.println("¡Correcto! Tu respuesta es correcta.\n");
                                                correctas++;
                                            }
                                            else{
                                                System.out.println("¡Oops! Tu respuesta es incorrecta.\n");
                                            }
                                            contador++;
                                        }
                                        if (correctas == cantidad){
                                            app.agregarListaEstudiada(index);
                                            System.out.println("¡Felicidades! Has terminado de estudiar esta lista.");
                                        }
                                        else{
                                            System.out.println("No has estudiado esta lista por completo. Sigue intentándolo para completar el estudio.");
                                        }
                                        break;
                                    }
    
                                    case 3: {
                                        System.out.println("\n===== LISTAS GUARDADAS =====");
                                        String listas = app.desplegarListas(index);
                                        if(listas.equalsIgnoreCase("")){
                                            System.out.println("Usted no tiene listas guardadas");
                                        }
                                        else{
                                            System.out.println(listas);
                                            System.out.println("\nIngrese el numero de la lista a observar: ");
                                            int numLista = teclado.nextInt();
                                            System.out.println(app.desplegarListaEsp(index, numLista));
                                        }
                                        break;
                                    }
    
                                    case 4: {
                                        System.out.println("\n===== LIBROS =====\n");
                                        String menuLibros = "\nLIBROS\n1. Ingresar libro\n2. Mostrar libros\n3. Salir de la sección libros";
                                        boolean selibro = true;
                                        while(selibro){
                                            System.out.println(menuLibros);
                                            System.out.println("\nSeleccione una opción: ");
                                            int opcionLibros = teclado.nextInt();
                                            teclado.nextLine();

                                            switch (opcionLibros) {

                                                case 1: //Ingresar libro
                                                    {
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
                                                        
                                                        app.agregarlibro(index, nombre, tema, paginas, idioma);
                                                        break;
                                                    }

                                                case 2: {
                                                    System.out.println("\n ===== LIBROS GUARDADOS =====");
                                                    String guardados = app.mostrarLibros(index);
                                                    if(guardados.equalsIgnoreCase("")){
                                                        System.out.println("Usted no tiene libros guardados");
                                                    }
                                                    else{
                                                        System.out.println(guardados);
                                                    }

                                                    break;

                                                }

                                                case 3: {
                                                    selibro = false;
                                                    break;
                                                }
                                            }
                                        }
                                    }
    
                                    case 5: {
                                        System.out.println("\n ===== METAS =====");
                                        System.out.println(app.metas(index));
                                        break;
                                    }
    
                                    case 6: {
                                        salir2 = false;
                                        break;
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("\nEl nombre de usuario/contraseña ingresadas no son correctas. Verifique su entrada.");
                            break;
                        }
                        break;
                    }
                    
                    case 3: { //Salir
                        app.actualizarDatos();
                        continuar = false;
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

