package com.quyvx.main_server.shared.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private Map<String, String> errors;

    public BadRequestException(Map<String, String> errors) {
        this.errors = errors;
    }

    public BadRequestException(String jsonString) {
        JSONObject jsonObject = new JSONObject();
        Object errorsResponse = jsonObject.get("errors");
        this.errors = ((JSONObject) errorsResponse).toMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
    }
}
