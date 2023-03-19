package com.role.auth.models.dto;

import lombok.Data;

@Data
public class UsersDTO {

    private String username;
    private String email;
    private String password;

    public UsersDTO(String username, String email, String password) {

        this.username = username;
        this.email = email;
        this.password = password;
    }
}
