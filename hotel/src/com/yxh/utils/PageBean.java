package com.yxh.utils;

import java.util.List;

public class PageBean<T> {
	private int currentPage = 1; 
	private int pageCount = 10;  
	private int totalCount;      
	private int totalPage;       
	private List<T> pageData;       
	private Condition condition;
	
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public int getTotalPage() {
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	

}
