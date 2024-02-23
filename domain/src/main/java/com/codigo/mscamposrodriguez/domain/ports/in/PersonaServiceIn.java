package com.codigo.mscamposrodriguez.domain.ports.in;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PersonaServiceIn {

    PersonaDTO crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(String numDoc);

    List<PersonaDTO> obtenerTodosIn();
    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);

    PersonaDTO deleteIn(Long id);





}
