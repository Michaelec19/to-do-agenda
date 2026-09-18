package com.agendaone.todoagenda.infrastructure.repositories;

import com.agendaone.todoagenda.domain.UserExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExternalRepository extends JpaRepository<UserExternal, Long> {

    Optional<UserExternal> findByEmailUser(String emailUser);

    @Query(value = "SELECT CONCAT(u.name_user, ' ', u.last_name_user) FROM users u " +
            "JOIN reservation_user ru ON u.id_user = ru.id_user " +
            "JOIN reservation r ON ru.id_reservation = r.id_reservation " +
            "WHERE r.id_schedule = :scheduleId", nativeQuery = true)
    List<String> findAttendeesByScheduleId(@Param("scheduleId") Long scheduleId);
}