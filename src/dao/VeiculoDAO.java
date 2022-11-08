package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import to.VeiculoTO;
/**Classe de manipulacao do banco de dados SAafe Car
 * 
 * @author Jhonatas Oliveira
 * @version 1.0
 *
 */
public class VeiculoDAO {
	
	private Connection con ;
	private ArrayList<VeiculoTO> listaVeiculos;
	private VeiculoTO veiculo;
	
	public VeiculoDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	
	/** Metodo responsavel por exibir um veiculo com base na sua placa.
	 * 
	 * @param login
	 * @return arraylist do objeto veiculo com todas suas informacoes.
	 */
	public ArrayList<VeiculoTO> getByPlaca(String placa) {
		PreparedStatement ps = null;
		String sql = "select * from t_sc_veiculo where ds_placa = ?";
		listaVeiculos = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, placa);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				veiculo = new VeiculoTO();
				veiculo.setId(rs.getLong(1));
				veiculo.setNmVeiculo(rs.getString(2));
				veiculo.setTipo(rs.getString(3));
				veiculo.setCor(rs.getString(4));
				veiculo.setPlaca(rs.getString(5));
				veiculo.setModelo(rs.getString(6));
				veiculo.setDsVeiculo(rs.getString(7));
				veiculo.setTripulantes(rs.getInt(8));
				listaVeiculos.add(veiculo);
			}
			con.close();
			rs.close();
			ps.close();
			return listaVeiculos;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
	}
	
	
	/** Metodo responsavel por inserir um veiculo no banco de dados.
	 * 
	 * @param login
	 * @return String se foi inserido ou nao.
	 */
	public String insertVehicle(VeiculoTO veiculo) {
		String sql = "insert into t_sc_veiculo (id_veiculo,"
				+ "nm_carro,"
				+ "tp_veiculo,"
				+ "ds_cor_veiculo,"
				+ "ds_placa,"
				+ "ds_modelo_veiculo,"
				+ "ds_veiculo,"
				+ "qt_tripulantes)"
				+ " values(SQ_SC_VEICULO_SEQUENCE.NEXTVAL,?,?,?,?,?,?,?)";
		
		
		
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, veiculo.getNmVeiculo());;
			ps.setString(2, veiculo.getTipo());;
			ps.setString(3, veiculo.getCor());;
			ps.setString(4, veiculo.getPlaca());;
			ps.setString(5, veiculo.getModelo());;
			ps.setString(6, veiculo.getDsVeiculo());;
			ps.setInt(7, veiculo.getTripulantes());;
			

			ps.executeUpdate();
		
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
	
	/** Metodo responsavel por atualizar um veiculo no banco de dados.
	 * 
	 * @param login
	 * @return String se foi inserido ou nao.
	 */
	public String updateVehicle(VeiculoTO veiculo) {
		String sql = "update t_sc_veiculo set "
				+ "nm_carro = ?,"
				+ "tp_veiculo = ?,"
				+ "ds_cor_veiculo = ?,"
				+ "ds_placa = ?,"
				+ "ds_modelo_veiculo = ?,"
				+ "ds_veiculo = ?,"
				+ "qt_tripulantes = ?"
				+ " where id_veiculo = ?";
		
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, veiculo.getNmVeiculo());;
			ps.setString(2, veiculo.getTipo());;
			ps.setString(3, veiculo.getCor());;
			ps.setString(4, veiculo.getPlaca());;
			ps.setString(5, veiculo.getModelo());;
			ps.setString(6, veiculo.getDsVeiculo());;
			ps.setInt(7, veiculo.getTripulantes());;
			ps.setLong(8, veiculo.getId());
			
			if (ps.executeUpdate() > 0) {
				return "alterado com sucesso";
				
			} else {
				return "erro ao alterar!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/** Metodo responsavel por deletar um veiculo no banco de dados.
	 * 
	 * @param login
	 * @return String se foi inserido ou nao.
	 */
	public String deleteVehicle(int id) {
		String sql = "delete t_sc_veiculo where id_veiculo = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			if (ps.executeUpdate() > 0) {
				return "Excluido com sucesso";
				
			} else {
				return "erro ao Excluir!";
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
