package com.hoanglam.ecommerce.dto.request;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class ProductRequestDto {
    private String id;
    private String title;
    private String metaTitle;
    private String desciption;
    private BigDecimal price;
    private float discount;
    private float averageRating;
    private int numberSold;
    private int numberRating;
    private String detail;
    private short quantity;
    private Date createdDate;
    private Date updatedDate;
    private String content;

}
