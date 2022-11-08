package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**Classe de configuracoes de conexao no banco de dados.
 * 
 * @author Jhonatas Oliveira
 * @version 1.0
 *
 */

public class ConnectionFactory {
	
    private Connection conexao = null;
    private String USER = "RM93423";
    private String PASS = "260404";
    
    public Connection getConnection() {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", USER, PASS );
			return conexao;
    		
    		
    		
		} catch (SQLException e) {
			e.printStackTrace();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
    	return null;
    }
	

}
