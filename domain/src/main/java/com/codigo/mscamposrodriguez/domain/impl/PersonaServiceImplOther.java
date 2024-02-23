package com.codigo.mscamposrodriguez.domain.impl;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import com.codigo.mscamposrodriguez.domain.ports.in.PersonaServiceIn;
import com.codigo.mscamposrodriguez.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImplOther implements PersonaServiceIn {

    @Autowired
    private PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return null;
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numDoc) {
        return Optional.empty();
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return null;
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarOut(id,requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}
