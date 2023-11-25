package com.quyvx.main_server.shared.exceptions;

import com.quyvx.main_server.shared.libs.application.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizationException.class)
    public ApiResponse handleUnauthorizationException(HttpServletRequest req, UnauthorizationException ex) {
        return responseBuilder(req, ex);
    }

    private ApiResponse<Map<String, Object>> responseBuilder(HttpServletRequest req, Exception ex) {
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }
}
