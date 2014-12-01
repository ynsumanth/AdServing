/**
 * 
 */
package com.ir.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class SearchAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> results;
	private List<String> ads;
	public List<String> getAds() {
		return ads;
	}
	public List<String> getResults() {
		return results;
	}
	public SearchAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("in doGet method");
		results = new ArrayList<String>();
		results.add("111111111111111111111111111");
		results.add("222222222222222222222222222");
		results.add("333333333333333333333333333");
		results.add("444444444444444444444444444");
		results.add("555555555555555555555555555");
		results.add("666666666666666666666666666");
		results.add("777777777777777777777777777");
		results.add("888888888888888888888888888");
		results.add("999999999999999999999999999");
		results.add("100000000000000000000000000");
		req.setAttribute("queryResults", results);
		List<String> ads = new ArrayList<String>();
		ads.add("111111111111111111111111111");
		ads.add("222222222222222222222222222");
		ads.add("333333333333333333333333333");
		ads.add("444444444444444444444444444");
		req.setAttribute("adResults", ads);
		resp.sendRedirect("results.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		System.out.println("in dopost method");
	}

}
