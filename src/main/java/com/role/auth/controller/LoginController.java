package com.role.auth.controller;

//import io.swagger.annotations.ApiOperation;
import com.role.auth.models.Users;
import com.role.auth.models.dto.UsersDTO;
import com.role.auth.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UsersService usersService;

    @GetMapping("/findAll")
//    @ApiOperation(value = "retorna se está logado ou nao")
    public ResponseEntity<List<Users>>  listUsers() {
        LOGGER.info("[GET-Usuarios] --> Buscando todos os usuarios cadastrados");
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @GetMapping("/findUsername/{username}")
//    @ApiOperation(value = "retorna se está logado ou nao")
    public ResponseEntity<UsersDTO>  user(@PathVariable String username) {
            String name = username.toLowerCase();
            LOGGER.info("[GET-Usuario] --> Buscando usuario com username: " + name);

            Users user = usersService.findByUserName(name);

            UsersDTO usersDTO = new UsersDTO(user.getUsername(), user.getEmail(), user.getPassword());

            return ResponseEntity.ok().body(usersDTO);
    }

    @PostMapping("new")
    public ResponseEntity<Void> create(@RequestBody Users user) {

        user = usersService.saveUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
