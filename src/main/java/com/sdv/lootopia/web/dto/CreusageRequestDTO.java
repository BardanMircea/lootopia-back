package com.sdv.lootopia.web.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreusageRequestDTO {
    private Long chasseId;
    private Double latitude;
    private Double longitude;
}
