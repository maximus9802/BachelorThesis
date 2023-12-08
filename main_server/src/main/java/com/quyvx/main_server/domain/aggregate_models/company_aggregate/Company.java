package com.quyvx.main_server.domain.aggregate_models.company_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Company extends EntityAggregateRoot {
    private Long identityId;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String permanentLink;
    private Boolean isDeleted;
}
