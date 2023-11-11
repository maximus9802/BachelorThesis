package com.quyvx.main_server.infrastructure.entity_mappers;

import com.quyvx.main_server.domain.aggregate_models.member_aggregate.Member;
import com.quyvx.main_server.infrastructure.entities.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MemberEntityMapper {
    public MemberEntity modelToEntity(Member model) {
        return MemberEntity.builder()
                .id(model.id)
                .createAt(model.getCreatedAt())
                .updateAt(model.getUpdateAt())
                .identityId(model.getIdentityId())
                .lastName(model.getLastName())
                .firstName(model.getFirstName())
                .dateOfBirth(model.getDateOfBirth())
                .postCode(model.getPostCode())
                .address(model.getAddress())
                .email(model.getEmail())
                .phoneNumber(model.getPhoneNumber())
                .isDeleted(model.getIsDeleted())
                .build();
    }

    public Member entityToModel(MemberEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        return Member.builder()
                .id(entity.getId())
                .createdAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .identityId(entity.getIdentityId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .dateOfBirth(entity.getDateOfBirth())
                .postCode(entity.getPostCode())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
