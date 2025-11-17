package com.sampleLogin.userlogin.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class IncidentDto {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    @Pattern(regexp = "^(INC|CHG|Alert)$")
    private String type;

    private String description;

    @NotBlank
    @Pattern(regexp = "^(Open|WIP|Completed|Closed|Cancelled|Rescheduled|Rollbacked|Monitoring)$")
    private String status;

    @NotBlank
    @Pattern(regexp = "^(PROD|PAS|DEV|UAT|DR|CERT)$")
    private String environment;

    private String application;

    // closedDate must be set only when status == Closed — controller/service validates this
    private LocalDate closedDate;

}
