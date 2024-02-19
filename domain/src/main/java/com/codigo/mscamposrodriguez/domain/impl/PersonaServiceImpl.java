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

public class PersonaServiceImpl implements PersonaServiceIn {

    @Autowired
    private PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return personaServiceOut.obtenerPersonaOut(id);
    }

    @Override
    public Optional<PersonaDTO> obtenerXDNIIn(String dni) {
        return personaServiceOut.obtenerXDNIOut(dni);
    }

    @Override
    public List<PersonaDTO> obtenerXEstadoIn(int estado) {
        return personaServiceOut.obtenerXEstadoOut(estado);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarOut(id, requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }

}
