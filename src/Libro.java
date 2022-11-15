

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase que crea los libros
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
public class Libro{
    private String nombre;
    private String tema;
    private int paginas;
    private String idioma;
    private String nombrePropietario;

    //Atributos que interactúan con la base de datos
    private Conexion conn;

    //Insertar un libro
    private final String INS_LIBRO = "INSERT INTO Libro (nombre,tema,paginas,idioma,nombrePropietario) VALUES (?,?,?,?,?)";
    //Modificar un libro
    private final String UPD_LIBRO = "UPDATE Libro SET nombre = ?, tema = ?, paginas = ?, idioma = ?, nombrePropietario = ? WHERE nombre = ?";
    //Eliminar un libro
    private final String DEL_LIBRO = "DELETE from Libro WHERE nombre = ?";
	//Seleccionar todos los libros
	private final String SEL_LIBROS = "SELECT * FROM Libro";
	//Seleccionar un libro dado su nombre
	private final String SEL_LIBRO_NOMBRE = "SELECT * FROM Libro WHERE nombre = ?";
    //Seleccionar libros dado su propietario
    private final String SEL_LIBROS_PROPIETARIO = "SELECT * FROM Libro WHERE nombrePropietario = ?";


    public Libro() {
        nombre = "";
        tema = "";
        paginas = 0;
        idioma = "";
        nombrePropietario = "";
        conn = new Conexion();
    }


    public Libro(String nombre, String tema, int paginas, String idioma, String nombrePropietario) {
        this.nombre = nombre;
        this.tema = tema;
        this.paginas = paginas;
        this.idioma = idioma;
        this.nombrePropietario = nombrePropietario;
        conn = new Conexion();
    }


    //Métodos para la base de datos

    public String insertarLibro(){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(INS_LIBRO, ps.RETURN_GENERATED_KEYS);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombre);
            ps.setString(2, tema);
            ps.setInt(3, paginas);
            ps.setString(4, idioma);
            ps.setString(5, nombrePropietario);

            //Se ejecuta la consulta
            ps.executeUpdate();

            //Se obtiene le valor autogenerado
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){//Si el resultSet tiene algo
                nombre = rs.getString(1);
                return nombre; //lo devuelve
            }
            else{
                return "No se pudo insertar.";
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "No se pudo insertar.";
        }
    }

    public boolean modificarLibro(){
        try{
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement (UPD_LIBRO);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombre);
            ps.setString(2,tema);
            ps.setInt(3,paginas);
            ps.setString(4,idioma);
            ps.setString(5, nombrePropietario);

            //Se ejecuta la consulta
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarLibro(){
        try {
			//Se obtiene la conexion
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(DEL_LIBRO);
			//Se llenan los par�metros de la consulta los ?
			ps.setString(1, nombre);
		
			//Se ejecuta la consulta
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
    }
    
    public Libro[] seleccionarLibros(String propietario){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_LIBROS_PROPIETARIO);
            ps.setString(5, nombrePropietario);
			
            ResultSet rs = ps.executeQuery();
			
			//Saber la cantidad de filas seleccionadas
			rs.last(); 
			int cant =rs.getRow();
			rs.beforeFirst();
			
			//Crear el arreglo de trabajadores con los trabajadores de la base de datos
			Libro[] libros = new Libro[cant];
			
			//Se  el rerecorresultSet y se guarda la informaci�n en un arreglo.
			int i = 0;
			while (rs.next()){
				//Se extrae la informaci�n del resultset
				String nombre = rs.getString("nombre");
				String tema = rs.getString("tema");
				int paginas = rs.getInt("paginas");
				String contrasenia = rs.getString("contrasenia");
                String nombrePropietario = rs.getString("nombrePropietario");
				
				//Se crea el trabajador que se guardar� en el arreglo
				Libro libroTemporal = new Libro(nombre, tema, paginas, contrasenia, nombrePropietario);
				//Se agrega en la posición i
				libros[i] = libroTemporal;
				i++;
			}
			return libros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Libro seleccionarLibro(String nombreLibro){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_LIBRO_NOMBRE);
			ps.setString(1, nombreLibro);
			
			ResultSet rs = ps.executeQuery();
			Libro libroTemporal =null;
			
			//Se averigua si se extrajo alguna fila de la tabla.
			if (rs.next()){
				//Se extrae la informaci�n del resultset
				String nombre = rs.getString("nombre");
                String tema = rs.getString("tema");
				int paginas = rs.getInt("paginas");
				String idioma = rs.getString("idioma");
                String nombrePropietario = rs.getString("nombrePropietario");
				
				//Se crea el trabajador que se devolver�
				libroTemporal = new Libro(nombre, tema, paginas, idioma, nombrePropietario);
			}
			return libroTemporal;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public ArrayList<Libro> seleccionarLibros2(String propietario){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(SEL_LIBROS_PROPIETARIO);
            ps.setString(5, propietario);
            ResultSet rs = ps.executeQuery();
    
            //Crear el arreglo de personas con las personas de la base de datos
            ArrayList<Libro> listaLibros = new ArrayList<Libro>();
    
            //Se el rerecorresultSet y se guarda la información en un arreglo.
            int i = 0;
            while(rs.next()){
                //Se extrae la información del resultset
                String nombre = rs.getString("nombre");
                String tema = rs.getString("tema");
                int paginas = rs.getInt("paginas");
                String idioma = rs.getString("idioma");
                String nombrePropietario = rs.getString("nombrePropietario");
    
                //Se crea la persona que se guarda en el arreglo
                Libro libroTemporal = new Libro(nombre, tema, paginas, idioma, nombrePropietario);
                //Se agrega en la posición i
                listaLibros.add(libroTemporal);
                i++;
            }
            return listaLibros;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    
    /** 
     * metodo para obtener el nombre
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * metodo para modificar el nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * metodo para obtener el tema
     * @return String
     */
    public String getTema() {
        return this.tema;
    }

    
    /** 
     * metodo para modificar el tema
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    
    /** 
     * metodo para obtener paginas
     * @return int
     */
    public int getPaginas() {
        return this.paginas;
    }

    
    /** 
     * metodo para modificar paginas
     * @param paginas
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    
    /** 
     * metodo para obtener el idioma
     * @return String
     */
    public String getIdioma() {
        return this.idioma;
    }

    
    /** 
     * metodo para modificar el idioma
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    
    /** 
     * metodo para transformar todo en String
     * @return String
     */
    @Override
    public String toString() {
        return "\n - Nombre: " + getNombre() + "\n" +
            " - Tema: " + getTema() + "\n" +
            " - Paginas: " + getPaginas() + "\n" +
            " - Idioma: " + getIdioma() + "\n";
    }


}

