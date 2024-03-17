package com.quyvx.main_server.domain.aggregate_models.company_aggregate;

import com.quyvx.main_server.domain.aggregate_models.EntityAggregateRoot;
import com.quyvx.main_server.shared.utils.TimeUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Company extends EntityAggregateRoot {
    private Long identityId;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String email;
    private String permanentLink;
    private Boolean isDeleted;

    public void updateCompanyInfo(String companyName, String phoneNumber, String address, String email, String permanentLink){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.permanentLink = permanentLink;
        this.updateAt = TimeUtils.now();
    }

    public void deleteCompany(){
        this.isDeleted = Boolean.TRUE;
        this.updateAt = TimeUtils.now();
    }
}
