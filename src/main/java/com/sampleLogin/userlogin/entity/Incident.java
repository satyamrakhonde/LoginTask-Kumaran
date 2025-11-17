package com.sampleLogin.userlogin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "incidents")
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 10)
    private String type; //INC, CHG, Alert

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 20)
    private String status;      // Open, WIP, Completed, Closed, ...

    @Column(nullable = false, length = 10)
    private String environment; // PROD,PAS,DEV,UAT,DR,CERT

    @Column(length = 100)
    private String application;

    private LocalDate closedDate;
}
