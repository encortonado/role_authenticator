package com.role.auth.services;

import com.role.auth.models.Users;
import com.role.auth.repository.UsersRepository;
import com.role.auth.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public List<Users> findAll() {

       return usersRepository.findAll();

    }

    public Users findByUserName(String username) {

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(username));

        return user.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado! Username: " + username + " Tipo: " + Users.class.getName()));

    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }
}
