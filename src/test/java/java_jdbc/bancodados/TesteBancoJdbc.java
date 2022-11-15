package java_jdbc.bancodados;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

import dao.TelefoneDAO;
import dao.UsuarioDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Usuario;

public class TesteBancoJdbc {
	
	@Test
	public void TelefoneSalvar() throws SQLException {
		TelefoneDAO telefonedao = new TelefoneDAO();
		Telefone telefone = new Telefone(); 
		telefone.setId(2);
		telefone.setNumero("(71)3333-4444");
		telefone.setTipo("Celular");
		telefone.setUsuariopessoa(7L);
		
		telefonedao.salvar(telefone);	
		
	}
	
	@Test
	public void testeCarregarUserTelefone() {
		TelefoneDAO dao = new TelefoneDAO();
		
		List<BeanUserFone> beanUserFones = dao.listarUserFone(6L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
		}
		
	}
	
	@Test
	public void TelefoneListar() throws SQLException {
		TelefoneDAO telefonedao = new TelefoneDAO();
		
		try {
			List<Telefone> list = telefonedao.listarTelefone();
			for (Telefone telefone : list) {
				System.out.println(telefone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
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
			System.out.println("Busca realizada com sucesso");
			System.out.println(usuario);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Erro ao tentar realiza busca por ID");
		}
	}
	
	@Test
	public void initAtualizar() {
		
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario objetoBanco = dao.buscar(4L);
			objetoBanco.setNome("Nome Atualizado");
			dao.atualizar(objetoBanco);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

	@Test
	public void initDeletar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.deletar(5l);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
