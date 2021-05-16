package me.itoxic.dtoProduct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutProductDTO {

    private String productName;
    private String typeOfProduct;
    private int AvailableQuantity;
    private int price;

}
