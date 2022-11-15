package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Usuario;

//Classe de persistencia

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	// Salva o ususario no banco de dados
	public void salvar(Usuario usuario) throws SQLException {

		try {
			String sql = "insert into usuario(nome,email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			//insert.setLong(1, usuario.getId()); atualizado no banco o auto incremento utilizando o SEQUENCE
			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getEmail());
			insert.execute();
			connection.commit(); // salva no banco
			System.out.println("Usuario salvo com sucesso");

		} catch (Exception e) {

			try {
				connection.rollback();// revert operação
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// Lista Todos os usuarios
	public List<Usuario> listar() throws SQLException {

		List<Usuario> list = new ArrayList<Usuario>();

		String sql = "Select *from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		//Result retorna o valor contido da Select  
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));

			list.add(usuario);

		}

		return list;
	}
    
	//Buscar Usuario pelo ID
	public Usuario buscar(Long id) throws SQLException {

		Usuario retorno = new Usuario();
		
		String sql = "Select *from usuario where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}
	
	public void atualizar(Usuario usuario) throws SQLException {
		
		try {
			
			String sql = "update usuario set nome = ? where id = "+ usuario.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			
			
		}
		
		
	}
	
	public void deletar(Long id) {
		try {
			
			String sql ="delete from usuario where id = "+id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
}
