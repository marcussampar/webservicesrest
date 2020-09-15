package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	public BaseDAO(){	
		//Driver do oracle
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();		
		}
	}
	
	protected Connection getConnection() throws SQLException {
		String URL = "jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain";		
		String USER = "dummy";
		String PASS = "dummy";
		Connection conn = DriverManager.getConnection(URL,USER,PASS);
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		//Testando conexão
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
