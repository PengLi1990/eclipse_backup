package com.company.shopping.action;

import java.util.List;

import com.company.shopping.bean.GType;
import com.company.shopping.bean.Goods;
import com.company.shopping.service.GTService;
import com.company.shopping.service.GoodsService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GoodsAction extends ActionSupport implements ModelDriven<GType>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GoodsService goodsService;
	private GTService gtService;
	
	private int totalPage=0;
	private int currentPage=1;
	private int rowTotal=0;
	
	private String[] goodspic;

	public String[] getGoodspic() {
		return goodspic;
	}

	public void setGoodspic(String[] goodspic) {
		this.goodspic = goodspic;
	}

	public int getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}

	private GType type = new GType();
	
	public void setGtService(GTService gtService) {
		this.gtService = gtService;
	}

	private List<?> list;
	private List<?> gtlist;
	private Goods goods;
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<?> getGtlist() {
		return gtlist;
	}



	public void setGtlist(List<?> gtlist) {
		this.gtlist = gtlist;
	}



	public List<?> getList() {
		return list;
	}



	public void setList(List<?> list) {
		this.list = list;
	}



	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public String list() {
		if(totalPage==0){
			totalPage = goodsService.getTotalPage(10);
		}
		if(totalPage==0){
			rowTotal = goodsService.getAllGoods().size();
		}
		if(currentPage<0){
			currentPage=1;
			
		}if(currentPage>=goodsService.getTotalPage(10)){
			currentPage=goodsService.getTotalPage(10);
		}
		list = goodsService.getPage(currentPage);
		
		for(int i=0;i<list.size();i++){
			Goods g = (Goods) list.get(i);
			String[] pic = g.getGoodspic().substring(1).split(",");
			//((Goods) list.get(i)).setPicpath(pic);
			g.setPicpath(pic);
		}
		
		return "success";
	}
	
	public String addgoodstype() {
		gtlist = gtService.getAll();
		return "success";
	}
	
	public String savetype() {
		gtService.save(type);
		return SUCCESS;
	}
	
	public GType getModel() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String typelist(){
		gtlist = gtService.getAll();
		return SUCCESS;
	}
	
	public String addgoods(){
		gtlist = gtService.getAll();
		return SUCCESS;
	}
	
	public String savegoods() throws Exception{
		System.out.println(goods.getGoodsname());
		System.out.println(goods.getGoodstype().getId());
		goodsService.save(goods);
		return SUCCESS;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
}	
