package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.company_aggregate.Company;
import com.quyvx.main_server.infrastructure.entities.CompanyEntity;
import com.quyvx.main_server.shared.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CompanyEntityMapper {

    public CompanyEntity modelToEntity(Company model) {
        return CompanyEntity.builder()
                .id(model.getId())
                .createAt(TimeUtils.nullOrNow(model.createAt))
                .updateAt(TimeUtils.nullOrNow(model.updateAt))
                .identityId(model.getIdentityId())
                .companyName(model.getCompanyName())
                .address(model.getAddress())
                .phoneNumber(model.getPhoneNumber())
                .email(model.getEmail())
                .permanentLink(model.getPermanentLink())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Company entityToModel(CompanyEntity entity) {
        if(ObjectUtils.isEmpty(entity)) return null;

        return Company.builder()
                .id(entity.getId())
                .createAt(TimeUtils.nullOrNow(entity.getCreateAt()))
                .updateAt(TimeUtils.nullOrNow(entity.getUpdateAt()))
                .identityId(entity.getIdentityId())
                .companyName(entity.getCompanyName())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .permanentLink(entity.getPermanentLink())
                .isDeleted(entity.getIsDeleted())
                .build();
    }

}
