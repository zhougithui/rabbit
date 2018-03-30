package com.footprint.rabbit.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单消息
 */
public class OrderMessage {
    private String requestNo;
    private Date orderTime;
    private BigDecimal amount;
    private String customerName;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
