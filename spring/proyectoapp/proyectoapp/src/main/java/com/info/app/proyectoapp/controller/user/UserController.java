package com.info.app.proyectoapp.controller.user;

import com.info.app.proyectoapp.dto.user.UsuarioDto;
import com.info.app.proyectoapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UsuarioDto usuario){
        //crear el usuario
        UsuarioDto usuarioDto = userService.createUser(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioDto);
    }
}
