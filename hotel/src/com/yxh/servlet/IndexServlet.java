package com.yxh.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxh.entity.Food;
import com.yxh.entity.FoodType;
import com.yxh.entity.Orders;
import com.yxh.utils.Condition;
import com.yxh.utils.PageBean;

public class IndexServlet extends BaseServlet {

	public Object getMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("table_id");

		String table_id = request.getParameter("table_id");
		if (table_id != null) {
			tableService.changeState(Integer.parseInt(table_id));
			if (obj == null) {
				session.setAttribute("table_id", table_id);
			}
		}

		
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("foodtypes", foodtypes);

		
		PageBean<Food> pb = new PageBean<Food>();

		Condition con = new Condition();
		
		String foodtype = request.getParameter("foodtype");
		String foodName = request.getParameter("foodName");
		if (foodtype != null && !foodtype.isEmpty()) {
			con.setFoodType_id(Integer.parseInt(foodtype));
			pb.setCondition(con);
		}
		if (foodName != null && !foodName.isEmpty()) {
			con.setFoodName(foodName);
			pb.setCondition(con);
		}

		pb.setPageCount(6);
		String curPage = request.getParameter("currentPage");
		if (curPage == null || curPage.isEmpty()) {
			pb.setCurrentPage(1);
		}
		if (curPage != null && !curPage.isEmpty()) {
			int currentPage = Integer.parseInt(curPage);
			pb.setCurrentPage(currentPage);
		}

		foodService.getAll(pb);

		request.setAttribute("pageBean", pb);
		uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

		return uri;
	}

	public Object searchFood(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri=null;
		
		PageBean<Food> pb = new PageBean<Food>();
		Condition condition = new Condition();
		
		String keyword = request.getParameter("keyword");
		if(keyword!=null && !keyword.isEmpty()){
			condition.setFoodName(keyword);
		}
		if(condition!=null){
			pb.setCondition(condition);
		}
		
		pb.setCondition(condition);
		
		foodService.getAll(pb);

		request.setAttribute("pageBean", pb);
		uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

		return uri;
	}

	
	public Object getFoodDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri =null;
		String id = request.getParameter("food");
		Food food = foodService.findById(Integer.parseInt(id));
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("food", food);
		request.setAttribute("foodtypes", foodtypes);
		uri = request.getRequestDispatcher("/app/detail/caixiangxi.jsp");
		
		return uri;
	}
	
	
	
}
