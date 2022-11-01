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
public class Persona{
    
    //Atributos
    private String nombre;
    private ArrayList<Libro> listaLibros;
    private ArrayList<ListaFlashcards> listaListaFlashcards;
    private String metas;
    private String carnet;
    private String contrasenia;

    //Atributos que interactúan con la base de datos
    protected Conexion conn;

    //Insertar una persona
    private final String INS_PERS = "INSERT INTO persona (nombre,metas,carnet,contrasenia) VALUES (?,?,?,?)";
    //Modificar una persona
    private final String UPD_PERS = "UPDATE persona SET nombre = ?, metas = ?, carnet = ?, contrasenia = ? WHERE carnet = ?";
    //Eliminar una persona
    private final String DEL_PERS = "DELETE from persona WHERE nombre = ?";
	//Seleccionar todos los trabajadores
	private final String SEL_PERS = "SELECT * FROM persona";
	//Seleccionar un trabajador dado su id
	private final String SEL_PERS_CARNET = "SELECT * FROM persona WHERE nombre = ?";
	
    //Constructores

    public Persona() {
        nombre = "";
        listaLibros = new ArrayList<Libro>();
        listaListaFlashcards = new ArrayList<ListaFlashcards>();
        metas = "";
        carnet = "";
        contrasenia = "";
        
        //crear la conexión para la base de datos
        conn = new Conexion();
    }

    public Persona(String nombre, ArrayList<Libro> listaLibros, ArrayList<ListaFlashcards> listaListaFlashcards, String metas, String carnet, String contrasenia) {
        this.nombre = nombre;
        this.listaLibros = listaLibros;
        this.listaListaFlashcards = listaListaFlashcards;
        this.metas = metas;
        this.carnet = carnet;
        this.contrasenia = contrasenia;
        conn = new Conexion();
    }

    public Persona(String nombre, String metas, String carnet, String contrasenia) {
        this.nombre = nombre;
        this.metas = metas;
        this.carnet = carnet;
        this.contrasenia = contrasenia;
        conn = new Conexion();
    }

    //Métodos
    public String insertarPers(){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(INS_PERS, ps.RETURN_GENERATED_KEYS);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombre);
            ps.setString(2, metas);
            ps.setString(3, carnet);
            ps.setString(4, contrasenia);

            //Se ejecuta la consulta
            ps.executeUpdate();

