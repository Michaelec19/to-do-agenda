package com.agendaone.todoagenda.core.services;

import com.agendaone.todoagenda.domain.PracticeSessionAgenda;
import com.agendaone.todoagenda.domain.ScheduleExternal;
import com.agendaone.todoagenda.infrastructure.repositories.PracticeSessionAgendaRepository;
import com.agendaone.todoagenda.infrastructure.repositories.ScheduleExternalRepository;
import com.agendaone.todoagenda.infrastructure.repositories.UserExternalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendaService {

    private final PracticeSessionAgendaRepository practiceRepository;
    private final ScheduleExternalRepository scheduleExternalRepository;
    private final UserExternalRepository userRepository;
    public AgendaService(PracticeSessionAgendaRepository practiceRepository,
                         ScheduleExternalRepository scheduleExternalRepository,
                         UserExternalRepository userRepository) {
        this.practiceRepository = practiceRepository;
        this.scheduleExternalRepository = scheduleExternalRepository;
        this.userRepository = userRepository;
    }

    public void syncExternalClasses(Long profesorId) {
        List<ScheduleExternal> externals = scheduleExternalRepository.findSchedulesByProfesorId(profesorId);

        for (ScheduleExternal ext : externals) {
            if (!practiceRepository.existsByExternalScheduleId(ext.getIdSchedule())) {
                PracticeSessionAgenda newSession = new PracticeSessionAgenda();
                newSession.setExternalScheduleId(ext.getIdSchedule());
                newSession.setDiscipline("Clase Principal");
                newSession.setLevel(ext.getLevel() != null ? ext.getLevel() : "N/A");
                newSession.setLocation(ext.getLocation() != null ? ext.getLocation() : "Sede Central");
                newSession.setDate(ext.getScheduleDate().toLocalDate());
                newSession.setStartTime(ext.getScheduleDate().toLocalTime());
                newSession.setEndTime(ext.getScheduleDate().toLocalTime().plusHours(1));
                newSession.setModality(ext.getModality() != null ? ext.getModality() : "N/A");
                newSession.setNotes("Sincronizada desde Reserve-One");
                newSession.setTrainerId(profesorId);
                newSession.setStatus("PENDING");
                newSession.setQuotas(ext.getQuotas());
                List<String> asistentes = userRepository.findAttendeesByScheduleId(ext.getIdSchedule());
                newSession.setAttendees(asistentes);

                practiceRepository.save(newSession);
            }
        }
    }

    public List<PracticeSessionAgenda> getAllLocalSessions() {
        return practiceRepository.findAll();
    }

    public PracticeSessionAgenda saveSession(PracticeSessionAgenda session) {
        if (session.getTrainerId() == null) {
            session.setTrainerId(1L);
        }
        return practiceRepository.save(session);
    }

    public PracticeSessionAgenda updateSession(UUID id, PracticeSessionAgenda updatedSession) {
        return practiceRepository.findById(id).map(session -> {
            session.setDiscipline(updatedSession.getDiscipline());
            session.setLevel(updatedSession.getLevel());
            session.setLocation(updatedSession.getLocation());
            session.setDate(updatedSession.getDate());
            session.setStartTime(updatedSession.getStartTime());
            session.setEndTime(updatedSession.getEndTime());
            session.setModality(updatedSession.getModality());
            session.setNotes(updatedSession.getNotes());
            session.setQuotas(updatedSession.getQuotas());
            return practiceRepository.save(session);
        }).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    public PracticeSessionAgenda updateStatus(UUID id, String status) {
        return practiceRepository.findById(id).map(session -> {
            session.setStatus(status);
            return practiceRepository.save(session);
        }).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    public void deleteSession(UUID id) {
        practiceRepository.deleteById(id);
    }
}