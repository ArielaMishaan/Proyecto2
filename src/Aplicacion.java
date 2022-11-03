import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;

/**
 * clase controladora
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
public class Aplicacion{
    
    //Atributos
    private ArrayList<Persona> usuarios;

    //Atributos que interactúan con la base de datos
    private Conexion conn;
    //Seleccionar todas las personas
	private final String SEL_PERS = "SELECT * FROM persona";

    //Constructores

    public Aplicacion() {
        usuarios = new ArrayList<Persona>();
    }

    public Aplicacion( ArrayList <Persona> usuarios) {
        this.usuarios = usuarios;
    }

    //Base de datos
    public ArrayList<Persona> seleccionarPersonas2(){
        try {
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = ((Connection) conn).prepareStatement(SEL_PERS);
            ResultSet rs = ps.executeQuery();
    
            //Crear el arreglo de personas con las personas de la base de datos
            ArrayList<Persona> usuarios = new ArrayList<Persona>();
    
            //Se el rerecorresultSet y se guarda la información en un arreglo.
            int i = 0;
            while(rs.next()){
                //Se extrae la información del resultset
                String nombre = rs.getString("nombre");
                String carnet = rs.getString("carnet");
                String metas = rs.getString("metas");
                String contrasenia = rs.getString("contrasenia");
    
                //Se crea la persona que se guarda en el arreglo
                Persona personaTemporal = new Persona(nombre, metas, carnet, contrasenia, 0);
                //Se agrega en la posición i
                usuarios.add(personaTemporal);
                i++;
            }
            return usuarios;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    
    public void llenarListasUsuarios(){
        this.usuarios = seleccionarPersonas2();
        for (Persona usuario : usuarios) {
            usuario.llenarListas();
        }
    }

    //Métodos

    public int confirmar(String usuario, String contrasena){
        int index = -1;
        int i =0;
        for(Persona person : usuarios){
            if(person.getNombre().equalsIgnoreCase(usuario) && person.getContrasenia().equalsIgnoreCase(contrasena)){
                index = i;
            }
            i++;
        }
        return index;
    }

    public void crear(String nombre, String contrasenia, String carnet){
        Persona nuevousu = new Persona(nombre, "", carnet, contrasenia, 0);
        usuarios.add(nuevousu);
    }

    public void agregarlibro(int index, String nombre, String tema, int paginas, String idioma){
        Persona usuariotem = usuarios.get(index);
        String nombrePropietario = usuariotem.getNombre();
        usuariotem.agregarLibro(nombre, tema, paginas, idioma, nombrePropietario);
    }

    public void agregarNuevaListaFlashcards(int index, String tema){
        Persona usuariotem = usuarios.get(index);
        usuariotem.agregarNuevaListaFlashcards(tema);
    }
    
    public String mostrarLibros(int index){
        Persona usuariotem = usuarios.get(index);
        return usuariotem.mostrarLibros();
    }
    
    public void agregarFlashcard2(int index, int numLista, String lado1, String lado2){
        Persona usuariotem = usuarios.get(index);
        usuariotem.agregarFlashcard2(numLista, lado1, lado2);
    }
    
    public String desplegarListas(int index){
        Persona usuariotem = usuarios.get(index);
        return usuariotem.desplegarListas();
    }
    
    public String desplegarListaEsp(int index, int numLista){
        Persona usuariotem = usuarios.get(index);
        return usuariotem.desplegarListaEspecifica(numLista);
    }
    
    public String mostrarLado1(int index, int numLista, int flashcard){
        Persona usuariotem = usuarios.get(index);
        ArrayList<ArrayList<String>> lista = usuariotem.retornarLados(numLista);
        
        return lista.get(flashcard).get(0);
        
    }
    
    public boolean verificarLado2(int index, int numLista, int flashcard, String lado2){
        boolean resultado = false;
        Persona usuariotem = usuarios.get(index);
        ArrayList<ArrayList<String>> lista = usuariotem.retornarLados(numLista);

        if (lista.get(flashcard).get(1).equalsIgnoreCase(lado2)){
            resultado = true;
        }
        
        return resultado;
        
    }

    public void agregarListaEstudiada(int index){
        Persona usuariotem = usuarios.get(index);
        int cantidad = usuariotem.getListasEstudiadas();
        usuariotem.setListasEstudiadas(cantidad + 1);

    }

    
    
}
