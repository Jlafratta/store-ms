package edu.store.client.service.mocks;

import edu.store.client.domain.model.Client;
import edu.store.client.domain.model.Person;
import edu.store.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ClientMock {

    @Autowired
    private ClientRepository clientRepository;

    public Client getOne() {
        return Client.builder()
            .id(1L)
            .person(
                Person.builder()
                    .id(1L)
                    .name("Julian")
                    .surname("Lafratta")
                    .birthDate(LocalDate.of(1998, 9, 8))
                    .build()
            ).build();
    }

    public List<Client> getList() {
        return List.of(
            Client.builder()
                .id(1L)
                .person(
                    Person.builder()
                        .id(1L)
                        .name("Julian")
                        .surname("Lafratta")
                        .birthDate(LocalDate.of(1998, 9, 8))
                        .build()
                ).build(),
            Client.builder()
                .id(2L)
                .person(
                    Person.builder()
                        .id(2L)
                        .name("Pedro")
                        .surname("Alfonso")
                        .birthDate(LocalDate.of(1997, 3, 15))
                        .build()
                ).build(),
            Client.builder()
                .id(3L)
                .person(
                    Person.builder()
                        .id(3L)
                        .name("Marcos")
                        .surname("Alonso")
                        .birthDate(LocalDate.of(1999, 12, 26))
                        .build()
                ).build()
        );
    }

    public void init() {
        clientRepository.saveAll(getList());
    }
}
