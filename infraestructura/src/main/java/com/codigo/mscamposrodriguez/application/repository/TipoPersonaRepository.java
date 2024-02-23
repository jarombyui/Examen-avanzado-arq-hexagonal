package com.codigo.mscamposrodriguez.application.repository;

import com.codigo.mscamposrodriguez.application.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import feign.Param;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {
    TipoPersonaEntity findByCodTipo( String codTipo);// el parametrer "x" hace reerencia a donde se reemplazara en kla query
}
