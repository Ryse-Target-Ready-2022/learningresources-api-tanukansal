package com.tgt.rysetii.learningresourcesapitanukansal.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="learningresource")
public class LearningResources implements Serializable {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="cost_price")
    private Double costPrice;
    @Column(name="selling_price")
    private Double sellingPrice;
    @Column(name="product_status")
    @Enumerated(EnumType.STRING)
    private LearningResourceStatus productStatus;
    @Column(name="created_date")
    private LocalDate createdDate;
    @Column(name="published_date")
    private LocalDate publishedDate;
    @Column(name="retired_date")
    private LocalDate retiredDate;

    public LearningResources(){

    }

    public LearningResources(Integer id, String name, Double costPrice, Double sellingPrice, LearningResourceStatus productStatus, LocalDate createdDate, LocalDate publishedDate, LocalDate retiredDate){
        this.id=id;
        this.name=name;
        this.costPrice=costPrice;
        this.sellingPrice=sellingPrice;
        this.productStatus=productStatus;
        this.createdDate=createdDate;
        this.publishedDate=publishedDate;
        this.retiredDate=retiredDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LearningResourceStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(LearningResourceStatus productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(LocalDate retiredDate) {
        this.retiredDate = retiredDate;
    }

    @Override
    public String toString() {
        return "LearningResource{" +
                "learningResourceId=" + id +
                ", learningResourceName='" + name + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", learningResourceStatus=" + productStatus +
                ", createdDate=" + createdDate +
                ", publishedDate=" + publishedDate +
                ", retiredDate=" + retiredDate +
                '}';
    }
}
