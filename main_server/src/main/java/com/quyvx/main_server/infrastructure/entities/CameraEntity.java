package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "cameras")
public class CameraEntity extends BaseEntity{
    private Long locationId;
    private String name;
    private Long typeAuthenId;
    @Column(name = "camera_uuid")
    private String cameraUUID;
    private Boolean isDeleted;
}
