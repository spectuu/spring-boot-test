package me.itoxic.service;

import me.itoxic.dto.Response;
import me.itoxic.entity.User;
import me.itoxic.dto.UserDTO;
import me.itoxic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Response ping() {
        return Response.builder().message("OK").build();
    }

    public Response list() {
        List<User> users = userRepository.findAll();
        return Response.builder().data(toDTO(users)).message("OK").build();
    }

    public List<UserDTO> toDTO(List<User> users) {
        return toDTO(users.toArray(new User[users.size()]));
    }

    public List<UserDTO> toDTO(User...users) {

        List<UserDTO> dtos = new ArrayList<>();

        for(User user : users)
            dtos.add(UserDTO.builder()
                     .email(user.getEmail())
                     .name(user.getName())
                     .build());

        return dtos;

    }

}
