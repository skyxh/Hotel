package com.yxh.service;

import java.util.List;

import com.yxh.entity.Orders;
import com.yxh.utils.PageBean;

public interface IOrdersService {
	
	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
	
	public void getAll(PageBean<Orders> pb);
}
