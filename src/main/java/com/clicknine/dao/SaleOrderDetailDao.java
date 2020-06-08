package com.clicknine.dao;

import java.util.List;

import com.clicknine.entity.po.SaleOrderDetail;

public interface SaleOrderDetailDao {

	List<SaleOrderDetail> queryByOrderId(Long orderId);

}
