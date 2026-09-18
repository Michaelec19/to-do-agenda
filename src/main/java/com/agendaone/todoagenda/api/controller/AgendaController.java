package com.agendaone.todoagenda.api.controllers;

import com.agendaone.todoagenda.core.services.AgendaService;
import com.agendaone.todoagenda.domain.PracticeSessionAgenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity<List<PracticeSessionAgenda>> getAllSessions() {
        return ResponseEntity.ok(agendaService.getAllLocalSessions());
    }

    @PostMapping
    public ResponseEntity<PracticeSessionAgenda> createSession(@RequestBody PracticeSessionAgenda session) {
        return ResponseEntity.ok(agendaService.saveSession(session));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PracticeSessionAgenda> updateSession(@PathVariable UUID id, @RequestBody PracticeSessionAgenda session) {
        return ResponseEntity.ok(agendaService.updateSession(id, session));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PracticeSessionAgenda> updateStatus(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(agendaService.updateStatus(id, body.get("status")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable UUID id) {
        agendaService.deleteSession(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sync/{profesorId}")
    public ResponseEntity<List<PracticeSessionAgenda>> syncFromMainApp(@PathVariable Long profesorId) {
        agendaService.syncExternalClasses(profesorId);
        return ResponseEntity.ok(agendaService.getAllLocalSessions());
    }
}