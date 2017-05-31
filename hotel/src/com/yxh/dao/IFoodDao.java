package com.yxh.dao;

import java.util.List;

import com.yxh.entity.Food;
import com.yxh.utils.PageBean;

public interface IFoodDao {

	void add(Food food);
	
	void delete(int id);
	
	void updata(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	List<Food> findByType(int type);
	void getAll(PageBean<Food> pb);
	
	

	int getTotalCount(PageBean<Food> pb);
}
