package com.company.shopping.service;

import java.util.List;

import com.company.shopping.bean.ShopCar;

public interface CarService {
	void addCar(ShopCar sc);

	List<?> getByUid(int uid);
}
