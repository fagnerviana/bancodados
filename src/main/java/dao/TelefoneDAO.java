package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;


public class TelefoneDAO {
	//Criar conexao com o BD
	private Connection connection;
		
	public TelefoneDAO() {
		connection = SingleConnection.getConnection();
	}
	
    public List<BeanUserFone> listarUserFone(Long idUser){
		
		List<BeanUserFone> beanUserFone = new ArrayList<BeanUserFone>();
		String sql = "select  numero,nome,email from telefoneuser as fone \r\n"
				+ "inner join usuario as userp\r\n"
				+ "on fone.usuariopessoa = userp.id\r\n"
				+ "where userp.id= "+idUser;
		try {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultado.getString("email"));
				userFone.setNome(resultado.getString("nome"));
				userFone.setNumero(resultado.getString("numero"));
				
				beanUserFone.add(userFone);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return beanUserFone;
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
