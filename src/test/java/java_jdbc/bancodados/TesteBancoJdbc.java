package java_jdbc.bancodados;

import java.sql.SQLException;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UsuarioDAO;
import model.Usuario;

public class TesteBancoJdbc {
	
	
	@Test
	public void initBanco() throws SQLException {
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		usuario.setId(04);
		usuario.setNome("Viana");
		usuario.setEmail("viana@rmail.com");
		
		usuarioDao.salvar(usuario);
		
	}

}
