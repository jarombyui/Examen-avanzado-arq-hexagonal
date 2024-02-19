package com.codigo.mscamposrodriguez.application.controller;


import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import com.codigo.mscamposrodriguez.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {

    @Autowired
    private PersonaServiceIn personaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity.status(HttpStatus.CREATED).body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PersonaDTO> obternerPersona(@PathVariable Long id){
        Optional<PersonaDTO> dtoOptional  = personaServiceIn.obtenerPersonaIn(id);
        return dtoOptional.map(dto ->
                        ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PersonaDTO> obtenerXDNI(@PathVariable String dni){
        Optional<PersonaDTO> dtoOptional  = personaServiceIn.obtenerXDNIIn(dni);
        return dtoOptional.map(dto ->
                        ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/estado")
    public ResponseEntity<List<PersonaDTO>> obtenerXEstado(){
        List<PersonaDTO> personasActivas = personaServiceIn.obtenerXEstadoIn(1);

        if (!personasActivas.isEmpty()) {
            return ResponseEntity.ok(personasActivas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PersonaDTO>> obtenerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.obtenerTodosIn());
    }

    @PutMapping("/guardar/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Long id, @RequestBody RequestPersona requestPersona){
        return ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.actualizarIn(id, requestPersona));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<PersonaDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(personaServiceIn.deleteIn(id));
    }



}
