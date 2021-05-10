package me.itoxic.service;

import me.itoxic.dto.*;
import me.itoxic.entity.User;
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
                     .coins(user.getCoins())
                     .build());

        return dtos;

    }

    public Response userDefinir(InTransaccionDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());

        if(user == null){

            return Response.builder().message("El usuario no existe").build();

        }

        user.setCoins(dto.getCoins());
        userRepository.save(user);
        return Response.builder().message("OK").build();


    }

    public Response userRemove(InTransaccionDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        if(user == null){

            return Response.builder().message("El usuario no existe").build();

        }

        user.setCoins(user.getCoins() - dto.getCoins());
        userRepository.save(user);
        return Response.builder().message("OK").build();

    }

    public Response userAgregar(InTransaccionDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        if(user == null){

            return Response.builder().message("El usuario no existe").build();

        }else{

            user.setCoins(user.getCoins() + dto.getCoins());
            userRepository.save(user);
            return Response.builder().message("OK").build();
        }


    }

    public Response userlogin(InDataDTO dto) {
        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
        OutCoinsDTO outCoinsDTO = OutCoinsDTO.builder().coins(user.getCoins()).build();
        if(user == null) {

            return Response.builder().message("El Usuario no existe").build();
        }
        return Response.builder().data(outCoinsDTO).message("OK").build();
    }

}
