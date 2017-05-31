package com.yxh.service;

import java.util.List;

import com.yxh.entity.FoodType;

public interface IFoodTypeService {

	void add(FoodType foodtype);
	
	void delete(int id);
	
	void updata(FoodType foodtype);
	
	List<FoodType> query();

	FoodType findById(int id);

	List<FoodType> query(String keyword);
	
	Integer getFirstType();
}
