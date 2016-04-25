package com.company.book.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.company.book.entity.Book;

public class BookDAO {
	private Connection conn;
	private PreparedStatement ps;
	public BookDAO()
	{
		conn = MysqlConn.getConnection();
	}
	public void insert(Book book) throws Exception{
		String sql = "insert into book value(null,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, book.getBookname());
		ps.setString(2, book.getAuthor());
		ps.setFloat(3, book.getPrice());
		ps.execute();
		ps.close();
	}
	
	public List<Book> select() throws SQLException{
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from book where 1";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setBookname(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setPrice(rs.getFloat(4));
			list.add(book);
		}
		return list;
	}
}
