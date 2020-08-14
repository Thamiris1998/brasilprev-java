package com.application.service;

import com.application.dao.OrderRepository;
import com.application.exception.BusinessException;
import com.application.model.Client;
import com.application.model.Order;
import com.application.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public OrderService(ClientService clientService, ProductService productService,OrderRepository orderRepository){
        this.clientService = clientService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOne(String id) {
        return orderRepository.findById(id);
    }

    public Order create(Order order) {
        Double total = 0.0;

        Optional<Client> client = clientService.findOne(order.getClientId());
        if(!client.isPresent()){
            throw new BusinessException("Código do cliente não encontrado.");
        }

        for (Order.Products prod : order.getProducts()){
            Optional<Product> product = productService.findOne(prod.getProductId());
            if (product.isPresent()) {
                if(product.get().getStock() < prod.getQuantity()){
                    throw new BusinessException("Produto insuficiente em estoque.");
                }
                product.get().setStock(product.get().getStock() - prod.getQuantity());
                productService.update(product.get());
                total += (prod.getPrice() * prod.getQuantity());
            } else {
                throw new BusinessException("Código do produto não encontrado.");
            }
        }
        order.setCreatedDate(new Date());
        order.setTotal(total);
        return orderRepository.save(order);
    }

    public Order update(Order order, Optional<Order> orderActual) {
        Double total = 0.0;

        Optional<Client> client = clientService.findOne(order.getClientId());
        if (!client.isPresent()) {
            throw new BusinessException("Código do cliente não encontrado.");
        }


        for (Order.Products prodActual : orderActual.get().getProducts()) {
            if (!order.getProducts().contains(prodActual)) {
                Optional<Product> product = productService.findOne(prodActual.getProductId());
                if (product.isPresent()) {
                    product.get().setStock(product.get().getStock() + prodActual.getQuantity());
                    productService.update(product.get());
                }
            }
        }

        for (Order.Products prodNew : order.getProducts()) {
            if (order.getProducts().contains(prodNew)) {
                Optional<Order.Products> productActual = order.getProducts().stream().filter(product -> product.getProductId().equals(prodNew.getProductId())).findFirst();
                if (productActual.get().getQuantity() < prodNew.getQuantity()) {
                    Optional<Product> product = productService.findOne(prodNew.getProductId());
                    if (product.isPresent()) {
                        product.get().setStock(product.get().getStock() + (prodNew.getQuantity() - productActual.get().getQuantity()));
                        productService.update(product.get());
                        total += (prodNew.getPrice() * prodNew.getQuantity());
                    }
                } else if (productActual.get().getQuantity() > prodNew.getQuantity()) {
                    Optional<Product> product = productService.findOne(prodNew.getProductId());
                    if (product.get().getStock() < (productActual.get().getQuantity() - prodNew.getQuantity())) {
                        throw new BusinessException("Produto insuficiente em estoque.");
                    }
                    if (product.isPresent()) {
                        product.get().setStock(product.get().getStock() + (productActual.get().getQuantity() - prodNew.getQuantity()));
                        productService.update(product.get());
                        total += (prodNew.getPrice() * prodNew.getQuantity());
                    }
                }
                total += (prodNew.getPrice() * prodNew.getQuantity());
            } else {
                Optional<Product> product = productService.findOne(prodNew.getProductId());
                if (product.isPresent()) {
                    if (product.get().getStock() < prodNew.getQuantity()) {
                        throw new BusinessException("Produto insuficiente em estoque.");
                    }
                    product.get().setStock(product.get().getStock() - prodNew.getQuantity());
                    productService.update(product.get());
                    total += (prodNew.getPrice() * prodNew.getQuantity());
                    }
                }
            }

            order.setCreatedDate(new Date());
            order.setTotal(total);
            return orderRepository.save(order);
        }

        public Order cancel(Order order){
            for (Order.Products prod : order.getProducts()) {
                Optional<Product> product = productService.findOne(prod.getProductId());
                if (product.isPresent()) {
                    product.get().setStock(product.get().getStock() + prod.getQuantity());
                    productService.update(product.get());
                } else {
                    throw new BusinessException("Código do produto não encontrado.");
                }
            }
            return orderRepository.save(order);
        }
}



