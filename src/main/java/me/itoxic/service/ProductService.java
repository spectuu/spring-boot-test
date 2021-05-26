package me.itoxic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dtoProduct.*;
import me.itoxic.dtoUser.Response;

import me.itoxic.entity.Product;
import me.itoxic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

   public Response allProducts(){

        List<Product> products = productRepository.findAll();
        return Response.builder().data(products).build();

        }

   public Response createProduct(InCreateProductDTO dto) {

       Product product = productRepository.findByProductName(dto.getProductName());

       if (product != null) {

           return Response.builder().message("Este producto ya existe").build();

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

           return Response.builder().message("El cantidad disponible no puede ser igual a cero.").build();

       }

       productRepository.save(product);

       return Response.builder().message("El producto se ha creado!").data(product.getId()).build();

   }

   public Response deleteProduct(InDeleteProductDTO dto){

       Product product = productRepository.findByProductName(dto.getProductName());

       if(product == null){

           return Response.builder().message("El producto no existe").build();

       }

       productRepository.delete(product);
       return Response.builder().message("El producto ha sido borrado...").data(product.getId()).build();

   }

   public Response updatePrice(InUpdateProductDTO dto){

       Product product = productRepository.findByProductName(dto.getProductName());

       if(product == null){

           return Response.builder().message("El producto no existe").build();

       }

       product.setProductName(dto.getProductName());
       product.setAvailableQuantity(dto.getAvailableQuantity());
       product.setTypeOfProduct(dto.getTypeOfProduct());
       product.setPrice(dto.getPrice());

       if(product.getAvailableQuantity() < 0){

           return Response.builder().message("la cantidad disponible no puede ser igual a cero.").build();

       }

       if(product.getPrice() <= 0){

           return Response.builder().message("El precio no puede ser igual a cero.").build();

       }
       productRepository.save(product);
       return Response.builder().message("El precio del producto se actualizo!").data("Price = " + product.getPrice()).build();

   }

   public Response finById(Long id){

    Optional<Product> productOptional = productRepository.findById(id);

    if(productOptional.isPresent()) {

        Product product = productOptional.get();

        return Response.builder().data(product.getProductName()).build();

    }

    return Response.builder().message("No eiste el producto").build();


   }



}
