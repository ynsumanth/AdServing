package com.ir.adserving;

import java.util.ArrayList;
import java.util.List;

public class Document {
	
	private String id, title, content, date, place, author;
	public Document() {
		// TODO Auto-generated constructor stub
	}
	public Document(String author, String content, String date, String id, String place, String title) {
		this.author = author;
		this.content = content;
		this.date = date;
		this.place = place;
		this.title = title;
		this.id = id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public String getPlace() {
		return place;
	}
	public String getDate() {
		return date;
	}
	public String getContent() {
		return content;
	}
	public String getAuthor() {
		return author;
	}
}
