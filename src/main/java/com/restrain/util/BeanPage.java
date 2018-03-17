package com.restrain.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeanPage<T> {

	long total;
	int totalPage;
	List<T> rows;
	String errorCode;
/*
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}*/

}
