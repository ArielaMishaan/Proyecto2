

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;

/**
 * clase que crea flashcards
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
public class Flashcard {
    
    //Atributos
    private String nombrePropietario;
    private String nombreLista;
    private String lado1;
    private String lado2;

    //Atributos que interactúan con la base de datos
    private Conexion conn;

    //Insertar una flashcard
    private final String INS_FLASH = "INSERT INTO flashcard (nombrePropietario,nombreLista,lado1,lado2) VALUES (?,?,?,?)";
    //Modificar una flashcard
    private final String UPD_FLASH = "UPDATE flashcard SET nombrePropietario = ?, nombreLista = ?, lado1 = ?, lado2 = ? WHERE lado1 = ?";
    //Eliminar una flashcard
    private final String DEL_FLASH = "DELETE from flashcard WHERE lado1 = ?";
	//Seleccionar todas las flashcards
	private final String SEL_FLASHCARDS = "SELECT * FROM flashcard";
	//Seleccionar una flashcard dado su lado 1
	private final String SEL_FLASH_LADO1 = "SELECT * FROM flashcard WHERE lado1 = ?";
    //Seleccionar todas las flashcards de uan persona, de una lista específica
    private final String SEL_FLASH_PROPIETARIO = "SELECT * FROM flashcard WHERE nombrePropietario = ? AND nombreLista = ?";


    public Flashcard() {
        nombrePropietario = "";
        nombreLista = "";
        lado1 = "";
        lado2 = "";
        conn = new Conexion();
    }


    public Flashcard(String lado1, String lado2, String nombrePropietario, String nombreLista) {
        this.nombrePropietario = nombrePropietario;
        this.nombreLista = nombreLista;
        this.lado1 = lado1;
        this.lado2 = lado2;
        conn = new Conexion();
    }

    //Métodos para la base de datos

    public String insertarFlashcard(){
        try {
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement(INS_FLASH, ps.RETURN_GENERATED_KEYS);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombrePropietario);
            ps.setString(2, nombreLista);
            ps.setString(3, lado1);
            ps.setString(4, lado2);

            //Se ejecuta la consulta
            ps.executeUpdate();

            //Se obtiene le valor autogenerado
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){//Si el resultSet tiene algo
                lado1 = rs.getString(3);
                return lado1; //lo devuelve
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

    public boolean modificarFlashcard(){
        try{
            //Se obtiene la conexión
            java.sql.Connection conexion = conn.getConn();
            //Se prepara la consulta y se le pide al preparedStatement que permita obtener el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = conexion.prepareStatement (UPD_FLASH);
            //Se llenan los parámetros de la consulta los ?,?,?,?
            ps.setString(1, nombrePropietario);
            ps.setString(2,nombreLista);
            ps.setString(3,lado1);
            ps.setString(4,lado2);

            //Se ejecuta la consulta
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarFlashcard(){
        try {
			//Se obtiene la conexion
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(DEL_FLASH);
			//Se llenan los par�metros de la consulta los ?
			ps.setString(3, lado1);
		
			//Se ejecuta la consulta
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
    }
    
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
    
    public Flashcard seleccionarFlashcard(String lado){
		try {
			java.sql.Connection conexion = conn.getConn(); 
			//Se prepara la consulta y se le pide al preparedStament que permita obtener el valor autogenerado
			java.sql.PreparedStatement ps = null;
			ps = conexion.prepareStatement(SEL_FLASH_LADO1);
			ps.setString(1, lado);
			
			ResultSet rs = ps.executeQuery();
			Flashcard flashcardTemporal =null;
			
			//Se averigua si se extrajo alguna fila de la tabla.
			if (rs.next()){
				//Se extrae la informaci�n del resultset
				String nombrePropietario = rs.getString("nombrePropietario");
                String nombreLista = rs.getString("nombreLista");
				String lado1 = rs.getString("lado1");
                String lado2 = rs.getString("lado2");
				
				//Se crea el trabajador que se devolver�
				flashcardTemporal = new Flashcard(lado1, lado2, nombrePropietario, nombreLista);
			}
			return flashcardTemporal;

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

    public ArrayList<Flashcard> seleccionarFlashcards_Propietario(String propietario){
        try {
            //Se prepara la consulta y se le pide al preparedStatement que permite obteneer el valor autogenerado
            java.sql.PreparedStatement ps = null;
            ps = ((Connection) conn).prepareStatement(SEL_FLASH_PROPIETARIO);
            ps.setString(5, propietario);
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

    /** 
     * metodo para obtener el lado
     * @return String
     */
    public String getLado1() {
        return this.lado1;
    }

    
    /** 
     * metodo para cambiar el lado
     * @param lado1
     */
    public void setLado1(String lado1) {
        this.lado1 = lado1;
    }

    
    /** 
     * metodo para obtener el lado 
     * @return String
     */
    public String getLado2() {
        return this.lado2;
    }

    
    /** 
     * metodo para cambiar el lado
     * @param lado2
     */
    public void setLado2(String lado2) {
        this.lado2 = lado2;
    }

    
    /** 
     * metodo para mostrar el lado 2 de la flashcard
     * @return String
     */
    public String darVuelta2(){
        return lado2;
    }

    
    /** 
     * metodo para mostrar el lado 1 de la flashcard
     * @return String
     */
    public String darVuelta1(){
        return lado1;
    }

    
    /** 
     * metodo para pasar todo a String
     * @return String
     */
    @Override
    public String toString() {
        return "Lado1: " + getLado1() + "\nLado2: " + getLado2() + "\n";
    }
    
}

