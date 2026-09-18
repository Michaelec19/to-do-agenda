package com.agendaone.todoagenda.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class ScheduleExternal {

    @Id
    @Column(name = "id_schedule")
    private Long idSchedule;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate;

    @Column(name = "level")
    private String level;

    @Column(name = "location")
    private String location;

    @Column(name = "modality")
    private String modality;

    @Column(name = "quotas")
    private Integer quotas;

    @Column(name = "id_user")
    private Long idUser;

    public Long getIdSchedule() { return idSchedule; }
    public LocalDateTime getScheduleDate() { return scheduleDate; }
    public String getLevel() { return level; }
    public String getLocation() { return location; }
    public String getModality() { return modality; }
    public Integer getQuotas() { return quotas; }
    public Long getIdUser() { return idUser; }
}