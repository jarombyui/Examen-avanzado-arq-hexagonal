package com.codigo.mscamposrodriguez.infraestructure.mapper;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.codigo.mscamposrodriguez.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PersonaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public PersonaDTO mapToDTO(PersonaEntity entity){
        return modelMapper.map(entity, PersonaDTO.class);
    }

    public PersonaEntity mapToEntity(PersonaDTO dto){
        return modelMapper.map(dto, PersonaEntity.class);
    }

    public List<PersonaDTO> mapToDTOList(List<PersonaEntity> personaEntities) {

        Type listType = new TypeToken<List<PersonaDTO>>() {}.getType();
        return modelMapper.map(personaEntities, listType);
    }

}
