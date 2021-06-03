package me.itoxic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dtoProduct.*;
import me.itoxic.dtoUser.InEmailDTO;
import me.itoxic.dtoUser.Response;

import me.itoxic.entity.Product;
import me.itoxic.entity.User;
import me.itoxic.repository.ProductRepository;
import me.itoxic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

   public Response allProducts(){

        List<Product> products = productRepository.findAll();
        return Response.builder().data(products).build();

        }

   public Response createProduct(InCreateProductDTO dto) {

       Product product = productRepository.findByProductName(dto.getProductName());

       if (product != null) {

           return Response.builder().message("Este producto ya existe.").build();

       }

       product = Product.builder()
               .productName(dto.getProductName())
               .typeOfProduct(dto.getTypeOfProduct())
               .AvailableQuantity(dto.getAvailableQuantity())
               .price(dto.getPrice()).build();

       if (product.getPrice() <= 0) {

           return Response.builder().message("El precio no puede ser igual a cero.").build();

       }

       if (product.getAvailableQuantity() < 0) {

           return Response.builder().message("El cantidad disponible no puede ser menor a cero.").build();

       }

       productRepository.save(product);
       return Response.builder().message("El producto se ha creado!").data(product).build();

   }

   public Response deleteProduct(InDeleteProductDTO dto){

       Product product = productRepository.findByProductName(dto.getProductName());

       List<User> users = userRepository.findAll();

       if(product == null){

           return Response.builder().message("El producto no existe.").build();

       }

       for(User user1: users){

           users.remove(user1.getProducts().remove(product));

       }

       productRepository.delete(product);
       return Response.builder().message("El producto ha sido borrado...").data(product.getId()).build();

   }

   public Response updateProduct(InUpdateProductDTO dto){

       Product productChange = productRepository.findByProductName(dto.getProductChange());
        Product productName = productRepository.findByProductName(dto.getProductName());

       if(productChange == null){

           return Response.builder().message("El producto no existe.").build();

       }

       if(productName != null){

           return Response.builder().message("Ya hay un producto con el nombre que desea colocar.").build();

       }


       productChange.setProductName(dto.getProductName());
       productChange.setTypeOfProduct(dto.getTypeOfProduct());
       productChange.setAvailableQuantity(dto.getAvailableQuantity());
       productChange.setPrice(dto.getPrice());

       if(productChange.getPrice() <= 0){

           return Response.builder().message("El precio no puede ser igual a cero.").build();

       }

       if(productChange.getAvailableQuantity() < 0){
           productChange.setAvailableQuantity(0);
            productRepository.save(productChange);
           return Response.builder().message("la cantidad disponible no puede ser menor a cero.").build();

       }

       productRepository.save(productChange);
       return Response.builder().message("El producto se actualizo!").data(productChange).build();

   }

    public Response buy(InBuyDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());

        Product product = productRepository.findByProductName(dto.getProductName());

        if(user == null){

            return Response.builder().message("No hay un usuario con ese email").build();

        }

        if(product == null){

            return Response.builder().message("El producto que desea comprar no esta en la base de datos.").build();

        }

        if(user.getCoins() < product.getPrice()){

            return Response.builder().message("no tienes suficiente dinero.").build();

        }

        if(product.getAvailableQuantity() <= 0){

            return Response.builder().message("El producto no se encuentra disponible.").build();

        }

        user.setCoins(user.getCoins() - product.getPrice());
        product.setAvailableQuantity(product.getAvailableQuantity() - 1);

        user.getProducts().add(product);

        productRepository.save(product);
        userRepository.save(user);
        return Response.builder().message("El produco fue comprado con exito!").build();

    }

}

