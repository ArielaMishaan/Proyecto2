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
    protected Conexion conn;

    
    //Constructores

    public Aplicacion() {
        usuarios = new ArrayList<Persona>();
        
        //crear la conexión para la base de datos
        conn = new Conexion();
    }

    public Aplicacion( ArrayList <Persona> usuarios) {
        this.usuarios = usuarios;
        conn = new Conexion();
    }

    //Métodos

    
    
}
