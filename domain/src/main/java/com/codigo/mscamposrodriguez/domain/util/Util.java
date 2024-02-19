package com.codigo.mscamposrodriguez.domain.util;

import com.codigo.mscamposrodriguez.domain.aggregates.dto.PersonaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Getter
@Setter
@Component
public class Util {
    //Converir a String
    public static String convertirToJson(PersonaDTO personaDTO){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(personaDTO);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);

        }
    }
    //Convertir a objeto
    public static <T> T convertirFromJson(String json, Class<T> valueType){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, valueType);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public Timestamp getTimestamp(){
        Long currient = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currient);
        return timestamp;
    }


}
