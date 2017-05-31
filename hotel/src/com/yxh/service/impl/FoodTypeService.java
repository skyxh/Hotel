package com.yxh.service.impl;

import java.util.List;

import com.yxh.dao.IFoodTypeDao;
import com.yxh.entity.FoodType;
import com.yxh.factory.BeanFactory;
import com.yxh.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService{

	IFoodTypeDao dao = BeanFactory.getInstance("foodTypeDao", IFoodTypeDao.class) ;
	
	@Override
	public void add(FoodType foodtype) {
		dao.add(foodtype);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void updata(FoodType foodtype) {
		dao.updata(foodtype);
	}

	@Override
	public List<FoodType> query() {
		return dao.query();
	}

	@Override
	public FoodType findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<FoodType> query(String keyword) {
		return dao.query(keyword);
	}

	@Override
	public Integer getFirstType() {
		return dao.getFirstType();
	}
}
