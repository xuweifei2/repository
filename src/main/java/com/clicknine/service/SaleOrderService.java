package com.clicknine.service;

import com.clicknine.common.resule.PageResult;
import com.clicknine.entity.po.SaleOrder;

public interface SaleOrderService {

	/**
	 * 新增销售订单
	 * @param saleOrder
	 */
	SaleOrder insert(SaleOrder saleOrder);

	/**
	 * 根据id查询销售订单
	 * @param orderId
	 * @return
	 */
	SaleOrder findOne(Long orderId);

	/**
	 * 分页查询销售订单
	 * @param pageNo
	 * @param pageSize
	 * @param saleOrder
	 * @return
	 */
	PageResult queryPage(Integer pageNo, Integer pageSize, SaleOrder saleOrder);

}
