package com.bnym.reverseproxy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "proxy-config")
@Component
public class ProxyService {
    private Map<String, String> urlMapping;
    private final static Logger logger = LogManager.getLogger(ProxyService.class);

    public Map<String, String> getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(Map<String, String> urlMapping) {
        this.urlMapping = urlMapping;
    }

    public ResponseEntity<Void> processProxyRequest(String body,
                                                      HttpMethod method, HttpServletRequest request, HttpServletResponse response, String traceId) throws URISyntaxException {
        String requestUrl = request.getRequestURI();

        //log if required in this line
        URI uri = new URI(urlMapping.get(requestUrl));

        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
}}

