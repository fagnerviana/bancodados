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
			String sql = "insert into usuario(id, nome,email) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, usuario.getId());
			insert.setString(2, usuario.getNome());
			insert.setString(3, usuario.getEmail());
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
}
