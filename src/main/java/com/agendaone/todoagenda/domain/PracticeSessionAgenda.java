package com.agendaone.todoagenda.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "practice_session_agenda")
public class PracticeSessionAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id", updatable = false, nullable = false)
    private UUID sessionId;

    @Column(name = "martial_discipline", nullable = false)
    private String discipline;

    @Column(name = "skill_level", nullable = false)
    private String level;

    @Column(name = "headquarters_location", nullable = false)
    private String location;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "modality", nullable = false)
    private String modality;

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "trainer_id_fk", nullable = false)
    private Long trainerId;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "external_schedule_id", unique = true, nullable = true)
    private Long externalScheduleId;
    
    @Column(name = "quotas", nullable = true)
    private Integer quotas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "practice_session_attendees", joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "attendee_name")
    private List<String> attendees = new ArrayList<>();

    public PracticeSessionAgenda() {
    }

    public UUID getSessionId() { return sessionId; }
    public void setSessionId(UUID sessionId) { this.sessionId = sessionId; }
    public String getDiscipline() { return discipline; }
    public void setDiscipline(String discipline) { this.discipline = discipline; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public String getModality() { return modality; }
    public void setModality(String modality) { this.modality = modality; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Long getTrainerId() { return trainerId; }
    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getExternalScheduleId() { return externalScheduleId; }
    public void setExternalScheduleId(Long externalScheduleId) { this.externalScheduleId = externalScheduleId; }
    public Integer getQuotas() { return quotas; }
    public void setQuotas(Integer quotas) { this.quotas = quotas; }
    public List<String> getAttendees() { return attendees; }
    public void setAttendees(List<String> attendees) { this.attendees = attendees; }
}