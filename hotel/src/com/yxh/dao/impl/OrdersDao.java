package com.yxh.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yxh.dao.IOrdersDao;
import com.yxh.entity.Orders;
import com.yxh.utils.JdbcUtils;
import com.yxh.utils.PageBean;

public class OrdersDao implements IOrdersDao{

	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	@Override
	public void add(Orders orders) {
		String sql =" INSERT orders(table_id,orderDate,totalPrice) VALUES(?,?,?)";
		try {
			qr.update(sql,orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getCount(){
		String sql ="select count(*) from orders";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void update(Orders orders) {
		String sql = "UPDATE orders SET orderStatus =? WHERE id=?";
		try {
			qr.update(sql,orders.getOrderStatus(),orders.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		
	}

	@Override
	public List<Orders> query() {
		String sql = "SELECT * FROM orders";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void getAll(PageBean<Orders> pb) {
		
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					   
		} else if (pb.getTotalPage()!=0 && pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		
		}
		
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		
		int count = pb.getPageCount();							
		String sql = "select * from orders limit ?,?";
		
		try {
			List<Orders> pageData = qr.query(sql, new BeanListHandler<Orders>(Orders.class), index, count);
			pb.setPageData(pageData);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from orders";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
