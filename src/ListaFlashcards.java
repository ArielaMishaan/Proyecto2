/**
 * clase la cual crea una lista de falshcards. Llama al lado uno y dos
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class ListaFlashcards{
    private String tema;
    private ArrayList<Flashcard> listaFlashcards;
    private String nombrePropietario;

    //Atributos que interactúan con la base de datos
    private Conexion conn;

    //Seleccionar todas las flashcards de uan persona
    private final String SEL_FLASH_PROPIETARIO = "SELECT * FROM flashcard WHERE nombrePropietario = ? AND nombreLista = ?";
    //Insertar una lista de flashcards
    private final String INS_LISTA = "INSERT INTO listaFlashcards (tema,nombrePropietario) VALUES (?,?)";
    //Modificar una lista de flashcards
    private final String UPD_LISTA = "UPDATE listaFlashcards SET tema = ? nombrePropietario = ? WHERE tema = ? AND nombrePropietario = ?";
    //Eliminar una lista de flashcards
    private final String DEL_LISTA = "DELETE from listaFlashcards WHERE tema = ? AND nombrePropietario = ?";
	//Seleccionar una lista de flashcards dado su nombre (tema)
	private final String SEL_LISTA_NOMBRE = "SELECT * FROM listaFlashcards WHERE tema = ?";
    //Seleccionar listas de flashcards dado su propietario
    private final String SEL_LISTAS_PROPIETARIO = "SELECT * FROM listaFlashcards WHERE nombrePropietario = ?";
    //Seleccionar lista de flashcards por nombre y propietario
    private final String SEL_LISTA_NOMBRE_PROPIETARIO = "SELECT * FROM listaFlashcards WHERE tema = ? AND nombrePropietario = ?";
    //Insertar una flashcard
    private final String INS_FLASH = "INSERT INTO flashcard (nombrePropietario,nombreLista,lado1,lado2) VALUES (?,?,?,?)";

    //constructores

    public ListaFlashcards() {
        tema = "";
        listaFlashcards = new ArrayList<Flashcard>();
        nombrePropietario = "";
        conn = new Conexion();
    }

    public ListaFlashcards(String tema, ArrayList<Flashcard> listaFlashcards, String nombrePropietario) {
        this.tema = tema;
        this.listaFlashcards = listaFlashcards;
        this.nombrePropietario = nombrePropietario;
        conn = new Conexion();
    }

    public ListaFlashcards(String tema, String nombrePropietario){
        this.tema = tema;
        this.nombrePropietario = nombrePropietario;
        listaFlashcards = new ArrayList<Flashcard>();
        conn = new Conexion();
    }

    //métodos base de datos
    
    public Flashcard[] seleccionarFlashcards(String propietario){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_FLASH_PROPIETARIO);
            ps.setString(5, nombrePropietario);
			
            ResultSet rs = ps.executeQuery();
			
			//Saber la cantidad de filas seleccionadas
			rs.last(); 
			int cant =rs.getRow();
			rs.beforeFirst();
			
			//Crear el arreglo de trabajadores con los trabajadores de la base de datos
			Flashcard[] listaFlashcards = new Flashcard[cant];
			
			//Se  el rerecorresultSet y se guarda la informaci�n en un arreglo.
			int i = 0;
			while (rs.next()){
				//Se extrae la informaci�n del resultset
				String nombrePropietario = rs.getString("nombrePropietario");
				String  nombreLista = rs.getString("nombreLista");
				String lado1 = rs.getString("lado1");
                String lado2 = rs.getString("lado1");
				
				//Se crea el trabajador que se guardar� en el arreglo
                Flashcard flashcardTemporal = new Flashcard(lado1, lado2, nombrePropietario, nombreLista);
				//Se agrega en la posición i
				listaFlashcards[i] = flashcardTemporal;
				i++;
			}
			return listaFlashcards;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public ArrayList<Flashcard> seleccionarFlashcards_Propietario_NombreLista(String propietario, String lista){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(SEL_FLASH_PROPIETARIO);
            ps.setString(5, propietario);
            ps.setString(2, lista);
            ResultSet rs = ps.executeQuery();
    
            //Crear el arreglo de personas con las personas de la base de datos
            ArrayList<Flashcard> listaFlashcards = new ArrayList<Flashcard>();
    
            //Se el rerecorresultSet y se guarda la información en un arreglo.
            int i = 0;
            while(rs.next()){
                //Se extrae la información del resultset
                String nombrePropietario = rs.getString("nombre");
                String nombreLista = rs.getString("lado1");
                String lado1 = rs.getString("idioma");
                String lado2 = rs.getString("lado2");
    
                //Se crea la persona que se guarda en el arreglo
                Flashcard flashcardTemporal = new Flashcard(lado1, lado2, nombrePropietario, nombreLista);
                //Se agrega en la posición i
                listaFlashcards.add(flashcardTemporal);
                i++;
            }
            return listaFlashcards;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    public void llenarListaBaseDeDatos(){
        this.listaFlashcards = seleccionarFlashcards_Propietario_NombreLista(this.nombrePropietario, this.tema);
    }

    public String insertarLista(){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(INS_LISTA, ps.RETURN_GENERATED_KEYS);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, tema);
            ps.setString(2, nombrePropietario);

            //Se ejecuta la consulta
            ps.executeUpdate();

            //Se obtiene le valor autogenerado
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){//Si el resultSet tiene algo
                tema = rs.getString(1);
                return tema; //lo devuelve
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

    public void insertarFlashcards(){
        for (Flashcard flashcard : listaFlashcards) {
            flashcard.insertarFlashcard();
        }
    }

    public boolean modificarLista(){
        try{
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement (UPD_LISTA);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, tema);
            ps.setString(2,nombrePropietario);

            //Se ejecuta la consulta
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarLista(){
        try {
			//Se obtiene la conexion
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(DEL_LISTA);
			//Se llenan los par�metros de la consulta los ?
			ps.setString(1, tema);
            ps.setString(2, nombrePropietario);
		
			//Se ejecuta la consulta
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
    }

    public ListaFlashcards seleccionarLista_tema(String nombreLista){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_LISTA_NOMBRE);
			ps.setString(1, nombreLista);
			
			ResultSet rs = ps.executeQuery();
			ListaFlashcards listaTemporal =null;
			
			//Se averigua si se extrajo alguna fila de la tabla.
			if (rs.next()){
				//Se extrae la informaci�n del resultset
				String tema = rs.getString("tema");
                String nombrePropietario = rs.getString("nombrePropietario");
				
				//Se crea el trabajador que se devolver�
                listaTemporal = new ListaFlashcards(tema, nombrePropietario);
			}
			return listaTemporal;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public ListaFlashcards seleccionarLista_propietario_nombre(String propietario, String nombreLista){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_LISTAS_PROPIETARIO);
            ps.setString(1, nombreLista);
			ps.setString(2, propietario);
			
			ResultSet rs = ps.executeQuery();
			ListaFlashcards listaTemporal =null;
			
			//Se averigua si se extrajo alguna fila de la tabla.
			if (rs.next()){
				//Se extrae la informaci�n del resultset
				String tema = rs.getString("tema");
                String nombrePropietario = rs.getString("nombrePropietario");
				
				//Se crea el trabajador que se devolver�
                listaTemporal = new ListaFlashcards(tema, nombrePropietario);
			}
			return listaTemporal;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public ArrayList<ListaFlashcards> seleccionarListas_Propietairo(String propietario){
        try {
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = ((Connection) conn).prepareStatement(SEL_LISTAS_PROPIETARIO);
            ps.setString(2, propietario);
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
    
    /** 
     * metodo para obtener el tema 
     * @return String
     */
    public String getTema() {
        return this.tema;
    }

    
    /** 
     * metodo para cambiar el tema
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    
    /** 
     * metodo para obtener la lista
     * @return ArrayList<Flashcard>
     */
    public ArrayList<Flashcard> getListaFlashcards() {
        return this.listaFlashcards;
    }

    
    /** 
     * metodo para cambiar la lista
     * @param listaFlashcards
     */
    public void setListaFlashcards(ArrayList<Flashcard> listaFlashcards) {
        this.listaFlashcards = listaFlashcards;
    }

    
    /** 
     * metodo para agregar la flashcard
     * @param lado1
     * @param lado2
     */
    public void agregarFlashcard(String lado1, String lado2){
        Flashcard nueva = new Flashcard(lado1, lado2, this.nombrePropietario, this.tema);
        listaFlashcards.add(nueva);
    }

    public ArrayList<ArrayList<String>> retornarLados(){
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();

        for (Flashcard flashcard : listaFlashcards) {
            ArrayList<String> lados = new ArrayList<String>();
            lados.add(flashcard.getLado1());
            lados.add(flashcard.getLado2());
            lista.add(lados);
        }
        return lista;
    }

    
    /** 
     * metodo para transformar todo en String
     * @return String
     */
    @Override
    public String toString() {
        String resultado = "";
        int i = 1;

        for (Flashcard flashcard : listaFlashcards) {
            resultado = resultado + "\nFlashcard " + i + "\n " + flashcard.toString();
            i++;
        }

        return resultado;
    }


    

}