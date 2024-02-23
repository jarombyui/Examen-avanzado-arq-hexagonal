package com.codigo.mscamposrodriguez.application.controller;


import com.codigo.mscamposrodriguez.domain.aggregates.dto.TipoPersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestTipoPersona;
import com.codigo.mscamposrodriguez.domain.ports.in.TipoPersonaServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/tipoPersona")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaServiceIn tipoPersonaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<TipoPersonaDTO> crear(@RequestBody RequestTipoPersona requestTipoPersona){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(tipoPersonaServiceIn.crearTipoPersonaIn(requestTipoPersona));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TipoPersonaDTO> obtenerTipoDoc(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoPersonaServiceIn.obtenerTipoPersonaIn(id).get());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoPersonaDTO>> obtenerTodo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoPersonaServiceIn.obtenerTodosIn());
    }

    @PutMapping("/guardar/{id}")
    public ResponseEntity<TipoPersonaDTO> actualizar(@PathVariable Long id, @RequestBody RequestTipoPersona requestTipoDocumento){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoPersonaServiceIn.actualizarIn(id, requestTipoDocumento));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<TipoPersonaDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tipoPersonaServiceIn.deleteIn(id));
    }

}
