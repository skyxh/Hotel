package com.yxh.service;

import java.util.List;

import com.yxh.entity.OrderDetail;

public interface IOrderDetailService {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderId(int id);
}
