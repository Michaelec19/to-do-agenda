package com.agendaone.todoagenda.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserExternal {

    @Id
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "email_user")
    private String emailUser;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "last_name_user")
    private String lastNameUser;

    @Column(name = "id_rol")
    private Long idRol;

    public Long getIdUser() { return idUser; }
    public String getEmailUser() { return emailUser; }
    public String getPasswordUser() { return passwordUser; }
    public String getNameUser() { return nameUser; }
    public String getLastNameUser() { return lastNameUser; }
    public Long getIdRol() { return idRol; }
}