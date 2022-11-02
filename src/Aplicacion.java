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
                Persona personaTemporal = new Persona(nombre, metas, carnet, contrasenia);
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
        Persona nuevousu = new Persona(nombre, "", carnet, contrasenia);
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
    



    
    
}
