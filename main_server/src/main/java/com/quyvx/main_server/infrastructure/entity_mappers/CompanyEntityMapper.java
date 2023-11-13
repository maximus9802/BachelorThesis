package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.infrastructure.entities.CompanyEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CompanyEntityMapper {

    public CompanyEntity modelToEntity(Company model) {
        return CompanyEntity.builder()
                .id(model.getId())
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .identityId(model.getIdentityId())
                .companyName(model.getCompanyName())
                .address(model.getAddress())
                .phoneNumber(model.getPhoneNumber())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Company entityToModel(CompanyEntity entity) {
        if(ObjectUtils.isEmpty(entity)) return null;

        return Company.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .identityId(entity.getIdentityId())
                .companyName(entity.getCompanyName())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .isDeleted(entity.getIsDeleted())
                .build();
    }

}
