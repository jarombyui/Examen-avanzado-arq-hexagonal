package com.codigo.mscamposrodriguez.infraestructure.rest;

import com.codigo.mscamposrodriguez.domain.aggregates.response.ResponseReniec;
import com.codigo.mscamposrodriguez.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter {
    private final ClientReniec reniec;

    @Value("${token.api}")
    private String tokenApi;


    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numDoc,authorization);
        return responseReniec;
    }

}
