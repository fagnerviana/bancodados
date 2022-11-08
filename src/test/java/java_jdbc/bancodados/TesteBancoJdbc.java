package java_jdbc.bancodados;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
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
	
	@Test
	public void initListar() {
		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			List<Usuario> list = dao.listar();
			for (Usuario usuario: list) {
							System.out.println(usuario);
							System.out.println("**************************");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			Usuario usuario = dao.buscar(3L);
			System.out.println(usuario);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
