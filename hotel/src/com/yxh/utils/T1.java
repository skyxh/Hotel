package com.yxh.utils;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yxh.entity.DinnerTable;
import com.yxh.entity.OrderDetail;

public class T1 {
	private static QueryRunner qr=JdbcUtils.getQuerrRunner();
	public static void main(String[] args) {
		String id="3";
		
		List<OrderDetail> list=findByOrderid(3);
		
		for (OrderDetail orderDetail : list) {
			System.out.println(orderDetail);
		}
		
	}
	
	public static List<OrderDetail> findByOrderid(int id) {
		try {
			String sql ="SELECT * FROM orderdetail where orderId=?";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
