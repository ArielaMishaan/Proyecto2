package src;

/**
 * clase que recolecta los datos
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
	private Connection conn;
	private String db;
	private String url;
	private String usuario;
	private String contrasenia;
	
	
	public Conexion(Connection conn, String db, String url, String usuario, String contrasenia) {
		super();
		this.conn = conn;
		this.db = db;
		this.url = url;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Conexion(){
		url = "jdbc:mysql://192.168.64.2/localhost:3306/";
		db = "proyectoPOO";
		usuario = "root";
		contrasenia = "";
	}

	
	/** 
	 * @throws SQLException
	 */
	public void conectar() throws SQLException{
		String connString = "";
		connString = url + db;

		conn = DriverManager.getConnection(connString, usuario, contrasenia);
	}


	
	/** 
	 * @return Connection
	 */
	public java.sql.Connection getConn() {
		try{
			//Si la conexión no existe o está cerrada
			if(conn == null || conn.isClosed()){
				conectar();
			}
			return conn;

		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	public void cerrar(){
		try{
			if(!conn.isClosed()){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	
	/** 
	 * @param conn
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}


	
	/** 
	 * @return String
	 */
	public String getDb() {
		return db;
	}


	
	/** 
	 * @param db
	 */
	public void setDb(String db) {
		this.db = db;
	}


	
	/** 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}


	
	/** 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	
	/** 
	 * @return String
	 */
	public String getUsuario() {
		return usuario;
	}


	
	/** 
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	
	/** 
	 * @return String
	 */
	public String getContrasenia() {
		return contrasenia;
	}


	
	/** 
	 * @param contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
