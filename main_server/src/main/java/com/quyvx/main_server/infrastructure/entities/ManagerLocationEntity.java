package com.quyvx.main_server.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@SuperBuilder
@Entity
@Table(name = "manager_location")
public class ManagerLocationEntity extends BaseEntity{
    private Long managerId;
    private Long locationId;

    @ManyToOne
    @JoinColumn(name = "managerId", referencedColumnName = "id")
    private ManagerEntity manager;
}
