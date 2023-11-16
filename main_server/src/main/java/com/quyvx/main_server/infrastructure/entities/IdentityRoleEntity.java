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
@Entity
@SuperBuilder
@Table(name = "identity_role")
public class IdentityRoleEntity extends BaseEntity {
    @Column(insertable = false, updatable = false)
    private Long identityId;
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "identityId", referencedColumnName = "id")
    private IdentityEntity identity;
}
