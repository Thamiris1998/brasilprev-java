package com.application.controller;

import com.application.model.Client;
import com.application.model.Order;
import com.application.model.Product;
import com.application.service.ClientService;
import com.application.service.OrderService;
import com.application.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/virtualstore")
public class VirtualStoreController {

    private final static Logger log = LoggerFactory.getLogger(VirtualStoreController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    // PRODUTOS
    @GetMapping("/product")
    public List<Product> getProducts() {
        log.info("get all products");
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("id") String id) {
        return Optional
                .ofNullable(productService.findOne(id))
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.create(product));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> createClient(@PathVariable("id") String id, @RequestBody Product product) {
        return productService.findOne(id)
                .map(pd -> ResponseEntity.ok().body(productService.update(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        return  productService.findOne(id)
                .map(product ->{ productService.delete(product);
                    return ResponseEntity.ok().build(); })
                .orElse(ResponseEntity.notFound().build());
    }

    // CLIENTES
    @GetMapping("/client")
    public List<Client> getClients() {
        log.info("get all products");
        return clientService.findAll();
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Optional<Client>> getClient(@PathVariable("id") String id) {
        return Optional
                .ofNullable(clientService.findOne(id))
                .map(client -> ResponseEntity.ok().body(client))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.create(client));
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> createClient(@PathVariable("id") String id, @RequestBody Client client) {
        return clientService.findOne(id)
                .map(cl -> ResponseEntity.ok().body(clientService.update(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") String id) {
        return  clientService.findOne(id)
                .map(client ->{ clientService.delete(client);
                    return ResponseEntity.ok().build(); })
                .orElse(ResponseEntity.notFound().build());
    }

    // PEDIDO
    @PostMapping("/order")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok().body(orderService.create(order));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> update(@PathVariable("id") String id, @RequestBody Order order) {

        Optional<Order> orderActual = orderService.findOne(id);
        if(!orderActual.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(order.getStatus() == Order.StatusOrder.CANCEL){
            return ResponseEntity.ok().body(orderService.cancel(order));
        }
        return ResponseEntity.ok().body(orderService.update(order, orderActual));
    }
}
