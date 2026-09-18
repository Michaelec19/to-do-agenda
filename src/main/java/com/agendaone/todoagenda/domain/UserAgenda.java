package com.agendaone.todoagenda.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_agenda")
public class UserAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "trainer_id", updatable = false, nullable = false)
    private UUID trainerId;

    @Column(name = "trainer_name", nullable = false, length = 50)
    private String nombre;

    @Column(name = "trainer_lastname", nullable = false, length = 50)
    private String apellido;

    @Column(name = "contact_email", unique = true, nullable = false)
    private String email;

    @Column(name = "auth_hash", nullable = false)
    private String password;

    public UserAgenda() {
    }

    public UserAgenda(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public UUID getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(UUID trainerId) {
        this.trainerId = trainerId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}