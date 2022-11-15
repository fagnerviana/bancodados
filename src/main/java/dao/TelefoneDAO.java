package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Telefone;


public class TelefoneDAO {
	//Criar conexao com o BD
	private Connection connection;
	
	
	public TelefoneDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Telefone telefone) throws SQLException{
		
		try {
			String sql = "INSERT INTO telefoneuser(numero,tipo,usuariopessoa ) VALUES (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			//insert.setLong(1, telefone.getId());
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuariopessoa());		
			insert.execute();
			connection.commit(); // salva no banco
			System.out.println("Telefone salvo com sucesso");
						
			
		} catch (Exception e) {
			try {
				connection.rollback();// revert operação
				System.out.println("Telefone não foi salvo com sucesso");
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Telefone não foi salvo com sucesso 2");
			}
			e.printStackTrace();
			System.out.println("Telefone não foi salvo com sucesso 3");
		}
		
		
	}
	 

	public List<Telefone> listarTelefone() throws SQLException {
		
		List<Telefone> lista = new ArrayList<Telefone>();
		
		String sql = "Select *from telefoneuser";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			Telefone telefone = new Telefone();
			telefone.setId(resultado.getLong("id"));
			telefone.setNumero(resultado.getString("numero"));
			telefone.setTipo(resultado.getString("tipo"));
			telefone.setUsuariopessoa(resultado.getLong("usuariopessoa"));
			lista.add(telefone);
			
		}
		
		return lista;
	}
}
