package com.bnym.reverseproxy.controller;

import com.bnym.reverseproxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
public class ProxyController {
    @Autowired
    ProxyService service;
    @RequestMapping("/**")
    public ResponseEntity<Void> sendRequestToSPM(@RequestBody(required = false) String body,
                                                   HttpMethod method, HttpServletRequest request, HttpServletResponse response)
            throws URISyntaxException {

        return service.processProxyRequest(body,method,request,response, UUID.randomUUID().toString());
    }
}
