package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
    public static String usuario = "root";
    public static String senha = "admin";
    public static String textoDeConexao = "jdbc:mariadb://127.0.0.1:3306/avaliacao1";


    public static Connection obterConexao() {
        try {
            Connection con = DriverManager.getConnection(
                    textoDeConexao, usuario, senha);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}