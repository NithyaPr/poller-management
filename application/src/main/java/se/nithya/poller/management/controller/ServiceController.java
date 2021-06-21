package se.nithya.poller.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.nithya.poller.management.businessbridge.ServiceBusinessBridge;
import se.nithya.poller.management.dto.ServiceDetailDto;

import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
public class ServiceController {

    private final ServiceBusinessBridge serviceBusinessBridge;

    public ServiceController(ServiceBusinessBridge serviceBusinessBridge) {
        this.serviceBusinessBridge = serviceBusinessBridge;
    }

    @PostMapping("/v1/service")
    public ResponseEntity<ServiceDetailDto> addService(@RequestBody ServiceDetailDto serviceDetailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceBusinessBridge.addService(serviceDetailDto));
    }

    @GetMapping("/v1/service/{id}")
    public ResponseEntity<ServiceDetailDto> getService(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceBusinessBridge.getService(id));
    }
    @GetMapping("/v1/service")
    public ResponseEntity<List<ServiceDetailDto>> getServices(@RequestParam(value = "user") String user) {
        log.info("Fetching all the services for the user {} ", user);
        return ResponseEntity.status(HttpStatus.OK).body(serviceBusinessBridge.getAllService(user));
    }
    @DeleteMapping("/v1/service/{id}")
    public ResponseEntity deleteService(@PathVariable(value = "id") String id) {
        serviceBusinessBridge.deleteService(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping("/v1/service/{id}")
    public ResponseEntity<ServiceDetailDto> updateService(@PathVariable(value = "id") String id,
                                                         @RequestBody ServiceDetailDto serviceDetailDto) {
        log.info("Update services ");
        return ResponseEntity.status(HttpStatus.OK).body(serviceBusinessBridge.updateService(id, serviceDetailDto));
    }   
}
