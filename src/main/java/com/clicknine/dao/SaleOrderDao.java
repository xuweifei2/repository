package com.clicknine.dao;

import java.util.List;

import com.clicknine.entity.po.SaleOrder;

public interface SaleOrderDao {

	Integer insert(SaleOrder saleOrder);

	SaleOrder findOne(Long orderId);

	List<SaleOrder> queryPage(SaleOrder saleOrder);
	
	
	
}
