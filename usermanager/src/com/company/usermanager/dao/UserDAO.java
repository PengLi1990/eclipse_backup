package com.company.usermanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.usermanager.bean.User;
import com.company.usermanager.conn.DBConn;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
		conn = DBConn.getConn();
	}
	
	public void insert(User user) throws SQLException{
		String sql = "insert into tbl_user value(null,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getRule());
		ps.setInt(4, user.getDeptno());
		ps.setString(5,user.getPic());
		ps.executeUpdate();
		ps.close();
	}
	
	public List<User> select() throws SQLException{
		String sql = "select * from tbl_user where 1";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		List<User> list = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setRule(rs.getInt(4));
			user.setDeptno(rs.getInt(5));
			user.setPic(rs.getString(6));
			list.add(user);
		}
		return list;
	}
	
	public void delete(int id) throws SQLException{
		String sql = "delete from tbl_user where id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		
	}
	
	public void update(User user){
		
	}

	public List<User> select(String condition) throws SQLException {
		String sql = "select * from tbl_user where "+condition;
		System.out.println(sql);
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		List<User> list = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setRule(rs.getInt(4));
			user.setDeptno(rs.getInt(5));
			list.add(user);
		}		
		st.close();
		return list;
	}


}
