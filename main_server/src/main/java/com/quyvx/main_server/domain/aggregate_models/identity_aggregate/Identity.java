package com.quyvx.main_server.domain.aggregate_models.identity_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class Identity extends EntityAggregateRoot {
    private String loginId;
    private String password;
    private Boolean isDeleted;
    private List<IdentityRole> identityRoles;

    public void updateIdentity(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
        this.updateAt = TimeUtils.now();
    }

    public void deleteIdentity() {
        this.isDeleted = Boolean.TRUE;
        this.updateAt = TimeUtils.now();
    }
}
