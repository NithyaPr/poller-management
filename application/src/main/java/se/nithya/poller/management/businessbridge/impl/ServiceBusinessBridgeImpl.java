package se.nithya.poller.management.businessbridge.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import se.nithya.poller.management.businessbridge.ServiceBusinessBridge;
import se.nithya.poller.management.dto.ServiceDetailDto;
import se.nithya.poller.management.exception.PollerException;
import se.nithya.poller.management.repository.ServiceModel;
import se.nithya.poller.management.repository.ServiceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ServiceBusinessBridgeImpl implements ServiceBusinessBridge {

    private final ServiceRepository serviceRepository;

    public ServiceBusinessBridgeImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDetailDto addService(ServiceDetailDto serviceDetailDto) {
        ServiceModel model = buildServiceModel(serviceDetailDto);
        model.setCreatedBy("admin");
        model.setCreatedDate(LocalDateTime.now());
        model = serviceRepository.save(model);
        serviceDetailDto.setId(model.getId());
        return serviceDetailDto;
    }

    private ServiceModel buildServiceModel(ServiceDetailDto serviceDetailDto) {
        ServiceModel serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceDetailDto,serviceModel);
        return serviceModel;
    }

    @Override
    public ServiceDetailDto getService(String serviceId) {
        ServiceModel serviceModel = getServiceModel(serviceId);
        return buildServiceDto(serviceModel);
    }

    private ServiceModel getServiceModel(String serviceId) {
        Optional<ServiceModel> serviceModelOptional = serviceRepository.findById(Long.valueOf(serviceId));
        ServiceModel serviceModel = serviceModelOptional.orElseThrow(
                () -> new PollerException(HttpStatus.NOT_FOUND,String.format("Service id [%s] not found", serviceId)));
        return serviceModel;
    }

    private List<ServiceModel> getServiceModels(String user) {

        Iterable<ServiceModel> models = Strings.isEmpty(user) || user.equals("all") ?
                serviceRepository.findAll() : serviceRepository.findByCreatedBy(user);
        List<ServiceModel> serviceModels = new ArrayList<>();
        models.iterator().forEachRemaining(serviceModels::add);

        return serviceModels;
    }

    private ServiceDetailDto buildServiceDto(ServiceModel serviceModel) {
        ServiceDetailDto serviceDetailDto = new ServiceDetailDto();
        BeanUtils.copyProperties(serviceModel,serviceDetailDto);
        return serviceDetailDto;
    }

    @Override
    public List<ServiceDetailDto> getAllService(String user) {
        List<ServiceModel> serviceModels = getServiceModels(user);
        log.info("Services {} ", serviceModels);
        return serviceModels.stream().map(this::buildServiceDto).collect(Collectors.toList());
    }

    @Override
    public ServiceDetailDto updateService(String serviceId, ServiceDetailDto serviceDetailDto) {

        ServiceModel serviceModel = getServiceModel(serviceId);
        serviceModel.setUrl(serviceDetailDto.getUrl());
        serviceModel.setName(serviceDetailDto.getName());
        serviceModel.setModifiedDate(LocalDateTime.now());
        ServiceModel service = serviceRepository.save(serviceModel);
        log.info("service updated {} ", service);
        return buildServiceDto(service);
    }

    @Override
    public void deleteService(String serviceId) {
        ServiceModel serviceModel = getServiceModel(serviceId);
        serviceRepository.delete(serviceModel);
    }
}
