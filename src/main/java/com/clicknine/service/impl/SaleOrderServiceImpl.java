package com.clicknine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicknine.common.resule.PageResult;
import com.clicknine.dao.SaleOrderDao;
import com.clicknine.entity.po.SaleOrder;
import com.clicknine.entity.po.SaleOrderDetail;
import com.clicknine.service.SaleOrderDetailService;
import com.clicknine.service.SaleOrderService;
import com.clicknine.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SaleOrderServiceImpl implements SaleOrderService{
	
	@Autowired
	private SaleOrderDetailService orderDetailService;

	@Autowired
	private SaleOrderDao saleOrderDao;
	
	
	@Override
	public SaleOrder insert(SaleOrder saleOrder) {
		int currentSecond = DateUtil.currentSecond();
		saleOrder.setCtime(currentSecond);
		saleOrder.setUtime(currentSecond);
		saleOrderDao.insert(saleOrder);
		return saleOrder;
	}


	@Override
	public SaleOrder findOne(Long orderId) {
		SaleOrder saleOrder = saleOrderDao.findOne(orderId);
		// 加载订单详情信息
		if(null != saleOrder && null != saleOrder.getOrderId()) {
			List<SaleOrderDetail> detailList = orderDetailService.queryByOrderId(saleOrder.getOrderId());
			saleOrder.setDetailList(detailList);
		}
		return saleOrder;
	}


	@Override
	public PageResult queryPage(Integer pageNo, Integer pageSize, SaleOrder saleOrder) {
		Page page = PageHelper.startPage(pageNo, pageSize, true);
		List<SaleOrder> orderList = saleOrderDao.queryPage(saleOrder);
		return PageResult.setPage(page, orderList);
	}

}
