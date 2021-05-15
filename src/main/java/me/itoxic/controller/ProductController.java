package me.itoxic.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.itoxic.dtoProduct.InCreateProductDTO;
import me.itoxic.dtoProduct.InDeleteProductDTO;
import me.itoxic.dtoProduct.InUpdateProductDTO;
import me.itoxic.dtoUser.Response;
import me.itoxic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("${application.services.products}")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/find/allProducts")
    private Response findAllProducts(){

        System.out.println("OK");
        return this.productService.allProducts();
    }

    @PostMapping("/createProduct")
    private Response createProduct(@RequestBody InCreateProductDTO dto){

        return this.productService.createProduct(dto);

    }

    @DeleteMapping("/deleteProduct")
    private Response deleteProduct(@RequestBody InDeleteProductDTO dto){

        return this.productService.deleteProduct(dto);

    }

    @PatchMapping("/updatePrice")
    private Response updateProduct(@RequestBody InUpdateProductDTO dto){

        return this.productService.updatePrice(dto);

    }
}
