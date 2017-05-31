package com.yxh.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxh.factory.BeanFactory;
import com.yxh.service.IDinnerTableService;
import com.yxh.service.IFoodService;
import com.yxh.service.IFoodTypeService;
import com.yxh.service.IOrderDetailService;
import com.yxh.service.IOrdersService;
import com.yxh.utils.WebUtils;


public abstract class BaseServlet extends HttpServlet {
	
	
	protected IDinnerTableService tableService = BeanFactory.getInstance(
			"dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance(
			"foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService = BeanFactory.getInstance("foodService",
			IFoodService.class);
	protected IOrdersService ordersService = BeanFactory.getInstance("ordersService",
			IOrdersService.class);
	protected IOrderDetailService orderDetailService = BeanFactory.getInstance("orderDetailService",
			IOrderDetailService.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object returnValue = null;
		
		String methodName = request.getParameter("method"); 
		
		try {
			Class clazz = this.getClass();
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		
		WebUtils.goTo(request, response, returnValue);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
