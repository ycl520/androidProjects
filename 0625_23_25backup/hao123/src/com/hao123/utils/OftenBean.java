package com.hao123.utils;

public class OftenBean {
	public int _id;
	public String url;
	public String title;
	public int count;

	public OftenBean() {
		super();
	}
	public OftenBean(int _id, String url, String title, int count) {
		super();
		this._id = _id;
		this.url = url;
		this.title = title;
		this.count = count;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
