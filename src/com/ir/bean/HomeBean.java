package com.ir.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class HomeBean {
	private List<String> list;
	private boolean pop;
	public HomeBean() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<String>();
	}
	public boolean getPop() {
		return pop;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String action() {
		System.out.println("inside action method");
		return "success";
	}
	public void listen(ActionEvent event) {
		list.add("one");
		list.add("two");
		pop = true;
	}
}
