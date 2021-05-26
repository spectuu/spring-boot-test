package me.itoxic.dtoProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutUserProductsDTO {

    private String email;
    private String productName;
    private int quantity;


}