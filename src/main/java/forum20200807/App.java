package forum20200807;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
		
		public static void main(String[] args) throws Exception {
			Connection conexão = null; 
			try {
				conexão = abrirConexão();
				droparTabelaProdutos(conexão);
				
				criarTabelaProduto(conexão);
				
				inserirDoisMilProdutos(conexão);
				removerDezProdutos(conexão);
				alteraPrimeiroProduto(conexão, "Smart TV 50 Samsung");			
				
				listarNoConsoleTrintaProdutos(conexão);			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conexão.close();
			}
		}
		
		private static void listarNoConsoleTrintaProdutos(Connection conexão) throws Exception {
			Statement getProdutos = null;
			try {
				getProdutos = conexão.createStatement();
				ResultSet resultado = getProdutos.executeQuery("SELECT TOP 30 id, nome, codigo FROM produto ORDER BY ID DESC");
				System.out.println("Produtos...");
				while (resultado.next()) {
					System.out.println("Id: " + resultado.getLong("id") + " Nome: " + resultado.getString("nome") + " Código: " + resultado.getInt("codigo"));
				}		
			} finally {
				getProdutos.close();
			}		
		}
		
		private static void removerDezProdutos(Connection conexao) throws Exception {
			PreparedStatement deletaProdutos = null;
			
			try {
				for(int contador = 1; contador <=10; contador ++) {
					deletaProdutos = conexao.prepareStatement("delete from produto where id =" + contador);
					deletaProdutos.execute();
				}
				
			} finally {
				deletaProdutos.close();
			}
		}

		private static void alteraPrimeiroProduto(Connection conexão, String nomeProduto) throws Exception {
			PreparedStatement alterarAnimal = null;
			try {
				alterarAnimal = conexão.prepareStatement("update produto set nome = ? where id = (select min(id) from produto)");
				alterarAnimal.setString(1, nomeProduto);			
				alterarAnimal.execute();
			} finally {
				alterarAnimal.close();
			}		
			
		}

		private static void droparTabelaProdutos(Connection conexão) throws Exception {
			Statement truncarTabela = null;
			try {
				truncarTabela = conexão.createStatement();
				truncarTabela.execute("DROP TABLE IF EXISTS produto");
			} finally {
				truncarTabela.close();
			}
		}

		private static void inserirDoisMilProdutos(Connection conexão) throws Exception {
			PreparedStatement inserirAnimal = null;
			try {
				inserirAnimal = conexão.prepareStatement("insert into produto (id, nome, codigo) values (?,?,?)");
				for (int contador = 1; contador <= 2000; contador++) {
					inserirAnimal.setLong(1, contador);
					inserirAnimal.setString(2, "Produto# " + contador);
					inserirAnimal.setInt(3, contador);
					inserirAnimal.execute();
				}
			} finally {
				inserirAnimal.close();
			}		
		}
		

		private static void criarTabelaProduto(Connection conexão) throws Exception {
			Statement criarTabela = null;
			try {
				criarTabela = conexão.createStatement();
				criarTabela.execute("CREATE TABLE IF NOT EXISTS produto ("
						+ "id long NOT NULL PRIMARY KEY,"
						+ "nome VARCHAR(255) NOT NULL, codigo INT NOT NULL UNIQUE"
						+ ")");
			} finally {
				criarTabela.close();
			}
		}

		private static Connection abrirConexão() throws Exception {
			Connection c = DriverManager.getConnection("jdbc:h2:~/produtos", "sa", "");
			return c;
		}

	
}
