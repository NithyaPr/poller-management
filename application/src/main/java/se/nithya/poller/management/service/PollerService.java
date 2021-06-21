package se.nithya.poller.management.service;

import se.nithya.poller.management.repository.ServiceModel;

public interface PollerService {

    void poll(ServiceModel model);
}
