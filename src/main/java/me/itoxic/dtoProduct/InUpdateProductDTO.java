package me.itoxic.dtoProduct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InUpdateProductDTO {

    private String productChange;
    private String productName;
    private String typeOfProduct;
    private int availableQuantity;
    private int price;


}
