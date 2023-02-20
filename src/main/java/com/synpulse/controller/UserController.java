package com.synpulse.controller;

import io.swagger.v3.oas.annotations.Operation;
import com.synpulse.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.Serializable;
import java.util.UUID;
import com.synpulse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/synpulse/")
public class UserController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @Operation(summary = "register user api")
    public ResponseEntity<Object> UserV1(
            @RequestBody @Valid UserRequest req,
            HttpServletResponse response) {
            
            if (req.getFirstName() == null || req.getLastName() == null){
                return ResponseEntity.badRequest().body("invalid input");
            }
            
            UUID userId = UUID.randomUUID();
            User user = new User(userId, req.getFirstName(), req.getLastName());
            logger.info("userId " + userId);
            logger.info("user " + user);
            
            userService.createUser(user);

            return ResponseEntity.ok(user.id.toString());
    }
}
