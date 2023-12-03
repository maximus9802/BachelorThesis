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

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "identities")
public class IdentityEntity extends BaseEntity {
    private String loginId;
    private String password;
    private Boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identity", orphanRemoval = true)
    private List<IdentityRoleEntity> identityRoles;
}
