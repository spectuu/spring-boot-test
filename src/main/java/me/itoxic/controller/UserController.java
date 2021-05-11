package me.itoxic.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dto.*;
import me.itoxic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("${application.services.users}")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody InDataDTO dto) {
        return this.userService.userlogin(dto);

    }

    @PostMapping("/addCoins")
    public Response addCoins(@RequestBody InCoinsDTO dto){

        return this.userService.addCoins(dto);
    }

    @PostMapping("/removeCoins")
    public Response removeCoins(@RequestBody InCoinsDTO dto){

        return this.userService.removeCoins(dto);

    }

    @PostMapping("setCoins")
    public Response setCoins(InCoinsDTO dto){

        return this.userService.setCoins(dto);

    }
    @PostMapping("/createAccount")
    public Response createAccount(@RequestBody InDataDTO dto){

        return this.userService.createAccount(dto);

    }

    @DeleteMapping("/deleAccount")
    public Response deleteAccount(@RequestBody InDataDTO dto){


        return this.userService.deleteAccount(dto);

    }

}