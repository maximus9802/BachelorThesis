package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "manager_location")
public class ManagerLocationEntity extends BaseEntity{
    @Column(insertable = false, updatable = false)
    private Long managerId;
    private Long locationId;

    @ManyToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    private ManagerEntity manager;
}
