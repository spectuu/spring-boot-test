package co.com.aruma.controller;

import co.com.aruma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.com.aruma.dto.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${application.services.users}")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Response list() {
        return this.userService.list();
    }

    @GetMapping("ping")
    public Response ping() {
        return this.userService.ping();
    }
    
}

