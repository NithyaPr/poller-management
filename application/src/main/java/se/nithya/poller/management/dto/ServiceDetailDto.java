package se.nithya.poller.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDetailDto {

    private Long id;
    @JsonProperty("serviceUrl")
    private String url;

    @JsonProperty("serviceName")
    private String name;

    private String status;

    private String createdBy;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime lastActiveDate;

    private LocalDateTime statusDate;

}
