package Menu;

import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import dao.VeiculoDAO;
import to.UsuarioTO;
import to.VeiculoTO;

/**Classe de Menu em JOption das funcionalidades!
 * 
 * @author Jhonatas Oliveira
 * @version 1.0
 *
 */
public class Menu {

	public static void main(String[] args) {
		String aux;
		int escolha, opcao;
		int continuar = 1;
		UsuarioDAO userDAO = new UsuarioDAO();
		UsuarioTO userTO = new UsuarioTO();
		VeiculoTO vt = new VeiculoTO();
		VeiculoDAO vdao = new VeiculoDAO();

		try {
			while (continuar == 1) {
				aux = JOptionPane.showInputDialog("Olá seja bem vindo ao menu Safe car!"
						+ " \nEscolha uma das opções abaixo:" + "\n(1) - Ações Usuario\n" + "(2) - Ações Veiculo\n\n");
				escolha = Integer.parseInt(aux);
				switch (escolha) {
				case 1:
					aux = JOptionPane.showInputDialog("Muito bem! Você escolheu Ações usuario!" + "\n(1) - Inserir"
							+ "\n(2) - Alterar" + "\n(3) - Excluir" + "\n(4) - Listar um Usuario\n");
					opcao = Integer.parseInt(aux);

					if (opcao == 1 || opcao == 2) {


						String nome = JOptionPane.showInputDialog("Digite seu nome");
						String profissao = JOptionPane.showInputDialog("Digite sua profissao");
						String raca = JOptionPane.showInputDialog("Digite sua raça");
						String genero = JOptionPane.showInputDialog("Digite seu genero (M - F - I");
						String nacionalidade = JOptionPane.showInputDialog("Digite sua nacionalidade");
						String sangue = JOptionPane.showInputDialog("Digite seu tipo sanguineo");
						String login = JOptionPane.showInputDialog("Digite seu Login");
						String senha = JOptionPane.showInputDialog("Digite sua senha");

						aux = JOptionPane.showInputDialog("Digite o id do veiculo");
						long idVeiculo = Integer.parseInt(aux);
						aux = JOptionPane.showInputDialog("Digite sua idade");
						int idade = Integer.parseInt(aux);
						aux = JOptionPane.showInputDialog("Digite seu salario");
						double salario = Double.parseDouble(aux);

						userTO.setGenero(genero);
						userTO.setIdade(idade);
						userTO.setLogin(login);
						userTO.setNacionalidade(nacionalidade);
						userTO.setNome(nome);
						userTO.setProfissao(profissao);
						userTO.setRaca(raca);
						userTO.setSalario(salario);
						userTO.setSenha(senha);
						userTO.setTpSanguineo(sangue);
						userTO.setVeiculo(idVeiculo);
					}

					switch (opcao) {
					case 1:
						System.out.println(userDAO.insert(userTO));

						break;
					case 2:
						System.out.println(userDAO.update(userTO));

						break;
					case 3:
						aux = JOptionPane.showInputDialog("Digite a identificação que deseja excluir: ");
						int id = Integer.parseInt(aux);
						System.out.println(userDAO.deleteUser(id));

						break;
					case 4:
						aux = JOptionPane.showInputDialog("Digite a identificação que deseja listar: ");
						int idListar = Integer.parseInt(aux);
						JOptionPane.showMessageDialog(null, userDAO.getUserById(idListar));

						break;

					default:
						break;
					}

					break;

				case 2:
					aux = JOptionPane.showInputDialog("Muito bem! Você escolheu Ações Veiculo!" + "\n(1) - Inserir"
							+ "\n(2) - Alterar" + "\n(3) - Excluir" + "\n(4) - Listar Placa\n");
					opcao = Integer.parseInt(aux);

					if (opcao == 1 || opcao == 2) {
						String nome = JOptionPane.showInputDialog("Digite o nome do veiculo");
						String tipo = JOptionPane.showInputDialog("Digite o tipo de veiculo");
						String cor = JOptionPane.showInputDialog("Digite a cor do veiculo");
						String placa = JOptionPane.showInputDialog("Digite a placa do veiculo");
						String modelo = JOptionPane.showInputDialog("Digite o modelo do veiculo");
						String desc = JOptionPane.showInputDialog("Digite a descricao do veiculo");
						aux = JOptionPane.showInputDialog("Digite a quantidade de tripulantes");
						int tripulantes = Integer.parseInt(aux);
						
						vt.setNmVeiculo(nome);
						vt.setDsVeiculo(desc);
						vt.setModelo(modelo);
						vt.setPlaca(placa);
						vt.setTipo(tipo);
						vt.setCor(cor);
						vt.setTripulantes(tripulantes);
						

					}

					switch (opcao) {
					case 1:
						System.out.println(vdao.insertVehicle(vt));

						break;
					case 2:
						System.out.println(vdao.updateVehicle(vt));

						break;
					case 3:
						aux = JOptionPane.showInputDialog("Digite o id que deseja excluir");
						int id = Integer.parseInt(aux);
						System.out.println(vdao.deleteVehicle(id));

						break;
					case 4:
						String placa = JOptionPane.showInputDialog("Digite a placa do veiculo");
						JOptionPane.showConfirmDialog(null, placa);

						break;

					default:
						break;
					}

					break;

				default:
					break;
				}

			}
			aux = JOptionPane.showInputDialog("Deseja continuar?\n(1) - Sim\n(2) - Não");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
