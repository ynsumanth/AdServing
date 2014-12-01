package com.ir.adserving;

public class Advertisement {
	private String bidPrice, id, url, keyword, title, clicks;
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setClicks(String clicks) {
		this.clicks = clicks;
	}
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	public String getUrl() {
		return url;
	}
	public String getTitle() {
		return title;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getId() {
		return id;
	}
	public String getClicks() {
		return clicks;
	}
	public String getBidPrice() {
		return bidPrice;
	}
	public Advertisement() {
		// TODO Auto-generated constructor stub
	}

}
