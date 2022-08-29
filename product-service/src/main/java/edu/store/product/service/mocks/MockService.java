package edu.store.product.service.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MockService {

    @Value("${mock-data}")
    private final Boolean mockData = false;

    @Autowired
    private CategoryMock categoryMock;

    @PostConstruct
    public void createFullContext() {
        if(mockData) {
            mockCategory();
        }
    }

    private void mockCategory() {
        categoryMock.init();
    }
}
