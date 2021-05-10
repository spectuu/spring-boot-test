package me.itoxic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InDataDTO {

    private String email;
    private String password;

}