            //Se obtiene le valor autogenerado
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){//Si el resultSet tiene algo
                carnet = rs.getString(3);
                return carnet; //lo devuelve
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

    public boolean modificarPersona(){
        try{
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement (UPD_PERS);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombre);
            ps.setString(2,metas);
            ps.setString(3,carnet);
            ps.setString(4,contrasenia);

            //Se ejecuta la consulta
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarPersona(){
        try {
			//Se obtiene la conexion
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(DEL_PERS);
			//Se llenan los par�metros de la consulta los ?
			ps.setString(1, nombre);
		
			//Se ejecuta la consulta
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
    }

    public Persona[] seleccionarPersonas(){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_PERS);
			ResultSet rs = ps.executeQuery();
			
			//Saber la cantidad de filas seleccionadas
			rs.last(); 
			int cant =rs.getRow();
			rs.beforeFirst();
			
			//Crear el arreglo de trabajadores con los trabajadores de la base de datos
			Persona[] personas = new Persona[cant];
			
			//Se  el rerecorresultSet y se guarda la informaci�n en un arreglo.
			int i = 0;
			while (rs.next()){
				//Se extrae la informaci�n del resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String area = rs.getString("area");
				double salarioB = rs.getDouble("salarioBase");
				int horasAus = rs.getInt("horasAusencia");
				
				//Se crea el trabajador que se guardar� en el arreglo
				Persona personaTemporal = new Persona(nombre, area, nombre, area);
				//Se agrega en la posición i
				personas[i] = personaTemporal;
				i++;
			}
			return personas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Persona seleccionarPersona( String carnet){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_PERS_CARNET);
			ps.setString(3, carnet);
			
			ResultSet rs = ps.executeQuery();
			Persona personaTemporal=null;
			
			//Se averigua si se extrajo alguna fila de la tabla.
			if (rs.next()){
				//Se extrae la informaci�n del resultset
				String nombre = rs.getString("nombre");
                String metas = rs.getString("metas");
				String carnetPers = rs.getString("carnet");
				String contrasenia = rs.getString("contraseña");
				
				//Se crea el trabajador que se devolver�
				personaTemporal = new Persona(nombre, metas, carnetPers, contrasenia);
			}
			return personaTemporal;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
     * metodo para cambiar el nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * metodo para obtener la lista
     * @return ArrayList<Libro>
     */
    public ArrayList<Libro> getListaLibros() {
        return this.listaLibros;
    }

    
    /** 
     * metodo para cambiar la lista
     * @param listaLibros
     */
    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    
    /** 
     * metodo para obtener la lista
     * @return ArrayList<ListaFlashcards>
     */
    public ArrayList<ListaFlashcards> getListaListaFlashcards() {
        return this.listaListaFlashcards;
    }

    
    /** 
     * metodo para cambiar la lista
     * @param listaListaFlashcards
     */
    public void setListaListaFlashcards(ArrayList<ListaFlashcards> listaListaFlashcards) {
        this.listaListaFlashcards = listaListaFlashcards;
    }

    
    /** 
     * metodo para obtener las metas
     * @return String
     */
    public String getMetas() {
        return this.metas;
    }

    
    /** 
     * metodo para cambiar las metas
     * @param metas
     */
    public void setMetas(String metas) {
        this.metas = metas;
    }

    
    /** 
     * metodo para obtener el carnet
     * @return String
     */
    public String getCarnet() {
        return this.carnet;
    }

    
    /** 
     * metodo para cambiar el carnet
     * @param carnet
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    
    /** 
     * metodo para obtener la contraseña
     * @return String
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

    
    /** 
     * metodo para cambiar la constraseña
     * @param contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    /** 
     * metodo para pasar todo a string
     * @return String
     */
    public String mostrarLibros(){
        String resultado = "";
        int contador = 1;
        resultado = resultado + "\nLIBROS LEÍDOS: \n";
        
        for (Libro libro : listaLibros) {
            resultado = resultado + "\nLibro " + contador + " :";
            resultado = resultado + libro.toString();
            contador ++;
        }
        return resultado;
    }

    
    /** 
     * @param nombre
     * @param tema
     * @param paginas
     * @param idioma
     */
    public void agregarLibro(String nombre, String tema, int paginas, String idioma){
        Libro actual = new Libro(nombre, tema, paginas, idioma);
        listaLibros.add(actual);
    }

    
    /** 
     * @param tema
     */
    public void agregarNuevaListaFlashcards(String tema){
        ArrayList<Flashcard> lista = new  ArrayList<Flashcard>();
        ListaFlashcards listaNueva = new ListaFlashcards(tema, lista);
        listaListaFlashcards.add(listaNueva);
    }

    
    /** 
     * @param numLista
     * @param lado1
     * @param lado2
     */
    public void agregarFlashcard2(int numLista, String lado1, String lado2){
        ListaFlashcards actual = listaListaFlashcards.get( numLista);
        actual.agregarFlashcard(lado1, lado2);
    }

    
    /** 
     * @return String
     */
    public String desplegarListas(){
        String resultado = "";
        int i = 1;

        for (ListaFlashcards lista : listaListaFlashcards) {
            resultado = resultado + "\n" + i + ". " + lista.getTema();
            i++;
        }

        return resultado;
    }

    
    /** 
     * @param num
     * @return String
     */
    public String desplegarListaEspecifica(int num){
        ListaFlashcards actual = listaListaFlashcards.get(num-1);
        return actual.toString();
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "" +
            "Nombre: " + getNombre() + "\n" +
            "Lista de Libros: " + getListaLibros() + "\n" +
            "Listas de Flashcards: " + getListaListaFlashcards().toString() + "\n" +
            "Metas: " + getMetas() + "\n" +
            "Carnet: " + getCarnet() + "\n";
    }

    
}
