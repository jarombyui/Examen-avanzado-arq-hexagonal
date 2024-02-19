package com.codigo.mscamposrodriguez.domain.ports.in;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoDocumentoDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoDocumento;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoServiceIn {

    TipoDocumentoDTO crearTipoDocumentoIn(RequestTipoDocumento requestTipoDocumento);
    Optional<TipoDocumentoDTO> obtenerTipoDocumentoIn(Long id);
    List<TipoDocumentoDTO> obtenerTodosIn();
    TipoDocumentoDTO actualizarIn(Long id, RequestTipoDocumento requestTipoDocumento);
    TipoDocumentoDTO deleteIn(Long id);


}
