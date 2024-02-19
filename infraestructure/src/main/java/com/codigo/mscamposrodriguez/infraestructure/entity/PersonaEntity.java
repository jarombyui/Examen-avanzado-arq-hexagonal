package com.codigo.mscamposrodriguez.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

    @Entity
    @Table(name = "persona")
    @Getter
    @Setter
    public class PersonaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_persona")
        private Long idPersona;

        @Column(name = "num_docu", nullable = false)
        private String numDocu;

        @Column(name = "nombres", nullable = false)
        private String nombres;

        @Column(name = "ape_pat", nullable = false)
        private String apePat;

        @Column(name = "ape_mat", nullable = false)
        private String apeMat;

        @Column(name = "estado", nullable = false)
        private int estado;

        @Column(name = "usua_crea")
        private String usuaCrea;

        @Column(name = "date_create")
        private Timestamp dateCreate;

        @Column(name = "usua_modif")
        private String usuaModif;

        @Column(name = "date_modif")
        private Timestamp dateModif;

        @Column(name = "usua_delet")
        private String usuaDelet;

        @Column(name = "date_delet")
        private Timestamp dateDelet;

        @ManyToOne
        @JoinColumn(name = "tipo_documento_id", nullable = false)
        private TipoDocumentoEntity tipoDocumento;

        @ManyToOne
        @JoinColumn(name = "tipo_persona_id", nullable = false)
        private TipoPersonaEntity tipoPersona;
}
