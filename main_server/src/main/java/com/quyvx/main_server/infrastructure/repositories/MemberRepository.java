package com.quyvx.main_server.infrastructure.repositories;

import com.quyvx.main_server.domain.aggregate_models.member_aggregate.Member;
import com.quyvx.main_server.domain.repositories.IMemberRepository;
import com.quyvx.main_server.infrastructure.entities.MemberEntity;
import com.quyvx.main_server.infrastructure.entity_mappers.MemberEntityMapper;
import com.quyvx.main_server.infrastructure.jpa_repositories.MemberJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class MemberRepository implements IMemberRepository{
    private final MemberJpaRepository memberJpaRepository;
    private final MemberEntityMapper mapper;
    @Override
    public Member save(Member model) {
        MemberEntity entity = mapper.modelToEntity(model);
        entity = memberJpaRepository.save(entity);
        return mapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        memberJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id).map(mapper::entityToModel);
    }
}
