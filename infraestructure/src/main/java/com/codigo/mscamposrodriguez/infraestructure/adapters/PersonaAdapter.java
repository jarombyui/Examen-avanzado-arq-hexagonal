package com.codigo.mscamposrodriguez.infraestructure.adapters;


import com.codigo.mscamposrodriguez.domain.aggregates.constants.Constant;
import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import com.codigo.mscamposrodriguez.domain.aggregates.response.ResponseReniec;
import com.codigo.mscamposrodriguez.domain.ports.out.PersonaServiceOut;
import com.codigo.mscamposrodriguez.domain.util.Util;
import com.codigo.mscamposrodriguez.infraestructure.entity.PersonaEntity;
import com.codigo.mscamposrodriguez.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.mscamposrodriguez.infraestructure.entity.TipoPersonaEntity;
import com.codigo.mscamposrodriguez.infraestructure.mapper.PersonaMapper;
import com.codigo.mscamposrodriguez.infraestructure.repository.PersonaRepository;
import com.codigo.mscamposrodriguez.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.mscamposrodriguez.infraestructure.repository.TipoPersonaRepository;
import com.codigo.mscamposrodriguez.infraestructure.rest.client.ClientReniec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaAdapter implements PersonaServiceOut {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;
    @Autowired
    private ClientReniec reniec;

    @Autowired
    private Util utils;
    @Value("${token.api}")
    String tokenApi;

    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec responseReniec = getExecutionReniec(tokenApi, requestPersona.getNumDoc());
        PersonaEntity entity =personaRepository.save(getEntity(responseReniec, requestPersona));
        return personaMapper.mapToDTO(entity);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long id) {
        //return Optional.of(personaMapper.mapToDTO(personaRepository.findById(id).get()));
        return personaRepository.findById(id)
                .map(personaMapper::mapToDTO);
    }

    @Override
    public Optional<PersonaDTO> obtenerXDNIOut(String dni) {
        return Optional.of(personaMapper.mapToDTO(personaRepository.findByNumDocu(dni)));
    }

    @Override
    public List<PersonaDTO> obtenerXEstadoOut(int estado) {
        List<PersonaEntity> personasActivas = personaRepository.findByEstado(estado); // 1 representa el estado activo
        return personaMapper.mapToDTOList(personasActivas);
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> dto = new ArrayList<>();
        List<PersonaEntity> personas = personaRepository.findAll();
        if (!personas.isEmpty()){
            for (PersonaEntity entity : personas){
                PersonaDTO personaDTO = personaMapper.mapToDTO(entity);
                dto.add(personaDTO);
                System.out.println(personaDTO.getNombres());
            }
            System.out.println("Hola");
            System.out.println(dto.get(1));
            System.out.println(dto.get(2));
            return dto;
        }
        return null;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(tokenApi, requestPersona.getNumDoc());
            personaRepository.save(getUpdate(responseReniec, entity.get(), requestPersona));
            return personaMapper.mapToDTO(getUpdate(responseReniec, entity.get(), requestPersona));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setDateDelet(utils.getTimestamp());
            entity.get().setUsuaDelet(Constant.AUDIT_ADMIN);
            entity.get().setEstado(Constant.STATUS_DISABLED);
            return personaMapper.mapToDTO(personaRepository.save(entity.get()));
        }
        return null;
    }



    private PersonaEntity getEntity(ResponseReniec responseReniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipoPersona(requestPersona.getTipoPer());
        PersonaEntity persona = new PersonaEntity();
        persona.setNumDocu(responseReniec.getNumeroDocumento());
        persona.setApePat(responseReniec.getApellidoPaterno());
        persona.setApeMat(responseReniec.getApellidoMaterno());
        persona.setNombres(responseReniec.getNombres());
        persona.setEstado(Constant.STATUS_ACTIVE);
        persona.setTipoDocumento(tipoDocumento);
        persona.setTipoPersona(tipoPersona);
        persona.setDateCreate(utils.getTimestamp());
        persona.setUsuaCrea(Constant.AUDIT_ADMIN);
        return persona;
    }

    private PersonaEntity getUpdate(ResponseReniec responseReniec, PersonaEntity personaActualizar, RequestPersona requestPersona){
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipoPersona(requestPersona.getTipoPer());
        personaActualizar.setNumDocu(responseReniec.getNumeroDocumento());
        personaActualizar.setApePat(responseReniec.getApellidoPaterno());
        personaActualizar.setApeMat(responseReniec.getApellidoMaterno());
        personaActualizar.setNombres(responseReniec.getNombres());
        personaActualizar.setTipoPersona(tipoPersona);
        personaActualizar.setDateModif(utils.getTimestamp());
        personaActualizar.setUsuaModif(Constant.AUDIT_ADMIN);
        return personaActualizar;
    }

    public ResponseReniec getExecutionReniec(String tokenApi, String numero){
        String authorization = "Bearer"+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero, authorization);
        return responseReniec;
    }

}
