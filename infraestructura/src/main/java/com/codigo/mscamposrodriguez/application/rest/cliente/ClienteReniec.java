package com.codigo.mscamposrodriguez.application.rest.cliente;


import com.codigo.mscamposrodriguez.domain.aggregates.response.ResponseReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cliente-reniec", url = "https://api.apis.net.pe/v2/reniec/")
public interface ClienteReniec {
    @GetMapping("/dni")
    ResponseReniec getInfoReniec(@RequestParam("numero") String numero,
                                 @RequestHeader("Authorization") String autorization);
}