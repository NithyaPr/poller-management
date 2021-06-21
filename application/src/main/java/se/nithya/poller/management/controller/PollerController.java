package se.nithya.poller.management.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import se.nithya.poller.management.businessbridge.PollerBusinessBridge;

@CrossOrigin(maxAge = 3600)
@RestController
public class PollerController {

    private final PollerBusinessBridge pollerBusinessBridge;

    public PollerController(PollerBusinessBridge pollerBusinessBridge) {
        this.pollerBusinessBridge = pollerBusinessBridge;
    }

    @Scheduled(fixedRate = 900000)
    @GetMapping("/v1/poll")
    public void schedulePollTask(){
        pollerBusinessBridge.pollServices();
    }
}
