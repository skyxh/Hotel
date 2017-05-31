package com.yxh.service;

import java.util.List;

import com.yxh.entity.Food;
import com.yxh.utils.PageBean;

public interface IFoodService {
	
void add(Food food);
	
	void delete(int id);
	
	void updata(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	public void getAll(PageBean<Food> pb);
	
	List<Food> findByType(int type);
}
