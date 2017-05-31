package com.yxh.dao;

import java.util.List;

import com.yxh.entity.Orders;
import com.yxh.utils.PageBean;

public interface IOrdersDao {

	void update(Orders orders);
	
	List<Orders> query();

	void add(Orders orders);
	
	int getCount();

	void getAll(PageBean<Orders> pb);

	int getTotalCount();
}
