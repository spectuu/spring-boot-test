package me.itoxic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.itoxic.dtoProduct.InCreateProductDTO;
import me.itoxic.dtoProduct.InDeleteProductDTO;
import me.itoxic.dtoProduct.InUpdateProductDTO;
import me.itoxic.dtoUser.Response;

import me.itoxic.entity.Product;
import me.itoxic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

   public Response allProducts(){

        List<Product> products = productRepository.findAll();
        return Response.builder().data(products).message("OK").build();

        }

   public Response createProduct(InCreateProductDTO dto){

       Product product = productRepository.findByProductName(dto.getProductName());

       if(product != null){

           return Response.builder().message("Este producto ya existe").build();

       }

       product = Product.builder().productName(dto.getProductName()).typeOfProduct(dto.getTypeOfProduct()).price(dto.getPrice()).build();
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

       product.setPrice(dto.getPrice());
       productRepository.save(product);
       return Response.builder().message("El precio del producto se actualizo!").data(product.getPrice()).build();

   }
}

