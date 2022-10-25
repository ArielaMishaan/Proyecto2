import java.sql.Connection;

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


	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public String getDb() {
		return db;
	}


	public void setDb(String db) {
		this.db = db;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
