package se.nithya.poller.management.businessbridge.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.nithya.poller.management.businessbridge.ServiceBusinessBridge;
import se.nithya.poller.management.dto.ServiceDetailDto;
import se.nithya.poller.management.exception.PollerException;
import se.nithya.poller.management.repository.ServiceModel;
import se.nithya.poller.management.repository.ServiceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ServiceBusinessBridgeImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceBusinessBridgeImpl serviceBusinessBridge;

    @Test
    void should_addService(){
        ServiceDetailDto detailDto = buildServiceDetailDto("http://poller-management/health",
                "admin","PollerAPI");
        when(serviceRepository.save(any())).thenReturn(buildServiceModel(
                detailDto));
        detailDto = serviceBusinessBridge.addService(detailDto);

        assertEquals(12L, detailDto.getId());
    }

    @Test
    void should_updateService_Service_NotFound(){
        ServiceDetailDto detailDto = buildServiceDetailDto("http://poller-management/health",
                "admin","PollerAPIUpdated");


        when(serviceRepository.findById(any())).thenReturn(Optional.empty());
        PollerException exception = assertThrows(PollerException.class,
                () -> serviceBusinessBridge.updateService("12", buildServiceDetailDto("http://poller-management/health",
                        "admin","PollerAPIUpdated")));

        assertTrue(exception.getMessage().contains("Service id [12] not found"));

    }

    @Test
    void should_updateService_Service(){
        ServiceDetailDto detailDto = buildServiceDetailDto("http://poller-management/health",
                "admin","PollerAPIUpdated");

        when(serviceRepository.save(any())).thenReturn(buildServiceModel(
                detailDto));
        when(serviceRepository.findById(any())).thenReturn(Optional.of(buildServiceModel(detailDto)));
        serviceBusinessBridge.updateService("12",detailDto);

        verify(serviceRepository).save(any());
    }

    @Test
    void should_deleteService(){
        ServiceDetailDto detailDto = buildServiceDetailDto("http://poller-management/health",
                "admin","PollerAPIUpdated");

        when(serviceRepository.findById(any())).thenReturn(Optional.of(buildServiceModel(detailDto)));
        serviceBusinessBridge.deleteService("12");

        verify(serviceRepository).delete(any());
    }

    @Test
    void should_getService(){
        ServiceDetailDto detailDto = buildServiceDetailDto("http://poller-management/health",
                "admin","PollerAPIUpdated");

        when(serviceRepository.findById(any())).thenReturn(Optional.of(buildServiceModel(detailDto)));
        detailDto = serviceBusinessBridge.getService("12");

        assertEquals(12L, detailDto.getId());
    }

    /**
     *
     * Test data preparation
     */

    private ServiceDetailDto buildServiceDetailDto(String url, String createdBy, String name){
        ServiceDetailDto serviceDetailDto = new ServiceDetailDto();
        serviceDetailDto.setUrl(url);
        serviceDetailDto.setName(name);
        serviceDetailDto.setCreatedBy(createdBy);
        return serviceDetailDto;
    }

    private ServiceModel buildServiceModel(ServiceDetailDto serviceDetailDto){
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId(12L);
        serviceModel.setCreatedDate(LocalDateTime.now());

        serviceModel.setName(serviceDetailDto.getName());
        serviceModel.setUrl(serviceDetailDto.getUrl());

        serviceModel.setCreatedBy(serviceDetailDto.getCreatedBy());
        return serviceModel;
    }
}