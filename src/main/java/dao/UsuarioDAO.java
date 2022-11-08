package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaojdbc.SingleConnection;
import model.Usuario;

//Classe de persistencia

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection =  SingleConnection.getConnection();
	}
	
	public void salvar(Usuario usuario) throws SQLException {
		try {
			String sql="insert into usuario(id, nome,email) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, usuario.getId());
			insert.setString(2, usuario.getNome());
			insert.setString(3,usuario.getEmail());
			insert.execute();
			connection.commit(); //salva no banco
			System.out.println("Usuario salvo com sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
