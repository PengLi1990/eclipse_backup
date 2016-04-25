package com.company.shopping.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.company.shopping.bean.Goods;
import com.company.shopping.bean.Order;
import com.company.shopping.bean.User;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("all")
public class OrderAction extends ActionSupport{
	
	private int gid;
	
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}


	/**
	 * �����û��ύ�Ķ���
	 * @return
	 */
	public String addOrder(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		user.getId();
		
		Order order = new Order();
		order.setBuytime(new Date());
		Set<Goods> goods = new HashSet<Goods>();
		Goods g = new Goods();
		g.setId(gid);
		goods.add(g);
		order.setGoods(goods);
		String ordernum = "1234567";
		order.setOrdernum(ordernum);
		order.setUser(user);
		order.setTotal(1000f);
		order.setStatus("0");
		
		//���һ��service���order.save(order)
		//��service���doa�� �ο�����ģ��
		
		return SUCCESS;
	}
	
	public String showOrder(){
		
		return SUCCESS;
	}
}
