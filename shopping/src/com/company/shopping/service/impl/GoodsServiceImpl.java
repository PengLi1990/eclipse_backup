package com.company.shopping.service.impl;

import java.util.List;

import com.company.shopping.bean.Goods;
import com.company.shopping.dao.GoodsDAO;
import com.company.shopping.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDAO goodsDao;
	
	public void setGoodsDao(GoodsDAO goodsDao) {
		this.goodsDao = goodsDao;
	}

	public List<?> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsDao.findAll();
	}




	public void save(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.save(goods);
	}




	public List<?> getByType(int i) {
		// TODO Auto-generated method stub
		return goodsDao.findByProperty("goodstype.id",i);
	}

	public Goods getById(int id) {
		List<?> list = goodsDao.findByProperty("id",id);
		Goods goods = list.size() > 0 ?(Goods)list.get(0):null;
		return goods ;
	}

	public int getTotalPage(int pageSize) {
		// TODO Auto-generated method stub
		return goodsDao.getTotalPage(pageSize);
	}

	public List<?> getPage(int currentPage) {
		// TODO Auto-generated method stub
		return goodsDao.getPage(currentPage);
	}

}
