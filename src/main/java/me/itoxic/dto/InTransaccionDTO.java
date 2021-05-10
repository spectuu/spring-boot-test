package me.itoxic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InTransaccionDTO {

    private String email;
    private int coins;


}
