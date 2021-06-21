package se.nithya.poller.management.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.nithya.poller.management.PollerManagementApplication;
import se.nithya.poller.management.repository.ServiceModel;
import se.nithya.poller.management.repository.ServiceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PollerManagementApplication.class)
class PollerServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private PollerServiceImpl pollerServiceImpl;

    @Before
    public void setup() {
    }



    @Test
    void should_startPoll(){
        when(serviceRepository.findAll()).thenReturn(List.of(buildServiceModel()));
        testClient.get()
                .uri("/notfoundendpoint")
                .exchange()
                .expectStatus()
                .is4xxClientError();

    }

    /**
     *
     * Test data preparation
     */

    private ServiceModel buildServiceModel(){
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId(12L);
        serviceModel.setCreatedDate(LocalDateTime.now());

        serviceModel.setName("Poller");
        serviceModel.setUrl("http://localhost:8080");

        serviceModel.setCreatedBy("admin");
        return serviceModel;
    }
}