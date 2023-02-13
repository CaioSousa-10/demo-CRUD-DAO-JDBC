
package Db;

import java.sql.Statement;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DB {
    
//    variavel estatica que apotara para o objeto de conexao con=m o banco de dados
    private static Connection conn = null;
    
//    metodo para fazer a conexao com o banco de dados
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url,props);
            } catch (SQLException e) {
                throw new DbException("Houve um erro ao fazer a conexao com o banco de dados!");
            }
        }
        
        return conn;
    }
//    metodo para fechar a conexao com banco de dados
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException("Houve um erro ao tentar fechar a conexao com o banco de dados!");
            }
        }
    }
//    metodo para carregar as propriedades de conexao com o banco de dados em um objeto do tipo Properties
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException("Houve um erro ao carregar as propriedades de conexao com o banco de dados!");
        }
    }
    
//    metodo para fechar a conexao com um objeto do Statement
    public static void closeStatement(Statement st ){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException("Houve um erro ao fechar o statement!");
            }
        }
    }
    
//   metodo para fechar a conexao com um objeto do tipo ResultSet
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException("Houve um erro ao fechar o ResultSet!");
            }
        }
    }
}
