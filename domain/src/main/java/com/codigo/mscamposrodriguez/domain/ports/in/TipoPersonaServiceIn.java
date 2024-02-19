package com.codigo.mscamposrodriguez.domain.ports.in;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoPersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoPersona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

public interface TipoPersonaServiceIn {

    TipoPersonaDTO crearTipoPersonaIn(RequestTipoPersona requestTipoPersona);
    Optional<TipoPersonaDTO> obtenerTipoPersonaIn(Long id);
    List<TipoPersonaDTO> obtenerTodosIn();
    TipoPersonaDTO actualizarIn(Long id, RequestTipoPersona requestTipoPersona);
    TipoPersonaDTO deleteIn(Long id);
}
