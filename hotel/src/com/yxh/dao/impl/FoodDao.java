package com.yxh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yxh.dao.IFoodDao;
import com.yxh.entity.Food;
import com.yxh.utils.Condition;
import com.yxh.utils.JdbcUtils;
import com.yxh.utils.PageBean;

public class FoodDao implements IFoodDao{

	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	@Override
	public void add(Food food) {
		String sql =" INSERT food(foodName,foodType_id,price,mprice,remark,img) VALUES(?,?,?,?,?,?);";
		try {
			qr.update(sql, food.getFoodName(),food.getFoodType_id(),
					food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql ="DELETE FROM food WHERE id=?";
			qr.update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updata(Food food) {
		try {
			String sql ="UPDATE food SET foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? WHERE id =?";
			qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),
					food.getMprice(),food.getRemark(),food.getImg(),food.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Food> query() {
		try {
			String sql ="SELECT * FROM food";
			return  qr.query(sql,new BeanListHandler<Food>(Food.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Food findById(int id) {
		try {
			String sql ="SELECT * FROM food where id =?";
			return qr.query(sql,new BeanHandler<Food>(Food.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> query(String keyword) {
		try {
			String sql ="SELECT * FROM food WHERE foodName LIKE ?";
			return qr.query(sql,new BeanListHandler<Food>(Food.class) , "%"+keyword+"%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getTotalCount(PageBean<Food> pb) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append(" SELECT");
		sb.append("   count(*) ");
		sb.append(" FROM ");
		sb.append("     	food f,");
		sb.append("     	foodtype ft");
		sb.append(" WHERE 	1=1 ");
		sb.append("     	AND f.foodType_id=ft.id");
		
		Condition condition = pb.getCondition();
		if(condition!=null){
			String foodName = condition.getFoodName();
			if(foodName!=null && !foodName.isEmpty()){
				sb.append("  AND f.foodName LIKE ? ");
				list.add("%"+foodName+"%");
			}
			
			int type_id = condition.getFoodType_id();
			if(type_id>0){
				sb.append(" AND f.foodType_id=? ");
				list.add(type_id);
			}
		}
		try {
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void getAll(PageBean<Food> pb) {
		
		int totalCount = this.getTotalCount(pb);
		pb.setTotalCount(totalCount);
		
		List<Object> list = new ArrayList<Object>();
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    
		} else if (pb.getTotalPage()!=0 && pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		
		}
		
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		
		int count = pb.getPageCount();							
		
		Condition condition = pb.getCondition();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append("     	f.id,");
		sb.append("     	f.foodName,");
		sb.append("     	f.foodType_id,");
		sb.append("     	f.price,");
		sb.append("     	f.mprice,");
		sb.append("     	f.remark,");
		sb.append("     	f.img,");
		sb.append("     	ft.typeName");
		sb.append(" FROM ");
		sb.append("     	food f,");
		sb.append("     	foodtype ft");
		sb.append(" WHERE 	1=1 ");
		sb.append("     	AND f.foodType_id=ft.id");
		
		if(condition!=null){
			String foodName = condition.getFoodName();
			if(foodName!=null && !foodName.isEmpty()){
				sb.append("  AND f.foodName LIKE ? ");
				list.add("%"+foodName+"%");
			}
			
			int type_id = condition.getFoodType_id();
			if(type_id>0){
				sb.append(" AND f.foodType_id=? ");
				list.add(type_id);
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		try {
			if(index>=0){
				List<Food> pageData = qr.query(sb.toString(), new BeanListHandler<Food>(Food.class), list.toArray());
				pb.setPageData(pageData);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Food> findByType(int type) {
		try {
			String sql ="SELECT * FROM food WHERE foodType_id =?";
			return qr.query(sql, new BeanListHandler<Food>(Food.class), type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
