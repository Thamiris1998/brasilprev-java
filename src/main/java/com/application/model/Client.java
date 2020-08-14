package com.application.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Data
public class Client implements Serializable {

    @Id
    private String id = ObjectId.get().toHexString();
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String adress; // endereço
    private String adressNumber; // numero
    private String neighborhood; //bairro
    private String city; //cidade
    private String state; // estado
    private String zipCode; //cep

}
