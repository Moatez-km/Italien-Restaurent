package de.tekup.ex.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetResponse {

    private String nom;
    private float prix;
    private String type;

}
