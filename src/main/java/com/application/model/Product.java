package com.application.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Data
public class Product implements Serializable {

    @Id
    private String id = ObjectId.get().toHexString();
    private String name;
    private String description;
    private Double price;
    private int stock;
}
