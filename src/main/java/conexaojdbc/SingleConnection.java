package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

//Clase onde terá as configurações de acesso ao Banco de dados
public class SingleConnection {
	private static String url="jdbc:postgresql://localhost:5434/bancojdbc";
	private static String password= "admin";
	private static String user="postgres";
	private static Connection connection=null;
	
	
	//Criação dos metodos
	static {
		conectar();
	}
	
	public SingleConnection() {
		
	}
	
	private static void conectar() {
		try {
			if(connection==null) {
				Class.forName("org.postgresql.Driver");
				connection=DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				//System.out.println("Conectado com Sucesso");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
