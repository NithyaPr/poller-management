package se.nithya.poller.management.businessbridge;

import se.nithya.poller.management.dto.ServiceDetailDto;

import java.util.List;

public interface ServiceBusinessBridge {

    ServiceDetailDto addService(ServiceDetailDto serviceDetailDto);
    ServiceDetailDto getService(String serviceId);
    List<ServiceDetailDto> getAllService(String user);
    ServiceDetailDto updateService(String serviceId, ServiceDetailDto serviceDetailDto);
    void deleteService(String serviceId);

}
