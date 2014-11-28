package com.ir.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import com.ir.adserving.Document;
import com.ir.adserving.Query;
@ManagedBean
public class HomeBean {
	private Map<Document, List<String>> newsResult;
	private String userQuery;
	private boolean pop;
	public HomeBean() {
		// TODO Auto-generated constructor stub
		newsResult = new HashMap<Document, List<String>>();
	}
	public String getUserQuery() {
		return userQuery;
	}
	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}
	public boolean getPop() {
		return pop;
	}
	public void setNewsResult(Map<Document, List<String>> newsResult) {
		this.newsResult = newsResult;
	}
	public Map<Document, List<String>> getNewsResult() {
		return newsResult;
	}
	public String action() {
		System.out.println("inside action method");
		return "success";
	}
	public void listen(ActionEvent event) {
		Query query = new Query();
		System.out.println("inside listen method");
		newsResult = query.query(userQuery);
		if(newsResult!=null && newsResult.size()!=0) 
			pop = true;
	}
}
