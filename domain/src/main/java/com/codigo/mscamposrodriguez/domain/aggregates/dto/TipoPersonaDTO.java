package com.codigo.mscamposrodriguez.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TipoPersonaDTO {

    private Long idTipoPersona;
    private String codTipo;
    private String descTipo;
    private int estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
