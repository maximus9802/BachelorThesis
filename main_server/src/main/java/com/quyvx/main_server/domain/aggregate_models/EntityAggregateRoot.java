package com.quyvx.main_server.domain.aggregate_models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
public class EntityAggregateRoot extends Entity {
    public EntityAggregateRoot(){
        super();
    }

    public EntityAggregateRoot(Long id, LocalDateTime createdAt, LocalDateTime updateAt) {
        super(id, createdAt, updateAt);
    }
}
