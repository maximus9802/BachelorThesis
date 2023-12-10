package com.quyvx.main_server.api.application.queries.authentication_type;

public interface IAuthenticationTypeQueriesService {
    Long getAuthenticationTypeIdByTypeName(String typeName);

    String getAuthenticationTypeById(Long typeId);
}
