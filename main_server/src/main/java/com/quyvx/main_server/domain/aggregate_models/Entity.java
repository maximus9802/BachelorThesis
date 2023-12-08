package com.quyvx.main_server.domain.aggregate_models;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
public class Entity {
    public Long id;
    public LocalDateTime createAt;
    public LocalDateTime updateAt;

    public Entity(){

    }

    public Entity(Long id, LocalDateTime createAt, LocalDateTime updateAt){
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
