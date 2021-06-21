package se.nithya.poller.management.repository;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "SERVICES")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_SEQ")
    @SequenceGenerator(sequenceName = "SERVICES_SEQ", allocationSize = 1,
            name = "SERVICES_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @Column(name = "LAST_ACTIVE_DATE")
    private LocalDateTime lastActiveDate;

    @Column(name = "STATUS_DATE")
    private LocalDateTime statusDate;

}
