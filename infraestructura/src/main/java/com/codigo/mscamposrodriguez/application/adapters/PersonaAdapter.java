package com.codigo.mscamposrodriguez.application.adapters;

import com.codigo.mscamposrodriguez.application.entity.PersonaEntity;
import com.codigo.mscamposrodriguez.application.entity.TipoDocumentoEntity;
import com.codigo.mscamposrodriguez.application.entity.TipoPersonaEntity;
import com.codigo.mscamposrodriguez.application.mapper.PersonaMapper;
import com.codigo.mscamposrodriguez.application.repository.PersonaRepository;
import com.codigo.mscamposrodriguez.application.repository.TipoDocumentoRepository;
import com.codigo.mscamposrodriguez.application.repository.TipoPersonaRepository;
import com.codigo.mscamposrodriguez.application.rest.cliente.ClienteReniec;
import com.codigo.mscamposrodriguez.domain.aggregates.constants.Constants;
import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.domain.aggregates.request.RequestPersona;
import com.codigo.mscamposrodriguez.domain.aggregates.response.ResponseReniec;
import com.codigo.mscamposrodriguez.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final ClienteReniec reniec;
    private final PersonaMapper personaMapper;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec responseReniec = getInfo(requestPersona.getNumDoc());
        personaRepository.save(getEntity(responseReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(responseReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(String numDoc) {
        return  Optional.of(personaMapper.mapToDto(personaRepository.findByNumDocu(numDoc)));
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOS = new ArrayList<>(); // 1 declaro un aray lis tipo perosonaDTO vacia
        List<PersonaEntity> entityList = personaRepository.findByEstado(Constants.STATUS_ACTIVE);// recupero mi lista
        for(PersonaEntity entity : entityList){// recorro la lista con  for each
            PersonaDTO personaDTO = personaMapper.mapToDto(entity);// creo una personaDTO
            personaDTOS.add(personaDTO);// aqui se llena la lista la añado a la lista
        }
        return personaDTOS;// me devuelve una lista que cree al principio que se llena en el bucle
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if (existe){
            Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);
            ResponseReniec responseReniec = getInfo(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec, personaRecuperada.get()));
            return personaMapper.mapToDto(getEntityUpdate(responseReniec, personaRecuperada.get()));
        }

        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if (existe){
            Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);
            personaRecuperada.get().setEstado(Constants.STATUS_INACTIVE);
            personaRecuperada.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            personaRecuperada.get().setDateDelet(getTimestamp());
            personaRepository.save(personaRecuperada.get());
            return personaMapper.mapToDto(personaRecuperada.get());
        }
        return null;
    }

    // metodos de apoyo
    private ResponseReniec getInfo(String numero){
        String autho = "Bearer" + tokenApi;
        return reniec.getInfoReniec(numero,autho);
    }

    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        System.out.println("PASEEEEEE TIPODOC");
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        System.out.println("PASEEEEE TIPO PERSO");
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setNumDocu(reniec.getNumeroDocumento());
        personaActualizar.setNumDocu(reniec.getNumeroDocumento());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }


}
