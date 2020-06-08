package com.clicknine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicknine.dao.SaleOrderDetailDao;
import com.clicknine.entity.po.SaleOrderDetail;
import com.clicknine.service.SaleOrderDetailService;

@Service
public class SaleOrderDetailServiceImpl implements SaleOrderDetailService{
	
	@Autowired
	private SaleOrderDetailDao orderDetailDao;

	@Override
	public List<SaleOrderDetail> queryByOrderId(Long orderId) {
		return orderDetailDao.queryByOrderId(orderId);
	}
	
	
}
