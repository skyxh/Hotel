package com.yxh.dao;

import java.util.List;

import com.yxh.entity.OrderDetail;

public interface IOrderDetailDao {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(int id);
}
