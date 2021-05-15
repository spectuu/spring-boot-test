package me.itoxic.dtoProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InCreateProductDTO {

    private String productName;
    private String typeOfProduct;
    private int price;

}
