package com.application.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {

    @Id
    private String id = ObjectId.get().toHexString();
    private String clientId;
    private List<Products> products;
    private StatusOrder status;
    private Date createdDate;
    private String formOfPayment;
    private Double total;

    @Data
    public static class Products {
        private String productId;
        private int quantity; // quantidade
        private Double price;
    }
    public enum StatusOrder {
        RESERVED, CANCEL, SOLD
    }
}


