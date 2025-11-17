package com.sampleLogin.userlogin.service;

import com.sampleLogin.userlogin.dtos.IncidentDto;
import com.sampleLogin.userlogin.entity.Incident;

import java.util.List;

public interface IncidentService {
    Incident save(IncidentDto dto);

    List<Incident> getAll();

    void deleteById(Long id);
}
