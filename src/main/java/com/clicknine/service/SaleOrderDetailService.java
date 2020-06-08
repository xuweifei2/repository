package com.clicknine.service;

import java.util.List;

import com.clicknine.entity.po.SaleOrderDetail;

public interface SaleOrderDetailService {

	List<SaleOrderDetail> queryByOrderId(Long orderId);

}
