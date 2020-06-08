package com.clicknine.entity.po;

import java.math.BigDecimal;

/**
 * 销售订单详情表
 * @author 95886
 *
 */
public class SaleOrderDetail {
	
	// 详情id
	private Long orderDetailId;
	
	// 订单id
	private Long orderId;
	
	// 商品id
	private Long goodsId;
	
	// 商品名称
	private String goodsName;
	
	// 销售价格
	private BigDecimal price;
	
	// 商品数量
	private Double goodsNumber;
	
	// 小计金额
	private BigDecimal subtotal;
	
	private Integer ctime;
	
	private Integer utime;
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Double getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Double goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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
	
}
