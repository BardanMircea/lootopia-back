package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Etape;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtapeApercuDTO {

    private Integer ordre;
    private String consigne;
    private Double latitudeCache;
    private Double longitudeCache;
    private String passphrase;


    public static EtapeApercuDTO fromEntity(Etape etape) {
        EtapeApercuDTO dto = new EtapeApercuDTO();
        dto.setOrdre(etape.getOrdre());
        dto.setConsigne(etape.getConsigne());
        dto.setPassphrase(etape.getPassphrase());
        dto.setLatitudeCache(etape.getLatitudeCache());
        dto.setLongitudeCache(etape.getLongitudeCache());
        return dto;
    }
}
