package edu.store.client.service.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MockService {

    @Value("${mock-data}")
    private final Boolean mockData = false;

    @Autowired
    private ClientMock clientMock;

    @PostConstruct
    public void createFullContext() {
        if(mockData) {
            mockClient();
        }
    }

    private void mockClient() {
        clientMock.init();
    }
}
