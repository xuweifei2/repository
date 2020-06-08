package com.clicknine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clicknine.common.resule.PageResult;
import com.clicknine.common.resule.RetResult;
import com.clicknine.entity.po.SaleOrder;
import com.clicknine.service.SaleOrderService;

/**
 * 销售单controller
 * @author 95886
 *
 */
@RestController
public class SaleOrderController {
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	/**
	 * 新增销售订单
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping(value = "/api/saleOrder/insert", method = RequestMethod.POST)
	public RetResult insert(SaleOrder saleOrder) {
		SaleOrder insert = saleOrderService.insert(saleOrder);
		return RetResult.setSuccess(insert);
	}
	
	/**
	 * 根据id查询销售订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/api/saleOrder/findOne", method = RequestMethod.POST)
	public RetResult findOne(Long orderId) {
		SaleOrder order = saleOrderService.findOne(orderId);
		return RetResult.setSuccess(order);
	}
	
	/**
	 * 分页查询销售订单
	 * @param pageNo
	 * @param pageSize
	 * @param saleOrder
	 * @return
	 */
	@RequestMapping(value = "/api/saleOrder/queryPage", method = RequestMethod.POST)
	public PageResult queryPage(Integer pageNo, Integer pageSize, SaleOrder saleOrder) {
		return saleOrderService.queryPage(pageNo, pageSize, saleOrder);
	}
	
	
}
