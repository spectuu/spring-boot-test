package me.itoxic.controller;

import me.itoxic.dto.*;
import me.itoxic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${application.services.users}")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody InDataDTO dto) {
        return this.userService.userlogin(dto);
    }

    @PostMapping("/remove")
    public Response restar(@RequestBody InTransaccionDTO dto){

        return this.userService.userRemove(dto);

    }

    @PostMapping("/definir")
    public Response definir(@RequestBody InTransaccionDTO dto){

        return  this.userService.userDefinir(dto);
    }

    @PostMapping("/agregar")
    public Response agregar(@RequestBody InTransaccionDTO dto) {
        return this.userService.userAgregar(dto);
    }

    @GetMapping("/list")
    public Response list() {
        return this.userService.list();
    }

    @GetMapping("ping")
    public Response ping() {
        return this.userService.ping();
    }

}

