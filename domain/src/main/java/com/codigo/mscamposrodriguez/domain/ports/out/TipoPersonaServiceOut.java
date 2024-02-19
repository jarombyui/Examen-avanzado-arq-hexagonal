package com.codigo.mscamposrodriguez.domain.ports.out;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoPersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoPersona;

import java.util.List;
import java.util.Optional;

public interface TipoPersonaServiceOut {
    TipoPersonaDTO crearTipoPersonaOut(RequestTipoPersona requestTipoPersona);
    Optional<TipoPersonaDTO> obtenerTipoPersonaOut(Long id);
    List<TipoPersonaDTO> obtenerTodosOut();
    TipoPersonaDTO actualizarOut(Long id, RequestTipoPersona requestTipoPersona);
    TipoPersonaDTO deleteOut(Long id);
}
