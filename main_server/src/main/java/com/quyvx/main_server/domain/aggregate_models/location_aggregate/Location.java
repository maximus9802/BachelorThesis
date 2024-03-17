package com.quyvx.main_server.domain.aggregate_models.location_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Location extends EntityAggregateRoot {
    private Long companyId;
    private String name;
    private String address;
    private String permanentLink;
    private Boolean isDeleted;

    public void updateLocation(String address, String name) {
        this.address = address;
        this.name = name;
        this.updateAt = TimeUtils.now();
    }

    public void updatePermanentLinkLocation(String permanentLink) {
        this.permanentLink = permanentLink;
        this.updateAt = TimeUtils.now();
    }

    public void deleteLocation(){
        this.isDeleted = Boolean.TRUE;
        this.updateAt = TimeUtils.now();
    }
}
