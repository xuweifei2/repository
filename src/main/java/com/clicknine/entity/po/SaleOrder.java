package com.clicknine.entity.po;

import java.util.Date;
import java.util.List;

/**
 * 销售订单实体类
 * @author 95886
 *
 */
public class SaleOrder {
	
	// 订单id
	private Long orderId;
	
	// 客户名称
	private String customerName;
	
	// 客户地址
	private String customerAddress;
	
	// 制单时间
	private Date orderTime;
	
	// 订单号
	private String orderNumber;
	
	// 业务员
	private String salesman;
	
	// 业务员id
	private Long salesmanId;
	
	// 制单员
	private String former;
	
	private Integer ctime;
	
	private Integer utime;
	
	// 订单详情
	private List detailList;
	
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getFormer() {
		return former;
	}

	public void setFormer(String former) {
		this.former = former;
	}

	public Integer getCtime() {
		return ctime;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public Integer getUtime() {
		return utime;
	}

	public void setUtime(Integer utime) {
		this.utime = utime;
	}

	public List getDetailList() {
		return detailList;
	}

	public void setDetailList(List detailList) {
		this.detailList = detailList;
	}
	
	
}
