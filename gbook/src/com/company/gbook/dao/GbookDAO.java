package com.company.gbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.gbook.bean.Gbook;
import com.company.gbook.conn.DBConn;



public class GbookDAO {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	
	public GbookDAO(){
		conn = DBConn.getConn();
	}
	
	public void insert(Gbook g) throws SQLException{
		String sql = "insert into tbl_gbook value(null,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, g.getUsername());
		ps.setString(2,g.getTitle());
		ps.setString(3, g.getContent());
		ps.executeUpdate();
		ps.close();
	}
	
	public List<Gbook> select() throws SQLException{
		List<Gbook> list = new ArrayList<Gbook>();
		String sql = "select * from tbl_gbook where 1";
		st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			Gbook user = new Gbook();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString(2));
			user.setTitle(rs.getString(3));
			user.setContent(rs.getString(4));
			list.add(user);
		}
		st.close();
		return list ;
	}

//	public List<User> select(String condition) throws SQLException {
//		List<User> list = new ArrayList<User>();
//		String sql = "select * from tbl_user where "+condition;
//		st = conn.createStatement();
//		ResultSet rs = st.executeQuery(sql);
//		while(rs.next()){
//			User user = new User();
//			user.setId(rs.getInt("id"));
//			user.setUsername(rs.getString(2));
//			user.setPassword(rs.getString(3));
//			user.setRule(rs.getInt(4));
//			user.setDeptno(rs.getInt(5));
//			list.add(user);
//		}
//		st.close();
//		return list ;
//	}

	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_gbook where id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		
	}
	
}
