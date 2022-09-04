package edu.store.client.controller;

import edu.store.client.domain.dto.ClientDTO;
import edu.store.client.domain.dto.MappeableDTO;
import edu.store.client.domain.model.Client;
import edu.store.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController extends GenericController<Client, Long> {

    @Autowired
    public ClientController(ClientService clientService) {
        service = clientService;
    }

    @PostMapping()
    public ResponseEntity<Client> add(@RequestBody @Valid ClientDTO entity, BindingResult result) {
        return super.add(map(entity), result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        return super.findAll(page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody @Valid ClientDTO entity, BindingResult result) {
        return super.update(map(entity, id), result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> toggleEnabled(@PathVariable Long id) {
        return super.toggleEnabled(id);
    }

    @Override
    protected URI getLocation(Client entity) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/client/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
    }

    @Override
    protected Client map(MappeableDTO dto) {
        return mapper.map(dto, Client.class);
    }

    @Override
    protected Client map(MappeableDTO dto, Long id) {
        Client client = map(dto);
        client.setId(id);
        return client;
    }
}
