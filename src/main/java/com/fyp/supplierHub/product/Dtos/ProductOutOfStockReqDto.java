package com.fyp.supplierHub.product.Dtos;

import java.util.Date;

public class ProductOutOfStockReqDto {

    private Date outOfStockDate ;
    private int productId ;

    public ProductOutOfStockReqDto() {
    }

    public Date getOutOfStockDate() {
        return outOfStockDate;
    }

    public void setOutOfStockDate(Date outOfStockDate) {
        this.outOfStockDate = outOfStockDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
