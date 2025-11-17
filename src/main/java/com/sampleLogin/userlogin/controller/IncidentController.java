package com.sampleLogin.userlogin.controller;

import com.sampleLogin.userlogin.dtos.IncidentDto;
import com.sampleLogin.userlogin.entity.Incident;
import com.sampleLogin.userlogin.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public List<Incident> getAll() {
        return incidentService.getAll();
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestBody @Valid List<IncidentDto> dtos) {

        //save each and return saved list (with ids)
        for(IncidentDto d : dtos) {
            //basic validation: closedDate only if status == Closed
            if(!"Closed".equals(d.getStatus())) {
                d.setClosedDate(null);
            }
            incidentService.save(d);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Incident> save(@RequestBody @Valid IncidentDto dto) {
        Incident saved = incidentService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        incidentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
