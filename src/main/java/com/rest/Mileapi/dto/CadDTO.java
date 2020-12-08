package com.rest.Mileapi.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadDTO {
    private Long id;
    @NotEmpty
    private String nomeDono;
    @NotEmpty
    private String nomePet;
    @NotEmpty
    private String porte;
    @NotEmpty
    private String raca;

}
