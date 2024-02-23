package com.codigo.mscamposrodriguez.application.mapper;

import com.codigo.mscamposrodriguez.application.entity.PersonaEntity;
import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {


    // static en el metodo para utilizar el meotdo son instanciarlo
    private static final ModelMapper modelMapper =  new ModelMapper();
    public PersonaDTO mapToDto(PersonaEntity personaEntity){
        return modelMapper.map(personaEntity, PersonaDTO.class);
    }

    public PersonaEntity mapToEntity(PersonaDTO personaDTO){
        return modelMapper.map(personaDTO, PersonaEntity.class );
    }

}
