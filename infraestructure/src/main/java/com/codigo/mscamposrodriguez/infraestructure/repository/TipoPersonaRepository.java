package com.codigo.mscamposrodriguez.infraestructure.repository;

import com.codigo.mscamposrodriguez.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoPersonaRepository  extends JpaRepository<TipoPersonaEntity, Long> {
    TipoPersonaEntity findByCodTipoPersona(@Param("codTipo") String codTipo);
}
