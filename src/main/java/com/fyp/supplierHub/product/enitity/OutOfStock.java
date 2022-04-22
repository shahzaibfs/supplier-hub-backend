package com.fyp.supplierHub.product.enitity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_out_of_stock")
public class OutOfStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="out_of_stock_id")
    private Integer outOfStockId ;

    @Column(name = "out_of_stock_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date outOfStockDate;

    @OneToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="product_id",referencedColumnName = "product_id")
    @JsonIgnore
    private Product product ;


    public OutOfStock() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OutOfStock(Date outOfStockDate) {
        this.outOfStockDate = outOfStockDate;
    }

    public OutOfStock(Integer outOfStockId, Date outOfStockDate) {
        this.outOfStockId = outOfStockId;
        this.outOfStockDate = outOfStockDate;
    }

    public Integer getOutOfStockId() {
        return outOfStockId;
    }

    public void setOutOfStockId(Integer outOfStockId) {
        this.outOfStockId = outOfStockId;
    }

    public Date getOutOfStockDate() {
        return outOfStockDate;
    }

    public void setOutOfStockDate(Date outOfStockDate) {
        this.outOfStockDate = outOfStockDate;
    }
}
