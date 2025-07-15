package com.sdv.lootopia.web.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreusageResponseDTO {
    private boolean success;
    private String message;
    private Double gainCouronnes;
}
