package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO{
	
	public Carro getCarroById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}
		}finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Carro> findByName(String name) throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where lower(nome) like ?");
			stmt.setString(1, "%"+name.toLowerCase()+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		
		return carros;		
	}
	
	public List<Carro> findByTipo(String tipo) throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		
		return carros;		
	}
	
	public List<Carro> getCarros() throws SQLException{
		List<Carro> carros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro"); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		
		return carros;		
	}
	
	public Carro createCarro(ResultSet rs) throws SQLException{
		Carro c = new Carro();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setDesc(rs.getString("descricao"));
		c.setUrlFoto(rs.getString("url_foto"));
		c.setUrlVideo(rs.getString("url_video"));		
		c.setLatitude(rs.getString("latitude"));
		c.setLongitude(rs.getString("longitude"));
		c.setTipo(rs.getString("tipo"));
		return c;
	}
	
	public void save(Carro c) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			conn = getConnection();
			if(c.getId() == null){
				stmt1 = conn.prepareStatement("insert into carro (id, nome, descricao, "
						+ "url_foto, url_video, latitude, longitude, tipo) VALUES ("
						+ "seq_carro.nextval, ?, ?, ?, ?, ?, ?, ?) ");
			}else{
				stmt1 = conn.prepareStatement("update carro set nome = ?, "
						+ "descricao = ?, "
						+ "url_foto = ?, "
						+ "url_video = ?, "
						+ "latitude = ?, "
						+ "longitude = ?, "
						+ "tipo = ? "
						+ "where id = ?");
			}
			
			stmt1.setString(1, c.getNome());
			stmt1.setString(2, c.getDesc());
			stmt1.setString(3, c.getUrlFoto());
			stmt1.setString(4, c.getUrlVideo());
			stmt1.setString(5, c.getLatitude());
			stmt1.setString(6, c.getLongitude());
			stmt1.setString(7, c.getTipo());
			
			if(c.getId() != null){
				stmt1.setLong(8, c.getId());
			}
			
			int count = stmt1.executeUpdate();
			
			if(count == 0){
				throw new SQLException("Erro ao inserir Carro");				
			}
			
			if(c.getId() == null){
				stmt2 = conn.prepareStatement("select max(id) from carro"); 
				ResultSet rs = stmt2.executeQuery();
				if(rs.next()){
					c.setId(rs.getLong(1));
				}				
				
			}			
		}finally{
			if(stmt1 != null){
				stmt1.close();
			}
			if(stmt2 != null){
				stmt2.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
		
	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("delete from carro where id = ?");
			stmt.setLong(1,id);			
			int count = stmt.executeUpdate();
			boolean ok = count > 0;			
			return ok;
		}finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}		
	}
	
	
	

}
