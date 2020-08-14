package com.application.service;

import com.application.dao.OrderRepository;
import com.application.exception.BusinessException;
import com.application.model.Client;
import com.application.model.Order;
import com.application.model.Product;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {

    @Mock
    ClientService clientService;
    @Mock
    ProductService productService;
    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService orderService = new OrderService(clientService,productService,repository);;

    Product product = new Product();
    Client client = new Client();


    @Before
    public void init() {
        product.setId("123abc");
        product.setName("Cadeira");
        product.setDescription("Gamer MyMax");
        product.setPrice(520.00);
        product.setStock(8);

        client.setId("blá223abc");
        client.setName("Thamiris Lopes");
        client.setCpf("18525446785");
        client.setEmail("thamirisvasconsellos@hotmail.com");
        client.setPhone("(21)98858-7668");
        client.setAdress("Rua antonio davi");
        client.setAdressNumber("133");
        client.setCity("Duque de caxias");
        client.setNeighborhood("Vila Amelia");
        client.setZipCode("25025090");
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createProductFinishStock() {

        List<Order.Products> products = new ArrayList<>();
        Order.Products productOrder = new Order.Products();
        productOrder.setProductId("123abc");
        productOrder.setPrice(520.00);
        productOrder.setQuantity(10);
        products.add(productOrder);

        Order order = new Order();
        order.setFormOfPayment("CREDIT");
        order.setClientId("blá223abc");
        order.setStatus(Order.StatusOrder.RESERVED);
        order.setProducts(products);

        Mockito.when(clientService.findOne(order.getClientId())).thenReturn(Optional.ofNullable(client));
        for (Order.Products prod : order.getProducts()){
            Mockito.when(productService.findOne(prod.getProductId())).thenReturn(Optional.ofNullable(product));
        }
        Mockito.when(repository.save(order)).thenReturn(order);

        exception.expect(BusinessException.class);
        exception.expectMessage("Produto insuficiente em estoque.");
        orderService.create(order);
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }
}
