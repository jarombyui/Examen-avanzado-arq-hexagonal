package com.codigo.mscamposrodriguez.domain.impl;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import com.codigo.mscamposrodriguez.domain.ports.in.PersonaServiceIn;
import com.codigo.mscamposrodriguez.domain.ports.out.PersonaServiceOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonaServiceImpl implements PersonaServiceIn {
    @Autowired// para injectar
    private PersonaServiceOut personaServiceOut; // este es el objeto que se abajo para llamar asus metodos

    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) { //RequestPersona es usado como parametro
        return personaServiceOut.crearPersonaOut(requestPersona);// requestPersona es usado como argumento
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numDoc) {
        return personaServiceOut.obtenerPersonaOut(numDoc);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }


    /// estos 2 ultimos lo implemente en PersonServiceImplOther
    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return null;
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return null;
    }
}
