package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Etape;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtapeReponseDTO {

    private Integer ordre;
    private String consigne;
    private Double latitudeCache;
    private Double longitudeCache;
    private String passphrase;


    public static EtapeReponseDTO fromEntity(Etape etape) {
        EtapeReponseDTO dto = new EtapeReponseDTO();
        dto.setOrdre(etape.getOrdre());
        dto.setConsigne(etape.getConsigne());
        dto.setPassphrase(etape.getPassphrase());
        dto.setLatitudeCache(etape.getLatitudeCache());
        dto.setLongitudeCache(etape.getLongitudeCache());
        return dto;
    }
}
