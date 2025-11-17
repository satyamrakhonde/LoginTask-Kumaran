package com.sampleLogin.userlogin.service;

import com.sampleLogin.userlogin.dtos.IncidentDto;
import com.sampleLogin.userlogin.entity.Incident;
import com.sampleLogin.userlogin.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService{

    @Autowired
    private IncidentRepository repo;

    private void copyDtoToEntity(IncidentDto dto, Incident ent) {
        ent.setDate(dto.getDate());
        ent.setType(dto.getType());
        ent.setDescription(dto.getDescription());
        ent.setStatus(dto.getStatus());
        ent.setEnvironment(dto.getEnvironment());
        ent.setApplication(dto.getApplication());
        ent.setClosedDate(dto.getClosedDate());
    }

    @Override
    public Incident save(IncidentDto dto) {
        if (!"Closed".equals(dto.getStatus())) {
            dto.setClosedDate(null);
        }
        Incident ent = (dto.getId() == null) ? new Incident() : repo.findById(dto.getId()).orElse(new Incident());
        copyDtoToEntity(dto, ent);
        return repo.save(ent);
    }

    @Override
    public List<Incident> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
