package com.application.service;

import com.application.dao.ClientRepository;
import com.application.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findOne(String id) {
        return repository.findById(id);
    }

    public Client create(Client client) {
        return repository.save(client);
    }

    public Client update(Client client) {
        return repository.save(client);
    }

    public void delete(Client client) {
        repository.delete(client);
    }
}
