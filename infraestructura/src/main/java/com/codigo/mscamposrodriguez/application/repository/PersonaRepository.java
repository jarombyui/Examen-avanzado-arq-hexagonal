package com.codigo.mscamposrodriguez.application.repository;

import com.codigo.mscamposrodriguez.application.entity.PersonaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByNumDocu(@Param("numDocu") String numDocu);// filtrara una persona por el numero de documento
    List<PersonaEntity> findByEstado(@Param("estado") Integer estado);//devuelve una lista // filtra persona por el estado activo
}
