package com.agendaone.todoagenda.infrastructure.repositories;

import com.agendaone.todoagenda.domain.ScheduleExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleExternalRepository extends JpaRepository<ScheduleExternal, Long> {
    @Query("SELECT s FROM ScheduleExternal s WHERE s.idUser = :profesorId")
    List<ScheduleExternal> findSchedulesByProfesorId(Long profesorId);
}