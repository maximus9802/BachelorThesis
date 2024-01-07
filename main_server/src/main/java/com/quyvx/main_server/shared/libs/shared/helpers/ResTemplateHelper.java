package com.quyvx.main_server.shared.libs.shared.helpers;

import com.quyvx.main_server.shared.exceptions.BadRequestException;
import com.quyvx.main_server.shared.exceptions.ForbiddenException;
import com.quyvx.main_server.shared.exceptions.InnerServerErrorException;
import com.quyvx.main_server.shared.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class ResTemplateHelper {

    private ResTemplateHelper() { throw new IllegalStateException("Utility class");}

    public static <T, G> T callService(String endpoint, String apiPath, G request, String token, HttpMethod httpMethod, Class<T> responseClass) {

        if (StringUtils.isBlank(endpoint)) {
            throw new NotFoundException("Endpoint is blank");
        }
        String url = endpoint.concat(apiPath);
        try {
            RestTemplate restTemplate = new RestTemplate();
            log.info("----- Call to: {} start -----", url);
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, createHttpEntity(request, token), responseClass);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            exceptionHandler(e);
        } finally {
            log.info("----- Call to: {} finished -----", url);
        }
        return null;
    }

    private static HttpEntity<?> createHttpEntity(Object request, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        if (StringUtils.isNotBlank(token)) {
            headers.set(HttpHeaders.AUTHORIZATION, token);
        }
        return new HttpEntity<>(request, headers);
    }

    private static void exceptionHandler(HttpClientErrorException e) {
        HttpStatus status = HttpStatus.resolve(e.getStatusCode().value());
        if (status == null) {
            throw new InnerServerErrorException("Failed call to:" + e.getMessage());
        }
        switch (status) {
            case NOT_FOUND -> throw new NotFoundException(e.getMessage());
            case FORBIDDEN -> throw new ForbiddenException(e.getMessage());
            case BAD_REQUEST -> throw new BadRequestException(e.getResponseBodyAsString());
            case UNAUTHORIZED -> throw new NotFoundException();
            default -> throw new InnerServerErrorException(e.getMessage());
        }
    }
}
