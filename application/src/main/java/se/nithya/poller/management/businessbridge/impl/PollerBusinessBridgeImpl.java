package se.nithya.poller.management.businessbridge.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import se.nithya.poller.management.businessbridge.PollerBusinessBridge;
import se.nithya.poller.management.repository.ServiceModel;
import se.nithya.poller.management.repository.ServiceRepository;
import se.nithya.poller.management.service.PollerService;

@Component
public class PollerBusinessBridgeImpl implements PollerBusinessBridge {

    private final ServiceRepository serviceRepository;

    private final PollerService pollerService;

    public PollerBusinessBridgeImpl(ServiceRepository serviceRepository, PollerService pollerService) {
        this.serviceRepository = serviceRepository;
        this.pollerService = pollerService;
    }

    @Override
    public void pollServices() {
        Iterable<ServiceModel> services = serviceRepository.findAll();
        services.forEach(model -> pollerService.poll(model));
    }
}
