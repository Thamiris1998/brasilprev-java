package com.application.dao;

import com.application.model.Product;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryTest {

    private final static Logger log = Logger.getLogger(ProductRepositoryTest.class);

    @Autowired
    private ProductRepository repository;

    private Product product = new Product();

    @Before
    public void init() {
        product.setId("123abc");
        product.setName("Cadeira");
        product.setDescription("Gamer MyMax");
        product.setPrice(520.00);
        product.setStock(8);
        repository.save(product);
    }

    // Create new Account
    @Test
    public void whenCreateNewProduct() {
        Product prod = new Product();
        prod.setId("456def");
        prod.setName("Monitor");
        prod.setDescription("Samsung LED 22P");
        prod.setPrice(780.00);
        prod.setStock(3);

        repository.save(prod);
        List<Product> list = new ArrayList<>();
        Iterable<Product> iterable = repository.findAll();
        iterable.forEach(list::add);
        log.info(repository.findById("456def") + " : " + prod);
        assertThat(2).isEqualTo(list.size());
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }
}

