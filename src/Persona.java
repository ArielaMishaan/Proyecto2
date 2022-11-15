
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
    private int listasEstudiadas;
    private boolean nuevoUsuario;

    //Atributos que interactúan con la base de datos
    private Conexion conn;

    //Insertar una persona
    private final String INS_PERS = "INSERT INTO persona (nombre,metas,carnet,contrasenia,listasEstudiadas) VALUES (?,?,?,?,?)";
    //Modificar una persona
    private final String UPD_PERS = "UPDATE persona SET nombre = ?, metas = ?, carnet = ?, contrasenia = ?, listasEstudiadas = ? WHERE carnet = ?";
    //Eliminar una persona
    private final String DEL_PERS = "DELETE from persona WHERE nombre = ?";
	//Seleccionar todas las personas
	private final String SEL_PERS = "SELECT * FROM persona";
	//Seleccionar una persona dado su nombre
	private final String SEL_PERS_CARNET = "SELECT * FROM persona WHERE nombre = ?";
    //Seleccionar listas de flashcards dado su propietario
    private final String SEL_LISTAS_PROPIETARIO = "SELECT * FROM listaFlashcards WHERE nombrePropietario = ?";
    //Seleccionar libros dado su propietario
    private final String SEL_LIBROS_PROPIETARIO = "SELECT * FROM Libro WHERE nombrePropietario = ?";
	
    //Constructores

    public Persona() {
        nombre = "";
        listaLibros = new ArrayList<Libro>();
        listaListaFlashcards = new ArrayList<ListaFlashcards>();
        metas = "";
        carnet = "";
        contrasenia = "";
        listasEstudiadas = 0;
        nuevoUsuario = true;
        
        //crear la conexión para la base de datos
        conn = new Conexion();
    }

    public Persona(String nombre, ArrayList<Libro> listaLibros, ArrayList<ListaFlashcards> listaListaFlashcards, String metas, String carnet, String contrasenia, int listasEstudiadas, boolean nuevoUsuario) {
        this.nombre = nombre;
        this.listaLibros = listaLibros;
        this.listaListaFlashcards = listaListaFlashcards;
        this.metas = metas;
        this.carnet = carnet;
        this.contrasenia = contrasenia;
        this.listasEstudiadas = listasEstudiadas;
        conn = new Conexion();
        this.nuevoUsuario = nuevoUsuario;
    }

    public Persona(String nombre, String metas, String carnet, String contrasenia, int listasEstudiadas) {
        this.nombre = nombre;
        this.metas = metas;
        this.carnet = carnet;
        this.contrasenia = contrasenia;
        this.listasEstudiadas = listasEstudiadas;
        conn = new Conexion();
        listaLibros = new ArrayList<Libro>();
        listaListaFlashcards = new ArrayList<ListaFlashcards>();
        nuevoUsuario = true;
    }

    //Métodos base de datos

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
            ps.setInt(5, listasEstudiadas);

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

    public void insertarLibrosYListasFlashcards(){
        for (Libro libro : listaLibros) {
            libro.insertarLibro();
        }
        for (ListaFlashcards listaFlashcards : listaListaFlashcards) {
            listaFlashcards.insertarLista();
            listaFlashcards.insertarFlashcards();
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
            ps.setInt(5, listasEstudiadas);
            ps.setString(6,carnet);

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
				String nombre = rs.getString("nombre");
				String metas = rs.getString("metas");
				String carnet = rs.getString("carnet");
				String contrasenia = rs.getString("constrasenia");
                int listasEstudiadas = rs.getInt("listasEstudiadas");
				
				//Se crea el trabajador que se guardar� en el arreglo
				Persona personaTemporal = new Persona(nombre, metas, carnet, contrasenia, listasEstudiadas);
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
                int listasEstudiadas = rs.getInt("listasEstudiadas");
				
				//Se crea el trabajador que se devolver�
				personaTemporal = new Persona(nombre, metas, carnetPers, contrasenia, listasEstudiadas);
			}
			return personaTemporal;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public ArrayList<Persona> seleccionarPersonas2(){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(SEL_PERS);
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
                int listasEstudiadas = rs.getInt("listasEstudiadas");
    
                //Se crea la persona que se guarda en el arreglo
                Persona personaTemporal = new Persona(nombre, metas, carnet, contrasenia, listasEstudiadas);
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

    public ArrayList<ListaFlashcards> seleccionarListas_Propietairo(String propietario){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(SEL_LISTAS_PROPIETARIO);
            ps.setString(1, propietario);
            ResultSet rs = ps.executeQuery();
    
            //Crear el arreglo de personas con las personas de la base de datos
            ArrayList<ListaFlashcards> listaFlashcards = new ArrayList<ListaFlashcards>();
    
            //Se el rerecorresultSet y se guarda la información en un arreglo.
            int i = 0;
            while(rs.next()){
                //Se extrae la información del resultset
                String tema = rs.getString("tema");
                String nombrePropietario = rs.getString("nombrePropietario");
    
                //Se crea la persona que se guarda en el arreglo
                ListaFlashcards listaTemporal = new ListaFlashcards(tema, nombrePropietario);
                //Se agrega en la posición i
                listaFlashcards.add(listaTemporal);
                i++;
            }
            return listaFlashcards;
            
        } catch (SQLException e) {
            // TODO: handle exception
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
            ps.setString(1, propietario);
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

    public void llenarListas(){
        this.listaLibros = seleccionarLibros2(this.nombre);
        this.listaListaFlashcards = seleccionarListas_Propietairo(this.nombre);

        for (ListaFlashcards listaFlashcards : listaListaFlashcards) {
            listaFlashcards.llenarListaBaseDeDatos();
        }
    }

    //otros métodos 

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
     * metodo para obtener la cantidad de listas estudiadas
     * @return int
     */
    public int getListasEstudiadas() {
        return this.listasEstudiadas;
    }

    
    /** 
     * metodo para cambiar la cantidad de listas estudiadas
     * @param listasEstudiadas
     */
    public void setListasEstudiadas(int listasEstudiadas) {
        this.listasEstudiadas = listasEstudiadas;
    }
    
    public boolean getNuevoUsuario(){
        return this.nuevoUsuario;
    }

    public void setNuevoUsuario(boolean nuevoUsuario){
        this.nuevoUsuario = nuevoUsuario;
    }

    
    /** 
     * metodo para pasar todo a string
     * @return String
     */
    public String mostrarLibros(){
        String resultado = "";
        int contador = 1;
        
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
     * Método que agregar un libro dentro de la lista de libros leidos.
     */
    public void agregarLibro(String nombre, String tema, int paginas, String idioma){
        Libro actual = new Libro(nombre, tema, paginas, idioma, this.nombre);
        listaLibros.add(actual);
    }

    
    /** 
     * @param tema
     * Método para crear una lista que crea y agrega una lista de flashcards.
     */
    public void agregarNuevaListaFlashcards(String tema){
        ArrayList<Flashcard> lista = new  ArrayList<Flashcard>();
        ListaFlashcards listaNueva = new ListaFlashcards(tema, lista, this.nombre);
        listaListaFlashcards.add(listaNueva);
    }

    
    /** 
     * @param numLista
     * @param lado1
     * @param lado2
     * Método para crear una flashcard. 
     */
    public void agregarFlashcard2(int numLista, String lado1, String lado2){
        ListaFlashcards actual = listaListaFlashcards.get( numLista);
        actual.agregarFlashcard(lado1, lado2);
    }

    
    /** 
     * @return String
     * Método para mostrar las listas de flashcards que estan creadas en el perfil del usuario. 
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
     * Método que muestra elementos especificos de una lista de flashcard. 
     */
    public String desplegarListaEspecifica(int num){
        ListaFlashcards actual = listaListaFlashcards.get(num-1);
        return actual.toString();
    }
    
    /** 
     * Método que muestra los lados de la flashcard establecida.
     */
    public ArrayList<ArrayList<String>> retornarLados(int num){
        ListaFlashcards actual = listaListaFlashcards.get(num-1);
        return actual.retornarLados();
    }

    /** 
     * Método que muestra las metas del usuario
     */
    public String metas(){
        this.metas = "\nLibros leídos: " + listaLibros.size() + "\nListas estudiadas: " + listasEstudiadas;
        return metas;
    }

    
    /** 
     * @return String
     * Método que imprime todos los atributos de la clase con sus valores.
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
