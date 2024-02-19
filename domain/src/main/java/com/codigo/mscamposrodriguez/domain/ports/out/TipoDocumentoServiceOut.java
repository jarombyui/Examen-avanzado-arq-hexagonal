package com.codigo.mscamposrodriguez.domain.ports.out;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoDocumentoDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoDocumento;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoServiceOut {

    TipoDocumentoDTO crearTipoDocumentoOut(RequestTipoDocumento requestTipoDocumento);
    Optional<TipoDocumentoDTO> obtenerTipoDocumentoOut(Long id);
    List<TipoDocumentoDTO> obtenerTodosOut();
    TipoDocumentoDTO actualizarOut(Long id, RequestTipoDocumento requestTipoDocumento);
    TipoDocumentoDTO deleteOut(Long id);
}
