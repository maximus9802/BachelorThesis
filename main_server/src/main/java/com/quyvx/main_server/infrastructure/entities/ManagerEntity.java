package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "managers")
public class ManagerEntity extends BaseEntity{
    private Long memberId;
    private Boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager", orphanRemoval = true)
    private List<ManagerLocationEntity> managerLocations;
}
