package edu.store.client.service;

import edu.store.client.domain.model.Client;
import edu.store.client.exception.EntityNotFoundException;
import edu.store.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client, Long> {

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        repository = clientRepository;
    }

    @Override
    public Client findById(Long id) {
        return super.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(Client.class.getSimpleName(), id.toString()));
    }
}
