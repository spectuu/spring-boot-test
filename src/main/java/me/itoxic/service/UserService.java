package me.itoxic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dto.*;
import me.itoxic.entity.User;
import me.itoxic.repository.UserRepository;
import org.hibernate.annotations.RowId;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
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

    public List<UserDTO> toDTO(User... users) {

        List<UserDTO> dtos = new ArrayList<>();

        for (User user : users)
            dtos.add(UserDTO.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build());

        return dtos;

    }

    public Response createAccount(InDataDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail());

        if (user != null) {

            return Response.builder().message("El Usuario existe").build();
        }

        user = User.builder().email(dto.getEmail()).password(dto.getPassword()).build();
        userRepository.save(user);
        return Response.builder().data(user.getId()).message("OK").build();

    }

    public Response addCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
        if (user == null) {

            return Response.builder().message("El usuario no esta agregado en la base de datos.").build();

        }

        user.setCoins(user.getCoins() + dto.getCoins());
        userRepository.save(user);
        return Response.builder().data(user.getCoins()).build();

    }

    public Response removeCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
        if (user == null) {

            return Response.builder().message("El usuario no esta agregado en la base de datos.").build();

        }

        user.setCoins((user.getCoins() - dto.getCoins()));
        userRepository.save(user);
        return Response.builder().data(user.getCoins()).build();
    }

    public Response setCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if (user == null) {

            return Response.builder().message("El usuaro no esta agregado en la base de datos.").build();

        }

        user.setCoins(dto.getCoins());
        userRepository.save(user);
        return Response.builder().data(user.getCoins()).build();
    }

    public Response userlogin(InDataDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if (user != null) {
            return Response.builder().data(user.getId()).message("OK").build();

        }else{

            return Response.builder().message("El Usuario no existe").build();

        }
    }

    public Response deleteAccount(InDataDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if(user == null){

            return Response.builder().message("El Usuario no existe").build();
            
        }

            userRepository.delete(user);

            return Response.builder().data(user.getId()).message("OK").build();

    }

}
