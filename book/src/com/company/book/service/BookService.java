package com.company.book.service;

import java.sql.SQLException;
import java.util.List;

import com.company.book.dao.BookDAO;
import com.company.book.entity.Book;

public class BookService {
	
	private BookDAO bookDao;
	
	public BookService(){
		bookDao = new BookDAO();
	}
	
	public void save(Book book)
	{
		try {
			bookDao.insert(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Book> list(){
		try {
			return bookDao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
