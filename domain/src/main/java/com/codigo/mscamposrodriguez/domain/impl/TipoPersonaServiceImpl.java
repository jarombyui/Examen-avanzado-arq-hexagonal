package com.codigo.mscamposrodriguez.domain.impl;


import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoPersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoPersona;
import com.codigo.mscamposrodriguez.domain.ports.in.TipoPersonaServiceIn;
import com.codigo.mscamposrodriguez.domain.ports.out.TipoPersonaServiceOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPersonaServiceImpl implements TipoPersonaServiceIn {

    @Autowired
    private TipoPersonaServiceOut tipoPersonaServiceOut;
    @Override
    public TipoPersonaDTO crearTipoPersonaIn(RequestTipoPersona requestTipoPersona) {
        return tipoPersonaServiceOut.crearTipoPersonaOut(requestTipoPersona);
    }

    @Override
    public Optional<TipoPersonaDTO> obtenerTipoPersonaIn(Long id) {
        return tipoPersonaServiceOut.obtenerTipoPersonaOut(id);
    }

    @Override
    public List<TipoPersonaDTO> obtenerTodosIn() {
        return tipoPersonaServiceOut.obtenerTodosOut();
    }

    @Override
    public TipoPersonaDTO actualizarIn(Long id, RequestTipoPersona requestTipoPersona) {
        return tipoPersonaServiceOut.actualizarOut(id, requestTipoPersona);
    }

    @Override
    public TipoPersonaDTO deleteIn(Long id) {
        return tipoPersonaServiceOut.deleteOut(id);
    }

}
