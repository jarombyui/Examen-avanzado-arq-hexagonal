package com.codigo.mscamposrodriguez.application.controller;


import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoDocumentoDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoDocumento;
import com.codigo.mscamposrodriguez.domain.ports.in.TipoDocumentoServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/tipoDoc")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoServiceIn tipoDocumentoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<TipoDocumentoDTO> crear(@RequestBody RequestTipoDocumento requestTipoDocumento){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(tipoDocumentoServiceIn.crearTipoDocumentoIn(requestTipoDocumento));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TipoDocumentoDTO> obtenerTipoDoc(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoDocumentoServiceIn.obtenerTipoDocumentoIn(id).get());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoDocumentoDTO>> obtenerTodo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoDocumentoServiceIn.obtenerTodosIn());
    }

    @PutMapping("/guardar/{id}")
    public ResponseEntity<TipoDocumentoDTO> actualizar(@PathVariable Long id, @RequestBody RequestTipoDocumento requestTipoDocumento){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoDocumentoServiceIn.actualizarIn(id, requestTipoDocumento));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<TipoDocumentoDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoDocumentoServiceIn.deleteIn(id));
    }

}
