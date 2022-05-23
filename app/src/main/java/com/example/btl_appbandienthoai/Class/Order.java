package com.example.btl_appbandienthoai.Class;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String OrderNo;
    //    thông tin khách hàng
    private String custAddress;
    private String custName;
    private String custPhone;
    private String dateOrder;
    private String status;
    private int numProduct;
    private int totalPrice;
    private List<DetailOrder> listDetailOrder;

    public Order(){
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public Order(String custAddress, String custName, String custPhone, String dateOrder, String status, int numProduct, int totalPrice, List<DetailOrder> listDetailOrder) {
        this.custAddress = custAddress;
        this.custName = custName;
        this.custPhone = custPhone;
        this.dateOrder = dateOrder;
        this.status = status;
        this.numProduct = numProduct;
        this.totalPrice = totalPrice;
        this.listDetailOrder = listDetailOrder;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<DetailOrder> getListDetailOrder() {
        return listDetailOrder;
    }

    public void setListDetailOrder(List<DetailOrder> listDetailOrder) {
        this.listDetailOrder = listDetailOrder;
    }

    public void addToListDetailOrder(DetailOrder detailOrder){
        if (this.listDetailOrder == null){
            this.listDetailOrder = new ArrayList<>();
        }
        this.listDetailOrder.add(detailOrder);
    }
}
