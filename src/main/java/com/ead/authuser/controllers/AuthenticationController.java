package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){
        if(userService.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Usuário já existe!");    
        }

        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Email já existe!");
        }
        
        var userModel = new UserModel();
        
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index(){
        log.trace("TRACE"); // Log do Lombox, que vem pela anotação @Log4j2
        logger.debug("debug"); // Log da instancia do logger
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        return "Loggin Spring Boot";
    }    
}
