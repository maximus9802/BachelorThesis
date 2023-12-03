package com.quyvx.main_server.shared.exceptions;

import com.quyvx.main_server.shared.libs.application.dto.ApiResponse;
import com.quyvx.main_server.shared.libs.shared.helpers.MessageHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse handleNotFoundException(HttpServletRequest req, NotFoundException ex) {
        return responseBuilder(req, ex);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class, AccessDeniedException.class})
    public ApiResponse handleForbiddenException(HttpServletRequest req) {
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(false, MessageHelper.getMessage("access_denied"));
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }

    private ApiResponse<Map<String, Object>> responseBuilder(HttpServletRequest req, Exception ex) {
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }
}
