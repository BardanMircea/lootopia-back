package com.sdv.lootopia.web.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    private String email;
    private String motDePasse;
}
