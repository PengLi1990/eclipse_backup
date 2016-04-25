package com.company.shopping.service.impl;

import java.util.List;

import com.company.shopping.bean.ShopCar;
import com.company.shopping.dao.CarDAO;
import com.company.shopping.service.CarService;

public class CarServiceImpl implements CarService {

	
	private CarDAO carDao;
	
	public void addCar(ShopCar sc) {
		// TODO Auto-generated method stub
		carDao.insert(sc);
	}

	public void setCarDao(CarDAO carDao) {
		this.carDao = carDao;
	}

	public List<?> getByUid(int uid) {
		// TODO Auto-generated method stub
		return carDao.findByProperty("user.id",uid);
	}
	
	

}
