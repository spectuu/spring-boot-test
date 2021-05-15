package me.itoxic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dtoUser.*;
import me.itoxic.entity.User;
import me.itoxic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    .coins(user.getCoins())
                    .build());

        return dtos;

    }

    public Response createAccount(InDataDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail());

        if (user != null) {

            return Response.builder().message("El Usuario existe").build();
        }

        user = User.builder().email(dto.getEmail()).password(dto.getPassword()).coins(dto.getCoins()).build();
        userRepository.save(user);
        return Response.builder().data(user.getId()).message("OK").build();

    }

    public Response addCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
        if (user == null) {

            return Response.builder().message("El usuario no esta agregado en la base de datos.").build();

        }

        user.setCoins(user.getCoins() + dto.getCoins());
        if(user.getCoins() <= 0)
            user.setCoins(0);
        userRepository.save(user);
        return Response.builder().message("Las coins fueron agregadas!").data(user.getCoins()).build();
    }

    public Response removeCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if (user == null) {

            return Response.builder().message("El usuario no esta agregado en la base de datos.").build();

        }

        user.setCoins((user.getCoins() - dto.getCoins()));
        if(user.getCoins() <= 0)
            user.setCoins(0);
        userRepository.save(user);
        return Response.builder().message("Las coins fueron removidas!").data(user.getCoins()).build();
    }

    public Response setCoins(InCoinsDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if (user == null) {

            return Response.builder().message("El usuaro no esta agregado en la base de datos.").build();

        }


        user.setCoins(dto.getCoins());
        if(user.getCoins() <= 0)
        user.setCoins(0);
        userRepository.save(user);
        return Response.builder().message("Se han establecido las coins!").data(user.getCoins()).build();
    }

    public Response userlogin(InDataDTO dto) {

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if (user != null) {

            return Response.builder().message("Coins: " + user.getCoins()).data(user.getCoins()).build();

        }else{

            return Response.builder().message("El Usuario no existe").build();

        }
    }

    public Response getUserDataId(long id){

        User user = userRepository.findById(id);
        OutIdDTO outIdDTO = OutIdDTO.builder().email(user.getEmail()).coins(user.getCoins()).build();

        if(user == null){

            return Response.builder().message("El Usuario no existe").build();

        }

        return Response.builder().data(outIdDTO).build();

    }
    public Response deleteAccount(InPasswordDTO dto) {

        System.out.println(dto.getPassword() + ", " + dto.getEmail());

        User user = userRepository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());

        if(user == null){

            return Response.builder().message("El Usuario no existe").build();
            
        }

            userRepository.delete(user);
            return Response.builder().message("El usuario ha sido borrado...").data(user.getId()).message("OK").build();

    }

    public Response getUserDataCoins(int coins){

        List<User> users = userRepository.findAllByCoins(coins);

        if (users == null) {

            return Response.builder().message("no hay usuarios con este valor").build();

        }

        List<OutIdDTO> lista = new ArrayList<>();

        for (User user1: users){
                   lista.add(OutIdDTO.builder()
                    .email(user1.getEmail())
                    .coins(user1.getCoins()).build());
        }

        return  Response.builder().data(lista).message("OK").build();

    }

    public Response getUserDataPassword(String password){

        List<User> users = userRepository.findByPassword(password);

        if(users == null){

            return Response.builder().message("no hay usuarios con este valor").build();

        }

        List<OutPasswordDTO> dtos = new ArrayList<>();

        for(User usersPassword: users){

                dtos.add(OutPasswordDTO.builder()
                        .id(usersPassword.getId())
                        .password(usersPassword.getPassword())
                        .build());


        }

        return Response.builder().data(dtos).message("OK").build();

    }

}
