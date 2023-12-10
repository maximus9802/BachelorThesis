package com.quyvx.main_server.infrastructure.queries.location;

import com.quyvx.main_server.api.application.queries.location.ILocationQueriesService;
import com.quyvx.main_server.infrastructure.jpa_repositories.LocationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationQueriesService implements ILocationQueriesService {
    private final LocationJpaRepository locationJpaRepository;
}
