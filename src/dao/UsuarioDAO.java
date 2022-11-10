package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import to.UsuarioTO;

/**Classe de manipulacao do banco de dados SAafe Car
 * 
 * @author Jhonatas Oliveira
 * @version 1.0
 *
 */
public class UsuarioDAO {
	
	private ArrayList<UsuarioTO> listaUsers;
	private UsuarioTO usuario;

	
	private Connection con = null;

	public UsuarioDAO() {

		this.con = new ConnectionFactory().getConnection();

	}
		
	
	/** Metodo responsavel por exibir um usuario com base no seu id.
	 * 
	 * @param login
	 * @return arraylist do objeto usuario com todas suas informacoes.
	 */
	public String getUserById(int id) {
		String sql = "select * from t_sc_usuario where id_usuario = ?";
		ArrayList<UsuarioTO> listaUsuarios = new ArrayList<>();
		String listaUser = "Lista do usuario de id: " + id + "\n\n";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			
			if (rs.next()) {
				listaUser += "id do veiculo: " + rs.getLong(2) + "\n";
				listaUser += "Nome: " + rs.getString(3) + "\n";
				listaUser += "Idade: " + rs.getInt(4) + "\n";
				listaUser += "Profissao: " + rs.getString(5) + "\n";
				listaUser += "Salario: " + rs.getDouble(6) + "\n";
				listaUser += "Raça: " + rs.getString(7) + "\n";
				listaUser += "Genero: " + rs.getString(8) + "\n";
				listaUser += "Nacionalidade: " + rs.getString(9) + "\n";
				listaUser += "Tipo Sanguineo: " + rs.getString(10) + "\n";
				listaUser += "Login: " + rs.getString(11) + "\n";
				listaUser += "Senha: " + rs.getString(12) + "\n";
				return listaUser;
						
			}
			return listaUser;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	/** Metodo responsavel por inserir um usuario no sistema..
	 * 
	 * @param login
	 * @return String validando se foi bem sucedido ou nao.
	 */

	public String insert(UsuarioTO user) {
		PreparedStatement ps = null;

		String sqlUser = "insert into t_sc_usuario (id_usuario," + "id_veiculo," + "nm_usuario," + "ds_idade_usuario,"
				+ "ds_profissao_usuario," + "ds_salario_usuario," + "ds_raca_usuario," + "ds_genero,"
				+ "ds_nacionalidade," + "tp_sanguinio," + "ds_login,"
				+ "ds_senha) values (sq_sc_usuario_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			// User insert
			ps = con.prepareStatement(sqlUser);
			ps.setLong(1, user.getVeiculo());
			ps.setString(2, user.getNome());
			ps.setInt(3, user.getIdade());
			ps.setString(4, user.getProfissao());
			ps.setDouble(5, user.getSalario());
			ps.setString(6, user.getRaca());
			ps.setString(7, user.getGenero());
			ps.setString(8, user.getNacionalidade());
			ps.setString(9, user.getTpSanguineo());
			ps.setString(10, user.getLogin());
			ps.setString(11, user.getSenha());
			
			if (ps.executeUpdate() > 0) {
				return "inserido com sucesso";
				
			} else {
				return "erro ao inserir!";
			}				
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/** Metodo responsavel por atualizar um usuario no sistema..
	 * 
	 * @param login
	 * @return String validando se foi bem sucedido ou nao.
	 */


	public String update(UsuarioTO user) {
		PreparedStatement ps = null;
		String sql = "update t_sc_usuario set " + " nm_usuario = ?," + "ds_idade_usuario = ?,"
				+ "ds_profissao_usuario = ?," + "ds_salario_usuario = ?," + "ds_raca_usuario = ?," + "ds_genero = ?,"
				+ "ds_nacionalidade = ?," + "tp_sanguinio = ?," + "ds_login = ?," + "ds_senha = ? "
				+ "where id_usuario = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setInt(2, user.getIdade());
			ps.setString(3, user.getProfissao());
			ps.setDouble(4, user.getSalario());
			ps.setString(5, user.getRaca());
			ps.setString(6, user.getGenero());
			ps.setString(7, user.getNacionalidade());
			ps.setString(8, user.getTpSanguineo());
			ps.setString(9, user.getLogin());
			ps.setString(10, user.getSenha());
			ps.setLong(11, user.getId());

			
			if (ps.executeUpdate() > 0) {
				return "alterado com sucesso";

			} else {
				return "Erro ao alterar";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/** Metodo responsavel por deletar um usuario no sistema..
	 * 
	 * @param login
	 * @return String validando se foi bem sucedido ou nao.
	 */

	public String deleteUser(long id) {

		String sqlEnd = "delete t_sc_endereco where id_usuario = ?";
		String sqlCon = "delete t_sc_contato where id_usuario = ?";
		String sqlPlan = "delete from t_sc_plano_saude where id_usuario = ?";
		String sql = "delete  t_sc_usuario where id_usuario = ?";

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps = null;

		try {
			ps1 = con.prepareStatement(sqlEnd);
			ps1.setLong(1, id);
			
			ps2 = con.prepareStatement(sqlCon);
			ps2.setLong(1, id);
			
			ps3 = con.prepareStatement(sqlPlan);
			ps3.setLong(1, id);
		
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			
			ps2.executeUpdate();
			ps3.executeUpdate();
			ps1.executeUpdate();
			ps.executeUpdate();
			
			con.close();
			ps.close();
			ps2.close();
			ps3.close();
			return "Excluido com sucesso";



		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao excluir";
		}

	}

	


}
