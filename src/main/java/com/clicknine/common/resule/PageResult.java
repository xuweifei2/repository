package com.clicknine.common.resule;

import java.util.ArrayList;
import java.util.List;

import com.clicknine.entity.po.SaleOrder;
import com.github.pagehelper.Page;

public class PageResult extends RetResult {

	private Integer index;
	private Integer size;
	private Long totalCount;
	private Integer totalPages;

	public static PageResult setPage(Page page, List<SaleOrder> list) {
		PageResult result = new PageResult();
		result.setCode("000");
    	result.setMessage("OK");
    	result.setStatus(true);
    	result.setData(list);
    	result.setTotalCount(page.getTotal());
    	result.setIndex(page.getPageNum());
    	result.setTotalPages(page.getPages());
    	result.setSize(page.getPageSize());
		return result;
	}
	
	public static PageResult setError(String code, String message) {
		PageResult result = new PageResult();
		result.setCode(code);
		result.setMessage(message);
		result.setStatus(false);
		result.setData("");
		return result;
	}



	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public static PageResult emptyResult() {
		PageResult result = new PageResult();
		result.setCode("000");
		result.setMessage("OK");
		result.setStatus(true);
		result.setExtraData("");
		result.setIndex(1);
		result.setSize(10);
		result.setTotalCount(0L);
		result.setTotalPages(1);
		result.setData(new ArrayList<>());
		return result;
	}



}
