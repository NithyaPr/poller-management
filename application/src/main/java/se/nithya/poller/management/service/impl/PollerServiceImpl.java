package se.nithya.poller.management.service.impl;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import se.nithya.poller.management.repository.ServiceModel;
import se.nithya.poller.management.repository.ServiceRepository;
import se.nithya.poller.management.service.PollerService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PollerServiceImpl implements PollerService {

    private final ServiceRepository serviceRepository;

    private final WebClient webClient;

    public PollerServiceImpl(ServiceRepository serviceRepository, WebClient webClient) {

        this.serviceRepository = serviceRepository;
        this.webClient = webClient;
    }

    @Override
    public void poll(ServiceModel model) {
        pollEndpointAsync(model, model.getUrl().trim());
    }

    public void pollEndpointAsync(ServiceModel service, String baseUrl){
        HttpHeaders httpRequestHeaders = new HttpHeaders();
        httpRequestHeaders.add("transaction-id",
                UUID.randomUUID().toString().toUpperCase().replace("-", ""));
        log.info("Call to service endpoint {} " , baseUrl);
        Mono<String> response = buildClient(baseUrl).get()
                .headers(httpHeaders -> httpHeaders.addAll(httpRequestHeaders))
                .exchangeToMono(resp -> {
                    if (resp.statusCode()
                            .equals(HttpStatus.OK)) {
                        return Mono.just("OK");
                    } else if (resp.statusCode()
                            .is4xxClientError()) {
                        return Mono.just("FAIL");
                    } else {
                        return resp.createException()
                                .flatMap(Mono::error);
                    }
                });
        response.subscribe(res -> {
            log.info("service id {} baseUrl {} response {} ", service.getId(), baseUrl, res);
            saveStatus(res, service);
        });

    }

    private void saveStatus(String res, ServiceModel service) {
        service.setStatus(res);
        service.setStatusDate(LocalDateTime.now());

        if (res.equals("OK")) {
            service.setLastActiveDate(LocalDateTime.now());
        }
        serviceRepository.save(service);
    }

    private WebClient buildClient(String baseurl){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return webClient.mutate()
                .baseUrl(baseurl)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseurl))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
