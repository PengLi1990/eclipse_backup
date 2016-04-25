package com.company.shopping.dao;

import java.util.List;

import com.company.shopping.bean.ShopCar;

public interface CarDAO {
	void insert(ShopCar sc);

	List<?> findByProperty(String string, int uid);
}
